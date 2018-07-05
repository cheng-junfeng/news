package com.news.ui.design.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.custom.group.WaterFlowLayout;
import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;

import java.util.Random;

import butterknife.BindView;


public class WaterFlowActivity extends BaseSwipeBackActivity {

    private static int IMG_COUNT = 5;
    @BindView(R.id.waterfallLayout)
    WaterFlowLayout waterfallLayout;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_water_flow;
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
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(waterfallLayout);
            }
        });
    }

    public void addView(WaterFlowLayout waterfallLayout) {
        Random random = new Random();
        Integer num = Math.abs(random.nextInt());
        WaterFlowLayout.LayoutParams layoutParams = new WaterFlowLayout.LayoutParams(WaterFlowLayout.LayoutParams.WRAP_CONTENT,
                WaterFlowLayout.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(this);
        if (num % IMG_COUNT == 0) {
            imageView.setImageResource(R.drawable.pic_1);
        } else if (num % IMG_COUNT == 1) {
            imageView.setImageResource(R.drawable.pic_2);
        } else if (num % IMG_COUNT == 2) {
            imageView.setImageResource(R.drawable.pic_3);
        } else if (num % IMG_COUNT == 3) {
            imageView.setImageResource(R.drawable.pic_4);
        } else if (num % IMG_COUNT == 4) {
            imageView.setImageResource(R.drawable.pic_5);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        waterfallLayout.addView(imageView, layoutParams);

        waterfallLayout.setOnItemClickListener(new WaterFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int index) {
                Toast.makeText(WaterFlowActivity.this, "item=" + index, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
