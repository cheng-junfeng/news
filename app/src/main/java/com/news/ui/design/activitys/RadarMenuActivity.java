package com.news.ui.design.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.custom.view.RadarMenuView;
import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;

import butterknife.BindView;


public class RadarMenuActivity extends BaseSwipeBackActivity {

    @BindView(R.id.radarView)
    RadarMenuView mRadarMenuView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_radar_menu;
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
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(layoutParams);
        imageView.setTag("我是新加的");
        imageView.setImageResource(R.mipmap.menu_phone);
        mRadarMenuView.addView(imageView);
        mRadarMenuView.setOnMenuItemClickListener(new RadarMenuView.onMenuItemClickListener() {
            @Override
            public void onClick(View itemView, int position) {
                Toast.makeText(RadarMenuActivity.this, "tag=" + itemView.getTag() + " position=" + position, Toast.LENGTH_LONG).show();
            }
        });
    }
}
