package com.news.ui.design.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.custom.view.WaterView;
import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;

import butterknife.BindView;

public class WaterActivity extends BaseSwipeBackActivity {


    @BindView(R.id.mv)
    WaterView mv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_water_view;
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
        mv.change(200);
        mv.moveWaterLine();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.change(200);
            }
        });
        mv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.change(200);
            }
        });
        mv.setOnAngleColorListener(onAngleColorListener);
    }

    private WaterView.OnAngleColorListener onAngleColorListener = new WaterView.OnAngleColorListener() {
        @Override
        public void onAngleColorListener(int red, int green) {
            Color color = new Color();
            int c = Color.argb(150, red, green, 0);
            ll.setBackgroundColor(c);
        }
    };
}
