package com.apr.interview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.apr.interview.R;

public class TestView extends View {
    Paint paint;
    String mText;
    int mTextSize;
    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TestView);
        mText = ta.getString(R.styleable.TestView_android_text);
        mTextSize = ta.getDimensionPixelSize(R.styleable.TestView_android_textSize,24);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setTextSize(mTextSize);
        paint.setTextAlign(Paint.Align.CENTER);

    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); /**resultW 代表最终设置的宽，resultH 代表最终设置的高*/
        int resultW = widthSize;
        int resultH = heightSize;
        int contentW = 0;
        int contentH = 0;

        if (widthMode == MeasureSpec.AT_MOST){
            if (!TextUtils.isEmpty(mText)) {
                contentW = (int) paint.measureText(mText);
                contentW += getPaddingLeft()+getPaddingRight();
                resultW = contentW < widthSize ? contentW : widthSize;
            }

        }
        if (heightMode == MeasureSpec.AT_MOST){
            if (!TextUtils.isEmpty(mText)) {
                contentH = mTextSize;
                contentH += getPaddingBottom()+getPaddingTop();
                resultH = contentH < heightSize ? contentH : heightSize;
            }

        }
        setMeasuredDimension(resultW,resultH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),getHeight()/2,getHeight()/2,paint);
        int cx = getPaddingLeft();
        int cy = getPaddingTop() + mTextSize;
        Paint.FontMetrics metrics = paint.getFontMetrics();
        cy += metrics.descent;

        canvas.drawText(mText,cx,cy,paint);
    }
}
