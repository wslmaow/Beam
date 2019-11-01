package com.apr.interview.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class TestViewGroup extends ViewGroup {
    public TestViewGroup(Context context) {
        this(context,null);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override public LayoutParams generateLayoutParams(AttributeSet attrs) { //只关心子元素的 margin 信息，所以这里用
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); /**resultW 代表最终设置的宽，resultH 代表最终设置的高*/
        int resultW = widthSize;
        int resultH = heightSize; /**计算尺寸的时候要将自身的 padding 考虑进去*/
        int contentW = getPaddingLeft() + getPaddingRight();
        int contentH = getPaddingTop() + getPaddingBottom();
        measureChildren(widthMeasureSpec, heightMeasureSpec);/**对子元素进行尺寸的测量，这一步必不可少*/
        MarginLayoutParams layoutParams = null;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            layoutParams = (MarginLayoutParams) child.getLayoutParams(); //子元素不可见时，不参与布局，因此不需要将其尺寸计算在内
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            contentW += child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            if (contentW>heightSize){
                contentW = getPaddingLeft() + getPaddingRight()+child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                contentH += child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }

        }
        //重点处理 AT_MOST 模式，TestViewGroup 通过子元素的尺寸自主决定数值大小，但不能超过ViewGroup 给出的建议数值
        if (widthMode == MeasureSpec.AT_MOST) {
            resultW = contentW < widthSize ? contentW : widthSize;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            resultH = contentH < heightSize ? contentH : heightSize;
        } //一定要设置这个函数，不然会报错
        setMeasuredDimension(resultW, resultH);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int topStart = getPaddingTop();
        int leftStart = getPaddingLeft();
        int childW = 0;
        int childH = 0;
        MarginLayoutParams layoutParams = null;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            layoutParams = (MarginLayoutParams) child.getLayoutParams(); //子元素不可见时，不参与布局，因此不需要将其尺寸计算在内
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            childW = child.getMeasuredWidth();
            childH = child.getMeasuredHeight();
            leftStart += layoutParams.leftMargin;
            topStart += layoutParams.topMargin;

            if (leftStart + childW >getWidth()){
                leftStart = layoutParams.leftMargin;
                topStart += childH + layoutParams.bottomMargin;
            }

            child.layout(leftStart, topStart, leftStart + childW, topStart + childH);

            leftStart += childW + layoutParams.rightMargin;

        }
    }
}

