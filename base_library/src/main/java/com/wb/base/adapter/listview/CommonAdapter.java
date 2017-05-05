package com.wb.base.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wb.base.adapter.ClickCallback;
import com.wb.base.adapter.listview.base.ViewHolder;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter implements View.OnClickListener {

    public Context mContext;
    public List<T> mDatas;
    public LayoutInflater mInflater;
    private int layoutId;

    private ClickCallback mCallback;

    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    public CommonAdapter(Context context, int layoutId, List<T> datas, ClickCallback callback) {
        this.mContext = context;
        this.mDatas = datas;
        this.layoutId = layoutId;
        this.mCallback = callback;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {

        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutId, position);

        convert(holder, getItem(position));

        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);

}
