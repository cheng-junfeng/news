package com.news.ui.design.activitys;

import android.content.Intent;
import android.os.Bundle;;
import android.view.View;

import com.custom.view.DotView;
import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;

import butterknife.BindView;


public class ExtendsViewActivity extends BaseSwipeBackActivity {


    @BindView(R.id.dotView)
    DotView dotView;

    boolean isSelect = false;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_extends_view;
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
        findViewById(R.id.panelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExtendsViewActivity.this, PanelCircleActivity.class));
            }
        });

        findViewById(R.id.waterView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExtendsViewActivity.this, WaterActivity.class));
            }
        });

        findViewById(R.id.dotView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelect = !isSelect;
                dotView.setIsSelected(isSelect);
            }
        });
    }
}
