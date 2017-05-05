package com.wb.base.adapter.listview.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wb.base.transform.GlideCircleTransform;
import com.zhy.autolayout.utils.AutoUtils;


public class ViewHolder {

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        // TODO Auto-generated constructor stub
        this.mPosition = position;
        this.mContext = context;
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
        //对于listview，注意添加这一行，即可在item上使用高度
        AutoUtils.autoSize(mConvertView);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {

        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId 控件Id
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public int getPosition() {
        return mPosition;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public Context getmContext() {
        return mContext;
    }

    public ViewHolder setImageUri(Context context, int viewId, String url) {
        Glide.with(context).load(url).into((ImageView) getView(viewId));
        return this;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 加载本地图片
     *
     * @param viewId
     * @param resId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * 加载btimap图片
     *
     * @param viewId
     * @param bitmap
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 加载网络图片
     *
     * @param viewId 控件id
     * @param url    图片url
     * @param resId  资源id
     * @return
     */
    public ViewHolder setImageURI(int viewId, String url, int resId) {
        Glide.with(mContext).load(url).into((ImageView) getView(viewId)).onLoadFailed(null,
                ContextCompat.getDrawable(mContext, resId)
        );

        return this;
    }

    /**
     * 加载网络图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setCircleImageURI(int viewId, String url) {
        //noinspection deprecation
        Glide.with(mContext).load(url).transform(new GlideCircleTransform(mContext)).into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setTextColor(int viewId, int color) {
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }

}
