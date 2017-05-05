package com.wb.base.utils;

import android.widget.EditText;

/**
 * Author: Github:FancyOnePoint  PC:MX
 * Time: 2017/2/17 13:33
 * Email: wb1276831936@163.com
 * Instruction: please enter class instruction here
 */
public class EdittextUtil {
    /**
     * 设置EditText是否可编辑
     *
     * @param editText 要设置的EditText
     * @param value    可编辑:true 不可编辑:false
     * @author com.tiantian
     */
    public static void setEditTextEditable(EditText editText, boolean value)
    {
        if (value)
        {
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
        } else
        {
            editText.setFocusableInTouchMode(false);
            editText.clearFocus();
        }
    }
}
