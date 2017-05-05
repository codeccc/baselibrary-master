package com.wb.base.widget.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Author: 王博  PC:MX
 * Time: 2016/12/21 13:31
 * Email: wb1276831936@163.com
 * Instruction: 解决Scrollview嵌套GridView,GridView高度只有一行的问题
 */
public class ScrollGridview extends GridView {
    public ScrollGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollGridview(Context context) {
        super(context);
    }

    public ScrollGridview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
