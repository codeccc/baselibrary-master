package com.wb.base.adapter.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.wb.base.adapter.ClickCallback;
import com.wb.base.adapter.recyclerview.base.ItemViewDelegate;
import com.wb.base.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class RcyCommonAdapter<T> extends MultiItemTypeAdapter<T> implements View.OnClickListener {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    ClickCallback mClickCallback;

    public RcyCommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                RcyCommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    public RcyCommonAdapter(final Context context, final int layoutId, List<T> datas, ClickCallback clickCallback) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
        mClickCallback = clickCallback;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                RcyCommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);

}
