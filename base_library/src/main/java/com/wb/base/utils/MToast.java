package com.wb.base.utils;

import android.widget.Toast;

/**
 * Created by Maibenben on 2016/11/3.
 */

public class MToast {

    private MToast() {
    }

    /**
     * Toast短暂提示
     *
     * @param content 提示内容
     */
    public static void shortToast(String content) {
        DisplayToast.getInstance().display(content, Toast.LENGTH_SHORT);
    }

    /**
     * Toast长提示
     *
     * @param content 提示内容
     */
    public static void longToast(String content) {
        DisplayToast.getInstance().display(content, Toast.LENGTH_LONG);
    }

    /**
     * Toast自定义提示时间
     *
     * @param content  提示内容
     * @param duration 显示时间
     */
    public static void timeToast(String content, int duration) {
        DisplayToast.getInstance().display(content, duration);
    }

    /**
     * Toast短暂提示
     *
     * @param resId 从资源文件中获取提示文本
     */
    public static void shortToast(int resId) {
        DisplayToast.getInstance().display(resId, Toast.LENGTH_SHORT);
    }

    /**
     * Toast长提示
     *
     * @param resId 从资源文件中获取提示文本
     */
    public static void longToast(int resId) {
        DisplayToast.getInstance().display(resId, Toast.LENGTH_LONG);
    }

    /**
     * Toast自定义提示时间
     *
     * @param resId 从资源文件中获取提示文本
     */
    public static void timeToast(int resId, int duration) {
        DisplayToast.getInstance().display(resId, duration);
    }

    /**
     * 取消Toast
     */
    public static void cancel() {
        DisplayToast.getInstance().dismiss();
    }

}
