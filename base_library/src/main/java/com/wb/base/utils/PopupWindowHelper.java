package com.wb.base.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.wb.base.R;


/**
 * Time: 2016/12/21 16:12
 * Instruction: PopupWindow简化使用工具类
 */
public class PopupWindowHelper {

    private View popupView;
    private PopupWindow mPopupWindow;
    private OnDismissListener mOnDismissListener;
    public static final int TYPE_WRAP_CONTENT = 0, TYPE_MATCH_PARENT = 1;
    private boolean isCancelable = true;

    public PopupWindowHelper(View view, boolean isCancelable, int type)
    {
        popupView = view;
        this.isCancelable = isCancelable;
        switch (type)
        {
            case TYPE_WRAP_CONTENT:
                initPopupWindow(TYPE_WRAP_CONTENT);
                break;
            case TYPE_MATCH_PARENT:
                initPopupWindow(TYPE_MATCH_PARENT);
                break;
        }
    }

    public void showAsDropDown(View anchor)
    {
        showAsDropDown(anchor, 0, 0);
    }

    public void showAsDropDown(View anchor, int xoff, int yoff)
    {
        mPopupWindow.showAsDropDown(anchor, xoff, yoff);
    }

    public void showAtLocation(View parent, int gravity, int x, int y)
    {
        mPopupWindow.showAtLocation(parent, gravity, x, y);
    }

    public void dismiss()
    {
        if (mPopupWindow.isShowing())
        {
            mPopupWindow.dismiss();
        }
    }

    public void showAsPopUp(View anchor)
    {
        showAsPopUp(anchor, 0, 0);
    }

    public void showAsPopUp(View anchor, int xoff, int yoff)
    {
        mPopupWindow.setAnimationStyle(R.style.AnimationUpPopup);
        popupView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = popupView.getMeasuredHeight();
        int[] location = new int[2];
        anchor.getLocationInWindow(location);
        mPopupWindow.showAtLocation(anchor, Gravity.LEFT | Gravity.TOP, location[0] + xoff, location[1] - height + yoff);
    }

    /**
     * 这里type传1
     *
     * @param anchor
     */
    public void showFromBottom(View anchor)
    {
        initPopupWindow(TYPE_MATCH_PARENT);
        mPopupWindow.setAnimationStyle(R.style.AnimationFromButtom);
        mPopupWindow.showAtLocation(anchor, Gravity.LEFT | Gravity.BOTTOM, 0, 0);
    }

    /**
     * 这里type传1
     *
     * @param anchor
     */
    public void showFromTop(View anchor)
    {
        mPopupWindow.setAnimationStyle(R.style.AnimationFromTop);
        mPopupWindow.showAtLocation(anchor, Gravity.LEFT | Gravity.TOP, 0, getStatusBarHeight());
    }

    /**
     * touch outside dismiss the popupwindow, default is ture
     *
     * @param isCancelable
     */
    public void setCancelable(boolean isCancelable)
    {
        this.isCancelable = isCancelable;
    }

    public void initPopupWindow(int type)
    {
        if (type == TYPE_WRAP_CONTENT)
        {
            mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        } else if (type == TYPE_MATCH_PARENT)
        {
            mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
//        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //适配底部虚拟键
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (isCancelable)
        {
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);
        } else
        {
            mPopupWindow.setOutsideTouchable(false);
            mPopupWindow.setFocusable(false);
        }
        mPopupWindow.update();
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss()
            {
                if (mOnDismissListener != null)
                {
                    mOnDismissListener.onDismiss();
                }
            }
        });
    }

    private int getStatusBarHeight()
    {
        return Math.round(25 * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Sets the listener to be called when the window is dismissed.
     *
     * @param onDismissListener The listener.
     */
    public void setOnDismissListener(OnDismissListener onDismissListener)
    {
        mOnDismissListener = onDismissListener;
    }

    public PopupWindow getPopupWindow()
    {
        return mPopupWindow;
    }

    /**
     * Listener that is called when this popup window is dismissed.
     */
    public interface OnDismissListener {
        /**
         * Called when this popup window is dismissed.
         */
        public void onDismiss();
    }


}
