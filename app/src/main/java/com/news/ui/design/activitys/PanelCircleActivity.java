package com.news.ui.design.activitys;

import android.os.Bundle;
import android.view.View;

import com.custom.view.PanelCircleView;
import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;

import butterknife.BindView;


public class PanelCircleActivity extends BaseSwipeBackActivity {

    @BindView(R.id.panelView)
    PanelCircleView mPanelCircleView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_panel_circle;
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
        mPanelCircleView.setPercent(50);
    }
}
