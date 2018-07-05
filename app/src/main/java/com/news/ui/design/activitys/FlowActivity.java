package com.news.ui.design.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.custom.group.FlowLayout;
import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;

import java.util.Random;

import butterknife.BindView;


public class FlowActivity extends BaseSwipeBackActivity {

    @BindView(R.id.add)
    Button mButton;
    @BindView(R.id.flowLayout)
    FlowLayout mFlowLayout;

    Random mRandom = new Random();
    String[] text = new String[]{
            "加油呀",
            "会努力学习Android",
            "会努力学习自定义View",
            "努力",
            "是一步一步的",
            "哈哈哈哈啊哈哈啊啊啊啊啊"
    };

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_flow;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int m = mRandom.nextInt(5);
                TextView textView = new TextView(FlowActivity.this);
                ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(mRandom.nextInt(10), mRandom.nextInt(20), mRandom.nextInt(10), mRandom.nextInt(15));
                textView.setBackgroundResource(R.drawable.flag_03);
                textView.setTextColor(Color.RED);
                textView.setLayoutParams(layoutParams);
                textView.setText(text[m]);
                mFlowLayout.addView(textView);
            }
        });
    }
}
