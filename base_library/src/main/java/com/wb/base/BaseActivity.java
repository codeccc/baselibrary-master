package com.wb.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wb.base.transform.GlideCircleTransform;
import com.wb.base.transform.GlideRoundTransform;
import com.wb.base.utils.AppManager;
import com.wb.base.utils.DateTimeUtils;
import com.wb.base.utils.MToast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import xsf.zeuslibrary.zeusLog.ZeusLog;

/**
 * Author: 王博
 * Date: 2016/12/12
 * Email: wb1276831936@163.com
 * Instruction: 所有Activity的基类.
 * StatusLayoutManager用法:http://www.jianshu.com/p/9d53893b3eda
 */
public abstract class BaseActivity extends AutoLayoutActivity implements View.OnClickListener {

    public Context mContext;

    /**
     * Activity的rusume状态.
     */
    private static final int ACTIVITY_RESUME = 0;
    /**
     * Activity的stop状态.
     */
    private static final int ACTIVITY_STOP = 1;
    /**
     * Activity的pause状态.
     */
    private static final int ACTIVITY_PAUSE = 2;
    /**
     * Activity的destory状态.
     */
    private static final int ACTIVITY_DESTROY = 3;
    /**
     * 当前activity状态.
     */
    private int activityState;

    /**
     * 是否允许全屏.
     */
    private boolean mAllowFullScreen = true;

    /**
     * 双击退出间隔时间.
     */
    private static final int DELAY_MILLS = 1500;

    public Map<String, String> params = new HashMap<>();
    public Map<String, String> headers = new HashMap<>();
    public String url = "";
    public int page = 1;

    /**
     * 当前activity的log日志的tag
     */
    @NonNull
    private String logTag = "";

    /**
     * onCreate
     *
     * @param savedInstanceState 状态保存.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        //最终方案
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 全透明实现
            //getWindow.setStatusBarColor(Color.TRANSPARENT)
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mContext = this;

        AppManager.getAppManager().addActivity(this);

        onCreate(savedInstanceState, "");

        logTag = setLogTag();//设置log日志tag

    }

    /**
     * onResume.
     */
    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
    }

    /**
     * onPause.
     */
    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
    }

    /**
     * onStop.
     */
    @Override
    protected void onStop() {
        super.onResume();
        activityState = ACTIVITY_STOP;
    }

    /**
     * onDestroy.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = ACTIVITY_DESTROY;
    }

    /**
     * 设置屏幕方向
     * example:
     * setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     *
     * @return
     */
    protected abstract void setRequestedOrientation();

    /**
     * 执行Activity的OnCreate方法.
     *
     * @param savedInstanceState 状态保存
     * @param obj                拓展字段,仅方便自动重写onCreate
     */
    public abstract void onCreate(Bundle savedInstanceState, Object obj);


    /**
     * 设置布局文件id
     *
     * @return 布局R文件int值
     */
    @LayoutRes
    @NonNull
    protected abstract int getLayoutId();

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
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 跳转到指定Activity ,从右往左进入.
     *
     * @param clazz activity类
     */
    public void goToActivityWithAnim(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }

    /**
     * 跳转到指定Activity ,指定进入退出动画.
     *
     * @param clazz activity类
     */
    public void goToActivityWithAnim(Class clazz, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
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
     * 设置是否允许全屏
     *
     * @param allowFullScreen 是否允许全屏
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * 是否退出.
     */
    private static Boolean isExit = false;

    /**
     * 双击退出函数.
     */
    public void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            MToast.shortToast(getResources().getString(R.string.double_click_to_exit));
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, DELAY_MILLS); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
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
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
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
