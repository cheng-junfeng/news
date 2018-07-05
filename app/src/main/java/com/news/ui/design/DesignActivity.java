package com.news.ui.design;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hint.utils.ProgressUtils;
import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;
import com.news.ui.design.activitys.ExtendsViewActivity;
import com.news.ui.design.activitys.ViewGroupActivity;


public class DesignActivity extends BaseSwipeBackActivity {

    private Context mContext;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_design;
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
        mContext = this;

        findViewById(R.id.progress1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.showLoading(mContext);
            }
        });

        findViewById(R.id.progress2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.showLoading(mContext, "Loading Hardly");
            }
        });

        findViewById(R.id.progress3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.showProgressDialog(mContext);
            }
        });

        findViewById(R.id.progress4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.showProgressDialog(mContext, "Loading Hardly");
            }
        });

        findViewById(R.id.progress5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.showProgressFish(mContext);
            }
        });

        findViewById(R.id.progress6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.showProgressFish(mContext, ProgressUtils.FISH_TWO);
            }
        });

        findViewById(R.id.progress7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.showProgressScale(mContext, 70);
            }
        });

        findViewById(R.id.selfViewGroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DesignActivity.this, ViewGroupActivity.class));
            }
        });
        findViewById(R.id.selftView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DesignActivity.this, ExtendsViewActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ProgressUtils.dismissLoading();
        ProgressUtils.dismissProgressDialog();
        ProgressUtils.dismissProgressFish();
        ProgressUtils.dismissProgressScale();
    }
}
