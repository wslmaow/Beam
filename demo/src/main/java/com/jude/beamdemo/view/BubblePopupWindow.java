package com.jude.beamdemo.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.beamdome.R;

/**
 * Created by Administrator on 2017/12/18.
 */

public class BubblePopupWindow extends PopupWindow {
    Context context;

    View contentView;
    TextView content;
    ImageView triangle;

    public BubblePopupWindow(Context context) {
       this.context=context;
       init();
    }

    public BubblePopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubblePopupWindow(View contentView) {
        super(contentView);
    }

    public BubblePopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public BubblePopupWindow() {

    }
    public void showAt(View parent,int gravity){
        int location[]=new int[2];
        parent.getLocationOnScreen(location);

        setContent(gravity);
        contentView=getContentView();
        content=(TextView) contentView.findViewById(R.id.tv_content);
        triangle=(ImageView) contentView.findViewById(R.id.iv_triangle);

        content.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        triangle.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        int x=location[0]+((parent.getWidth()-content.getMeasuredWidth())/2);
        int y=0;
        if (gravity==Gravity.TOP){
            y=location[1]-content.getMeasuredHeight();
        }
        if (gravity==Gravity.BOTTOM){
            y=location[1]+parent.getHeight()-triangle.getMeasuredHeight();
        }
        int x_end=x+content.getMeasuredWidth();
        if (x_end>getScreenWidth(context)||x<0){
            setTriangleMargins(parent,x,x_end);
        }
        setAnimationStyle(R.style.PopupAnimation);
        showAtLocation(parent, Gravity.NO_GRAVITY,x,y);
    }
    private void init(){
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(false);
        setOutsideTouchable(false);
    }
    void setContent(int gravity){
        View contentView=null;
        switch(gravity){
            case Gravity.TOP:
                contentView= LayoutInflater.from(context).inflate(R.layout.bubble_pop_up,null);
                break;
            case Gravity.BOTTOM:
                contentView= LayoutInflater.from(context).inflate(R.layout.bubble_pop_bottom,null);
                break;
            default:
                break;
        }
        setContentView(contentView);
    }
    int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager WM = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        WM.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
    void setTriangleMargins(View parent,int xStart,int xEnd){
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(triangle.getLayoutParams());
        RelativeLayout.LayoutParams parentLayoutParams=(RelativeLayout.LayoutParams)parent.getLayoutParams();
        if (xStart<0){
            int margin_left=parent.getWidth()/2-triangle.getMeasuredWidth()/2+parentLayoutParams.leftMargin;
            int margin_top=0;
            //int margin_right=content.getMeasuredWidth()-parent.getWidth()/2-triangle.getMeasuredWidth()/2-parentLayoutParams.leftMargin;
            int margin_right=0;
            int margin_bottom=0;
            params.setMargins(margin_left,margin_top,margin_right,margin_bottom);
        }
        if(xEnd>getScreenWidth(context)){
            int margin_left=content.getMeasuredWidth()-parent.getWidth()/2-triangle.getMeasuredWidth()/2-parentLayoutParams.rightMargin;
            int margin_top=0;
            //int margin_right=parent.getWidth()/2-triangle.getMeasuredWidth()/2+parentLayoutParams.rightMargin;
            int margin_right=0;
            int margin_bottom=0;
            params.setMargins(margin_left,margin_top,margin_right,margin_bottom);
        }
        triangle.setLayoutParams(params);
    }
}
