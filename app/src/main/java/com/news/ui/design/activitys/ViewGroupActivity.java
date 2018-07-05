package com.news.ui.design.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;
import com.news.ui.histogram.HistogramActivity;

import butterknife.BindView;


public class ViewGroupActivity extends BaseSwipeBackActivity {

    @BindView(R.id.radarViewBtn)
    Button mRadarBtn;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_view_group;
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
        mRadarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewGroupActivity.this, RadarMenuActivity.class));
            }
        });

        findViewById(R.id.water).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewGroupActivity.this, WaterFlowActivity.class));
            }
        });
        findViewById(R.id.selfDe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewGroupActivity.this, FlowActivity.class));
            }
        });
        findViewById(R.id.histogramView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewGroupActivity.this, HistogramActivity.class));
            }
        });
    }
}
