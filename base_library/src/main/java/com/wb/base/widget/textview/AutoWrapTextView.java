package com.wb.base.widget.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.wb.base.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:chenpengfei88
 * Github:FancyOnePoint
 * PC:MX
 * source from:https://github.com/chenpengfei88/AutoWrapTextView
 * Time: 2017/4/18 9:46
 * Email: wb1276831936@163.com
 * Instruction:解决中英文混排参差不齐的问题
 */
public class AutoWrapTextView extends View {

    //文本画笔
    TextPaint mTextPaint;

    //左边边距
    private int mPaddingLeft = 0;
    //右边边距
    private int mPaddingRight = 0;
    //顶部边距
    private int mPaddingTop = 0;
    //底部边距
    private int mPaddingBottom = 0;
    //字体大小
    private int mTextSize = 50;
    //字体颜色
    private int mTextColor = 0x000000;
    //行间距
    private int mLineSpacingExtra = 7;

    /**
     * 文本Char数组
     */
    private char[] mTextCharArray;

    /**
     * 单行文本显示的宽度
     */
    private int mSingleTextWidth;

    /**
     * 拆分后的文本集合
     */
    private List<String> mSplitTextList;

    /**
     * 分割后的rect数组
     */
    private Rect[] mSplitTextRectArray = null;

    public AutoWrapTextView(Context context)
    {
        super(context);
    }

    public AutoWrapTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        init(context, attrs);
    }

    public AutoWrapTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AutoWrapTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        initStyle(context, attrs);
        initPaint();
    }

    private void initStyle(Context context, AttributeSet attrs)
    {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoWrapTextViewStyle);
        mPaddingLeft = typedArray.getDimensionPixelSize(R.styleable.AutoWrapTextViewStyle_paddingLeft, 0);
        mPaddingRight = typedArray.getDimensionPixelOffset(R.styleable.AutoWrapTextViewStyle_paddingRight, 0);
        mPaddingTop = typedArray.getDimensionPixelOffset(R.styleable.AutoWrapTextViewStyle_paddingTop, 0);
        mPaddingBottom = typedArray.getDimensionPixelOffset(R.styleable.AutoWrapTextViewStyle_paddingBottom, 0);

        mTextColor = typedArray.getColor(R.styleable.AutoWrapTextViewStyle_textColor, Color.BLACK);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.AutoWrapTextViewStyle_textSize, 50);
        mLineSpacingExtra = typedArray.getInteger(R.styleable.AutoWrapTextViewStyle_lineSpacingExtra, 7);
        typedArray.recycle();
    }

    private void initPaint()
    {
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    /**
     * 设置文本
     *
     * @param text 文本
     */
    public void setText(String text)
    {
        if (TextUtils.isEmpty(text))
        {
            return;
        }
        mTextCharArray = text.toCharArray();
        requestLayout();
    }

    /**
     * 拆分文本
     *
     * @param heightMode
     */
    private void splitText(int heightMode)
    {
        if (mTextCharArray == null)
        {
            return;
        }
        mSplitTextList = new ArrayList<>();
        mSingleTextWidth = getMeasuredWidth() - mPaddingLeft - mPaddingRight;
        int currentSingleTextWidth = 0;
        StringBuilder lineStringBuffer = new StringBuilder();
        for (int i = 0, length = mTextCharArray.length; i < length; i++)
        {
            char textchar = mTextCharArray[i];
            currentSingleTextWidth += getSingleCharWidth(textchar);
            if (currentSingleTextWidth > mSingleTextWidth)
            {
                mSplitTextList.add(lineStringBuffer.toString());
                lineStringBuffer = new StringBuilder();
                currentSingleTextWidth = 0;
                i--;
            } else
            {
                lineStringBuffer.append(textchar);
                if (i == length - 1)
                {
                    mSplitTextList.add(lineStringBuffer.toString());
                }
            }
        }
        int textHeight = 0;
        mSplitTextRectArray = new Rect[mSplitTextList.size()];
        for (int m = 0, length = mSplitTextList.size(); m < length; m++)
        {
            String lineText = mSplitTextList.get(m);
            Rect lineTextRect = new Rect();
            mTextPaint.getTextBounds(lineText, 0, lineText.length(), lineTextRect);
            if (heightMode == MeasureSpec.AT_MOST)
            {
                textHeight += (lineTextRect.height() + mLineSpacingExtra);
                if (m == length - 1)
                {
                    textHeight = textHeight + mPaddingBottom + mPaddingTop;
                }
            } else
            {
                if (textHeight == 0)
                {
                    textHeight = getMeasuredHeight();
                }
            }
            mSplitTextRectArray[m] = lineTextRect;
        }
        setMeasuredDimension(getMeasuredWidth(), textHeight);
    }

    /**
     * 得到单个char的宽度
     *
     * @param textchar char
     * @return 单个char的宽度
     */
    private float getSingleCharWidth(char textchar)
    {
        float[] width = new float[1];
        mTextPaint.getTextWidths(new char[]{textchar}, 0, 1, width);
        return width[0];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        splitText(MeasureSpec.getMode(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawText(canvas);
    }

    /**
     * 绘制文本
     *
     * @param canvas 画布
     */
    public void drawText(Canvas canvas)
    {
        if (mSplitTextList == null || mSplitTextList.size() == 0)
        {
            return;
        }
        int marginTop = getTopTextMargin();
        for (int m = 0, length = mSplitTextList.size(); m < length; m++)
        {
            String lineText = mSplitTextList.get(m);
            canvas.drawText(lineText, mPaddingLeft, marginTop, mTextPaint);
            marginTop += (mSplitTextRectArray[m].height() + mLineSpacingExtra);
        }
    }

    public int getTopTextMargin()
    {
        return mSplitTextRectArray[0].height() / 2 + mPaddingTop + getFontSpace();
    }

    private int getFontSpace()
    {
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        return (fontMetricsInt.descent - fontMetricsInt.ascent) / 2 - fontMetricsInt.descent;
    }
}
