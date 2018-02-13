package com.jude.beamdemo.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.jude.beamdemo.ui.MyRecyclerViewActivity;

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
        textPaint.setTextAlign(Paint.Align.LEFT);

    }

    private int dividerHeight;
    private Paint dividerPaint;
    private TextPaint textPaint;
    private Paint.FontMetrics fontMetrics;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        outRect.top = dividerHeight;
        outRect.bottom = 5;
        view.setBackgroundColor(Color.parseColor("#78dd33"));
    }

    @Override
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
            c.drawText("huij",left,view.getTop()-fontMetrics.bottom,textPaint);
            c.drawRect(left, top, right, bottom, dividerPaint);
        }

    }

    @Override
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
            float top = view.getTop()-dividerHeight;
            float bottom = view.getBottom();
            float bottom2 = Math.max(dividerHeight, view.getTop());
            if (bottom<bottom2){
                bottom2 = bottom;
            }
            //c.drawRect(left, top, right, bottom, dividerPaint);
            c.drawRect(left, bottom2 - dividerHeight, right, bottom2, dividerPaint);
            c.drawText("huij", left, bottom2 - fontMetrics.bottom, textPaint);

        }
    }

}
