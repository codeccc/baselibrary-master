package com.wb.base.adapter;

import android.view.View;

/**
 * Created by Administrator on 2017-05-05.
 * 将RecyclerView/ListView/Gridview列表中控件的点击事件回调至Activity/Fragment中
 */

public interface ClickCallback {
    void click(View v);
}
