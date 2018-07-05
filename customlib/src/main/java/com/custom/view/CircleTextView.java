package com.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * 圆点数字布局
 * 效果：默认红色的圆点，中间显示数字/字符
 * 场景：列表前面的索引，1,2, 3, 4, 5
 * */
public class CircleTextView extends AppCompatTextView {

    private int fillColor = Color.RED;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int max = Math.max(measuredWidth, measuredHeight);
        setMeasuredDimension(max, max);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getWidth();
        int radius = Math.min(width, height) / 2;
        Paint fillPaint = new Paint();
        fillPaint.setColor(fillColor);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, radius, fillPaint);
        super.onDraw(canvas);
    }

    public void setFillColor(int color){
        this.fillColor = color;
        invalidate();
    }
}