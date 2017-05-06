package com.wb.base.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.wb.base.BaseActivity;

public class MainActivity extends BaseActivity {

    /**
     * 设置当前Activity的屏幕方向
     * setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     */
    @Override
    protected void setRequestedOrientation() {

    }

    @Override
    public void onCreate(Bundle bundle, Object o) {
        setContentView(getLayoutId());
    }

    @NonNull
    @Override
    protected int getLayoutId() {
        return 0;
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
