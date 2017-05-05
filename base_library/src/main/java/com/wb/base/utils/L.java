package com.wb.base.utils;

import xsf.zeuslibrary.zeusLog.ZeusLog;

/**
 * Created by 樱花洛 on 2017-05-05.
 */

public class L {

    public static final String TAG = "ZEUSLOG";
    
    /**
     * Log打印json字符串
     *
     * @param jsonStr json字符串
     */
    public static void json(String jsonStr) {
        ZeusLog.printJsonStr(TAG, jsonStr);
    }

    /**
     * 指定Tag Log打印json字符串
     *
     * @param tag     Tag
     * @param jsonStr json字符串
     */
    public static void json(String tag, String jsonStr) {
        ZeusLog.printJsonStr(tag, jsonStr);
    }

    public static void i(Object text) {
        ZeusLog.i(TAG, text);
    }

    public static void i(String tag, Object text) {
        ZeusLog.i(tag, text);
    }

    public static void e(Object text) {
        ZeusLog.e(TAG, text);
    }

    public static void e(String tag, Object text) {
        ZeusLog.e(tag, text);
    }

    public static void w(Object text) {
        ZeusLog.w(TAG, text);
    }

    public static void w(String tag, Object text) {
        ZeusLog.w(tag, text);
    }

    public static void d(Object text) {
        ZeusLog.d(TAG, text);
    }

    public static void d(String tag, Object text) {
        ZeusLog.d(tag, text);
    }

    public static void a(Object text) {
        ZeusLog.a(TAG, text);
    }

    public static void a(String tag, Object text) {
        ZeusLog.a(tag, text);
    }

    public static void v(Object text) {
        ZeusLog.v(TAG, text);
    }

    public static void v(String tag, Object text) {
        ZeusLog.v(tag, text);
    }
}
