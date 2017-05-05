package com.wb.base;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wb.base.transform.GlideCircleTransform;
import com.wb.base.transform.GlideRoundTransform;
import com.wb.base.utils.DateTimeUtils;
import com.wb.base.utils.MToast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import xsf.zeuslibrary.zeusLog.ZeusLog;

/**
 * Author: 王博  PC:MX
 * Time: 2016/12/13 13:48
 * Email: wb1276831936@163.com
 * Instruction: Fragment基类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    /**
     * 当前界面是否呈现给用户的状态标志
     */
    protected boolean isVisible;

    public Map<String, String> params = new HashMap<>();
    public Map<String, String> headers = new HashMap<>();
    public String url = "";
    public int page = 1;//分页页数

    //当前Fragment的getActivity对象
    public FragmentActivity mContext;

    /**
     * 当前activity的log日志的tag
     */
    @NonNull
    private String logTag = "";

    public View mView;//Fragment视图

    /**
     * 设置布局文件id
     *
     * @return 布局R文件int值
     */
    @LayoutRes
    @NonNull
    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onCreateView(inflater, container, savedInstanceState, "");
    }

    /**
     * onCreateView Fragment创建视图方法
     *
     * @param inflater           LayoutInflater
     * @param container          ViewGroup
     * @param savedInstanceState 保存实例状态
     * @param obj                为方便重写方法,无意义
     * @return View视图
     */
    @Nullable
    public abstract View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, Object obj);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        onActivityCreated(savedInstanceState);
    }

    /**
     * onActivityCreated Activity已创建方法
     *
     * @param savedInstanceState 保存的状态
     * @param obj                无意义
     */
    public abstract void onActivityCreated(@Nullable Bundle savedInstanceState, Object obj);


    /**
     * 重写Fragment父类生命周期方法，在onCreate之前调用该方法，实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser 当前是否已将界面显示给用户的状态
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 当界面呈现给用户，即设置可见时执行，进行加载数据的方法
     * 在用户可见时加载数据，而不在用户不可见的时候加载数据，是为了防止控件对象出现空指针异常
     */
    protected void onVisible() {
        setlazyLoad();
    }

    /**
     * 当界面还没呈现给用户，即设置不可见时执行
     */
    protected void onInvisible() {

    }

    /**
     * 加载数据方法
     */
    protected void setlazyLoad() {

    }

    /**
     * 设置当前Activity的Log日志Tag
     *
     * @return
     */
    @NonNull
    public abstract String setLogTag();

    /**
     * 初始化控件.
     */
    public abstract void initView();

    /**
     * 初始化数据.
     */
    public abstract void initData();

    /**
     * 加载数据.
     */
    public abstract void onLoadData();

    /**
     * 显示结果
     *
     * @param response
     */
    @UiThread
    public abstract void showResult(Object response);

    /**
     * 控件点击事件
     *
     * @param v
     */
    public abstract void widgetClick(View v);

    /**
     * 跳转到指定Activity ,无跳转动画.
     *
     * @param clazz activity类
     */
    public void goToActivity(Class clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }

    /**
     * 跳转到指定Activity ,从右往左进入.
     *
     * @param clazz activity类
     */
    public void goToActivityWithAnim(Class clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
        mContext.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }

    /**
     * 跳转到指定Activity ,指定进入退出动画.
     *
     * @param clazz activity类
     */
    public void goToActivityWithAnim(Class clazz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
        mContext.overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * 点击事件处理.
     *
     * @param view 控件.
     */
    @Override
    public void onClick(View view) {
        widgetClick(view);
    }

    /**
     * toast短时间提示
     *
     * @param msg 内容
     */
    public void shortToast(@NonNull Object msg) {
        MToast.shortToast(String.valueOf(msg));
    }

    /**
     * toast长时间提示
     *
     * @param msg 内容
     */
    public void longToast(@NonNull Object msg) {
        MToast.longToast(String.valueOf(msg));
    }

    /**
     * 设置控件不可见
     *
     * @param v
     */
    public void gone(@NonNull View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].setVisibility(View.GONE);
        }
    }

    /**
     * 设置控件可见
     *
     * @param v
     */
    public void visible(@NonNull View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置控件不可见
     *
     * @param v
     */
    public void invisible(@NonNull View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 淡入淡出加载圆形图片
     *
     * @param imageview 图片控件
     * @param url       图片url
     */
    public void setCircleImage(@NonNull ImageView imageview, @NonNull String url) {
        Glide.with(mContext).load(url).transform(new GlideCircleTransform(mContext)).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载圆形图片
     *
     * @param imageview 图片控件
     * @param drawable  图片Drawable
     */
    public void setCircleImageDrawable(@NonNull ImageView imageview, @NonNull Drawable drawable) {
        Glide.with(mContext).load(drawable).transform(new GlideCircleTransform(mContext)).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载圆形图片
     *
     * @param imageview 图片控件
     * @param imageFile 图片文件
     */
    public void setCircleImageFile(@NonNull ImageView imageview, File imageFile) {
        Glide.with(mContext).load(imageFile).transform(new GlideCircleTransform(mContext)).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载指定圆角大小图片
     *
     * @param imageview 图片控件
     * @param url       图片url
     * @param radius    圆角大小,单位dp
     */
    public void setImageRadius(@NonNull ImageView imageview, @NonNull String url, int radius) {
        Glide.with(mContext).load(url).transform(new GlideRoundTransform(mContext, radius)).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载指定圆角大小图片
     *
     * @param imageview 图片控件
     * @param drawable  图片Drawable
     * @param radius    圆角大小,单位dp
     */
    public void setImageDrawableRadius(@NonNull ImageView imageview, @NonNull Drawable drawable, int radius) {
        Glide.with(mContext).load(drawable).transform(new GlideRoundTransform(mContext, radius)).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载指定圆角大小图片
     *
     * @param imageview 图片控件
     * @param imageFile 图片url
     * @param radius    圆角大小,单位dp
     */
    public void setImageFileRadius(@NonNull ImageView imageview, @NonNull File imageFile, int radius) {
        Glide.with(mContext).load(imageFile).transform(new GlideRoundTransform(mContext, radius)).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载网络图片
     *
     * @param imageview 图片控件
     * @param url       图片url
     */
    public void setImageUrl(@NonNull ImageView imageview, @NonNull String url) {
        Glide.with(mContext).load(url).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载网络图片
     *
     * @param imageview 图片控件
     * @param drawable  图片Drawable
     */
    public void setImageDrawable(@NonNull ImageView imageview, @NonNull Drawable drawable) {
        Glide.with(mContext).load(drawable).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载网络图片
     *
     * @param imageview 图片控件
     * @param imageFile 本地图片文件File
     */
    public void setImageFile(@NonNull ImageView imageview, @NonNull File imageFile) {
        Glide.with(mContext).load(imageFile).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载图片
     *
     * @param imageview 图片控件
     * @param uri       图片uri
     */
    public void setImageUri(@NonNull ImageView imageview, @NonNull Uri uri) {
        Glide.with(mContext).load(uri).crossFade().into(imageview);
    }

    /**
     * 淡入淡出加载圆形图片
     *
     * @param imageview        图片控件
     * @param url              图片url
     * @param errorResId       加载失败时显示的图片资源id
     * @param placeholderResId 加载时的占位图资源id
     */
    public void setCircleImage(@NonNull ImageView imageview, @NonNull String url, @DrawableRes int errorResId, @DrawableRes int placeholderResId) {
        Glide.with(mContext)
                .load(url)
                .transform(new GlideCircleTransform(mContext))
                .crossFade()
                .error(errorResId)
                .placeholder(placeholderResId)
                .into(imageview);
    }

    /**
     * 淡入淡出加载指定圆角大小图片
     *
     * @param imageview        图片控件
     * @param url              图片url
     * @param radius           圆角大小,单位dp
     * @param errorResId       加载失败时显示的图片资源id
     * @param placeholderResId 加载时的占位图资源id
     */
    public void setImageRadius(@NonNull ImageView imageview, String url, int radius, @DrawableRes int errorResId, @DrawableRes int placeholderResId) {
        Glide.with(mContext)
                .load(url)
                .transform(new GlideRoundTransform(mContext, radius))
                .crossFade()
                .error(errorResId)
                .placeholder(placeholderResId)
                .into(imageview);
    }

    /**
     * 淡入淡出加载网络图片
     *
     * @param imageview        图片控件
     * @param url              图片url
     * @param errorResId       加载失败时显示的图片资源id
     * @param placeholderResId 加载时的占位图资源id
     */
    public void setImageUrl(@NonNull ImageView imageview, String url, @DrawableRes int errorResId, @DrawableRes int placeholderResId) {
        Glide.with(mContext)
                .load(url)
                .crossFade()
                .error(errorResId)
                .placeholder(placeholderResId)
                .into(imageview);
    }

    /**
     * 淡入淡出加载图片
     *
     * @param imageview        图片控件
     * @param uri              图片uri
     * @param errorResId       加载失败时显示的图片资源id
     * @param placeholderResId 加载时的占位图资源id
     */
    public void setImageUri(@NonNull ImageView imageview, Uri uri, @DrawableRes int errorResId, @DrawableRes int placeholderResId) {
        Glide.with(mContext)
                .load(uri)
                .crossFade()
                .error(errorResId)
                .placeholder(placeholderResId)
                .into(imageview);
    }

    /**
     * 设置点击监听事件
     *
     * @param v
     */
    public void setClick(@NonNull View... v) {
        for (int i = 0; i < v.length; i++) {
            v[i].setOnClickListener(this);
        }
    }

    /**
     * 设置控件背景透明度
     *
     * @param v     控件
     * @param alpha 透明度,范围在0-255之间
     */
    public void setAlpha(@NonNull View v, @IntRange(from = 0, to = 255) int alpha) {
        v.getBackground().setAlpha(alpha);
    }

    /**
     * 设置控件宽度
     *
     * @param v     控件
     * @param width 宽度,MATCH_PARENT = -1, WRAP_CONTENT = -2;
     */
    public void setWidth(@NonNull View v, int width) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = width;
        v.setLayoutParams(lp);
    }

    /**
     * 设置控件高度
     *
     * @param v      控件
     * @param height 高度,MATCH_PARENT = -1, WRAP_CONTENT = -2;
     */
    public void setHeight(@NonNull View v, int height) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = height;
        v.setLayoutParams(lp);
    }

    /**
     * 设置控件宽度高度
     *
     * @param v      控件
     * @param width  宽度,MATCH_PARENT = -1, WRAP_CONTENT = -2;
     * @param height 高度,MATCH_PARENT = -1, WRAP_CONTENT = -2;
     */
    public void setHeight(@NonNull View v, int width, int height) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = height;
        lp.width = width;
        v.setLayoutParams(lp);
    }

    /**
     * 获取输入框文本
     *
     * @param eitText 输入框
     * @return
     */
    public String text(@NonNull EditText eitText) {
        String text = eitText.getText().toString().trim();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    /**
     * 获取文本控件文本
     *
     * @param textview 文本控件
     * @return
     */
    public String text(@NonNull TextView textview) {
        String text = textview.getText().toString().trim();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    /**
     * 获取文本控件文本
     *
     * @param button 按钮控件
     * @return
     */
    public String text(@NonNull Button button) {
        String text = button.getText().toString().trim();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    /**
     * @param time   毫秒数
     * @param format 输出格式,如yyyy-MM-dd HH:mm:ss
     * @return format格式如yyyy-MM-dd HH:mm:ss字符串
     */
    public String formartTimeWithDate(long time, String format) {
        return DateTimeUtils.formartTimeWithDate(time, format);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 透明度,在0.0~1.0之间
     */
    public void backgroundAlpha(@FloatRange(from = 0.0, to = 1.0) float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * 得到Drawable资源
     *
     * @param resId 资源id
     * @return Drawable
     */
    public Drawable getDrawableRes(@DrawableRes int resId) {
        return ContextCompat.getDrawable(mContext, resId);
    }

    /**
     * 得到color int值
     *
     * @param colorResId color资源id
     * @return 得到color int值
     */
    @ColorInt
    public int getColorInt(@ColorRes int colorResId) {
        return ContextCompat.getColor(mContext, colorResId);
    }

    /**
     * Log打印json字符串
     *
     * @param jsonStr json字符串
     */
    public void json(String jsonStr) {
        ZeusLog.printJsonStr(logTag, jsonStr);
    }

    /**
     * 指定Tag Log打印json字符串
     *
     * @param tag     Tag
     * @param jsonStr json字符串
     */
    public void json(String tag, String jsonStr) {
        ZeusLog.printJsonStr(tag, jsonStr);
    }

    public void i(Object text) {
        ZeusLog.i(logTag, text);
    }

    public void i(String tag, Object text) {
        ZeusLog.i(tag, text);
    }

    public void e(Object text) {
        ZeusLog.e(logTag, text);
    }

    public void e(String tag, Object text) {
        ZeusLog.e(tag, text);
    }

    public void w(Object text) {
        ZeusLog.w(logTag, text);
    }

    public void w(String tag, Object text) {
        ZeusLog.w(tag, text);
    }

    public void d(Object text) {
        ZeusLog.d(logTag, text);
    }

    public void d(String tag, Object text) {
        ZeusLog.d(tag, text);
    }

    public void a(Object text) {
        ZeusLog.a(logTag, text);
    }

    public void a(String tag, Object text) {
        ZeusLog.a(tag, text);
    }

    public void v(Object text) {
        ZeusLog.v(logTag, text);
    }

    public void v(String tag, Object text) {
        ZeusLog.v(tag, text);
    }


}
