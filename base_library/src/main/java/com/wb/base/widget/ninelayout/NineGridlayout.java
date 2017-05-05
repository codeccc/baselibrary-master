package com.wb.base.widget.ninelayout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wb.base.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pan_ on 2015/2/2.
 */
public class NineGridlayout extends ViewGroup {

    /**
     * 图片之间的间隔
     */
    private int gap = 10;
    private int columns;//
    private int rows;//
    private List listData;
    private int totalWidth;

    boolean isFirstPicBigView = true;//设置是否只有一张图片是否大图浏览

    List<RatioImageView> mImageViewList;

    NineGridLayoutClickListener mClickListener;

    public NineGridlayout(Context context) {
        super(context);
    }

    public NineGridlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ScreenTools screenTools = ScreenTools.instance(getContext());
        totalWidth = screenTools.getScreenWidth() - screenTools.dip2px(80);
        mImageViewList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void layoutChildrenView() {
        int childrenCount = listData.size();
        int singleWidth = (totalWidth - gap * (3 - 1)) / 3;
        int singleHeight = singleWidth;
        if (isFirstPicBigView) {
            if (listData.size() == 1) {//单图图片宽高 3:4
                singleWidth = (int) (singleWidth * 1.5);
                singleHeight = singleHeight * 2;
            }
            if (listData.size() == 4 || listData.size() == 2) {//2X2 图片放大 1.3 倍
                singleWidth = (int) (singleWidth * 1.3);
                singleHeight = singleWidth;
            }
        }

        //根据子view数量确定高度
        LayoutParams params = getLayoutParams();
        params.height = singleHeight * rows + gap * (rows - 1);
        setLayoutParams(params);

        for (int i = 0; i < childrenCount; i++) {
            RatioImageView childrenView = (RatioImageView) getChildAt(i);
            childrenView.setImageUrl(((Image) listData.get(i)).getUrl() == null ? "" : ((Image) listData.get(i)).getUrl());
            int[] position = findPosition(i);
            int left = (singleWidth + gap) * position[1];
            int top = (singleHeight + gap) * position[0];
            int right = left + singleWidth;
            int bottom = top + singleHeight;

            childrenView.layout(left, top, right, bottom);
            childrenView.setTag(R.id.position, i);
            mImageViewList.add(childrenView);
        }

    }


    private int[] findPosition(int childNum) {
        int[] position = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i * columns + j) == childNum) {
                    position[0] = i;//行
                    position[1] = j;//列
                    break;
                }
            }
        }
        return position;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public boolean isFirstPicBigView() {
        return isFirstPicBigView;
    }

    public void setFirstPicBigView(boolean firstPicBigView) {
        isFirstPicBigView = firstPicBigView;
    }

    public void setImagesData(List<Image> lists) {
        if (lists == null || lists.isEmpty()) {
            return;
        }
        //初始化布局
        generateChildrenLayout(lists.size());
        //这里做一个重用view的处理
        if (listData == null) {
            int i = 0;
            while (i < lists.size()) {
                RatioImageView iv = generateImageView();
                addView(iv, generateDefaultLayoutParams());
                i++;
            }
        } else {
            int oldViewCount = listData.size();
            int newViewCount = lists.size();
            if (oldViewCount > newViewCount) {
                removeViews(newViewCount - 1, oldViewCount - newViewCount);
            } else if (oldViewCount < newViewCount) {
                for (int i = 0; i < newViewCount - oldViewCount; i++) {
                    RatioImageView iv = generateImageView();
                    addView(iv, generateDefaultLayoutParams());
                }
            }
        }
        listData = lists;
        layoutChildrenView();
    }


    /**
     * 根据图片个数确定行列数量
     * 对应关系如下
     * num	row	column
     * 1	   1	1
     * 2	   1	2
     * 3	   1	3
     * 4	   2	2
     * 5	   2	3
     * 6	   2	3
     * 7	   3	3
     * 8	   3	3
     * 9	   3	3
     *
     * @param length
     */
    private void generateChildrenLayout(int length) {
        if (length <= 3) {
            rows = 1;
            columns = length;
        } else if (length <= 6) {
            rows = 2;
            columns = 3;
            if (length == 4) {
                columns = 2;
            }
        } else {
            rows = 3;
            columns = 3;
        }
    }

    private RatioImageView generateImageView() {
        RatioImageView iv = new RatioImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onImageViewClicked(NineGridlayout.this, (RatioImageView) v, (Integer) v.getTag(R.id.position));
                }
            }
        });
        iv.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onImageViewLongClicked(NineGridlayout.this, (RatioImageView) v, (Integer) v.getTag(R.id.position));
                }
                return true;
            }
        });
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
        return iv;
    }

    /**
     * 改变指定位置的Imageview显示的图片
     *
     * @param pos 位置
     */
    public void notifyDataSetChanged(int pos) {
        removeViews(pos, 1);
        RatioImageView iv = generateImageView();
        addView(iv, generateDefaultLayoutParams());
    }

    public List getListData() {
        return listData;
    }

    public void setOnImageClickListener(NineGridLayoutClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface NineGridLayoutClickListener {
        void onImageViewClicked(NineGridlayout nineGridlayout, RatioImageView imageView, int pos);

        void onImageViewLongClicked(NineGridlayout nineGridlayout, RatioImageView imageView, int pos);
    }

}
