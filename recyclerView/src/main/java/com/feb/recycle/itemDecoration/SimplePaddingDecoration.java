package com.feb.recycle.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

/**
 * Created by Administrator on 2018/2/12.
 */

public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {
    public SimplePaddingDecoration(Context context) {
        dividerHeight = 50;
        dividerPaint = new Paint();
        dividerPaint.setColor(Color.parseColor("#123478"));

        textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(30);
        fontMetrics = new Paint.FontMetrics();
        textPaint.getFontMetrics(fontMetrics);
        textPaint.setTextAlign(Paint.Align.CENTER);

    }

    private int dividerHeight;
    private Paint dividerPaint;
    private TextPaint textPaint;
    private Paint.FontMetrics fontMetrics;

    @Override//设置item四周的margin
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);

        outRect.top = 50;//类似item上方的margin
        outRect.left = 10;//margin_left
        outRect.right = 10;//margin_right
        outRect.bottom = 50;//margin_bottom
        //view.setBackgroundColor(Color.parseColor("#78dd33"));//设置item背景颜色
    }

    @Override//可以实现类似绘制背景的效果，内容在上面
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        textPaint.setColor(Color.BLACK);
        dividerPaint.setColor(Color.WHITE);
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + 5;
            textPaint.setTextAlign(Paint.Align.CENTER);
            //在item的上方绘制文字,fontMetrics.bottom指的是最下字符到baseline的值，即descent的最大值,这样文字的最下边也在item上方
            c.drawText("teat",view.getLeft()+20,view.getTop()-fontMetrics.bottom,textPaint);

            //c.drawRect(left, top, right, bottom, dividerPaint);//在item的下方绘制矩形
        }

    }

    @Override//可以绘制在内容的上面，覆盖内容
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setColor(Color.RED);
        dividerPaint.setColor(Color.BLUE);
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            //float top = view.getTop()-dividerHeight;
            float bottom = view.getBottom();
            float top = Math.max(dividerHeight, view.getTop());//在item 的上方绘制50px高的矩形,在滑动过程中item 的top到达矩形的上边缘时，将矩形固定在最上方，即0~50px的地方,直到被下一个item滑上来覆盖
            if (bottom<top){
                top = bottom;//当滑动过程中item 的bottom到达矩形的下边缘，即item的内容已经被矩形全部覆盖后，使矩形向上滑动
            }
            //c.drawRect(left, top, right, bottom, dividerPaint);
            c.drawRect(left, top - dividerHeight, right, top, dividerPaint);
            c.drawText("hijk", left, top - fontMetrics.bottom, textPaint);

        }
    }

}
