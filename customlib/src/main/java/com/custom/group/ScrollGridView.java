package com.custom.group;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自适应的网格布局
 * 效果：随子项增加而逐步加载
 * 场景：动态添加子项
 * */
public class ScrollGridView extends GridView {
    public ScrollGridView(Context context) {
        super(context);
    }

    public ScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
