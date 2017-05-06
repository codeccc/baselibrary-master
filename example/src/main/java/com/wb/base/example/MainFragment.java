package com.wb.base.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wb.base.BaseFragment;

/**
 * Created by Administrator on 2017-05-06.
 */

public class MainFragment extends BaseFragment {
    @NonNull
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup group, @Nullable Bundle bundle, Object o) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle, Object o) {

    }

    @NonNull
    @Override
    public String setLogTag() {
        return null;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLoadData() {

    }

    @Override
    public void showResult(Object o) {

    }

    @Override
    public void widgetClick(View view) {

    }
}
