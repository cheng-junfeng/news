package com.news.ui.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.hint.utils.ToastUtils;
import com.library.utils.file.FileTypesUtils;
import com.library.utils.url.UrlValidator;
import com.news.BuildConfig;
import com.news.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ToolsTestActivity extends AppCompatActivity {

    @BindView(R.id.fragment_banner)
    Banner fragmentBanner;
    @BindView(R.id.url_check)
    TextView urlCheck;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_test);
        ButterKnife.bind(this);

        mContext = this;
        initView();
    }

    private void initView(){
        List<Integer> images = new ArrayList();
        images.add(R.drawable.afternoon);
        images.add(R.drawable.morning);
        List<String> titles = new ArrayList();
        titles.add("afternoon");
        titles.add("morning");

        //设置banner样式
        fragmentBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        fragmentBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        fragmentBanner.setImages(images);
        //设置banner动画效果
        fragmentBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        fragmentBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        fragmentBanner.isAutoPlay(true);
        //设置轮播时间
        fragmentBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        fragmentBanner.setIndicatorGravity(BannerConfig.RIGHT);
        fragmentBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showToast(mContext, "click:"+position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        fragmentBanner.start();


        String url = "http://www";
        UrlValidator urlValidator = UrlValidator.getInstance();
        boolean isFine = urlValidator.isValid(url);
        urlCheck.setText(isFine?"true ": "false");
    }

    @OnClick(R.id.open_file)
    public void onViewClicked() {
        File dirConf = null;
        if(Environment.isExternalStorageEmulated()){
            dirConf = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
        if(dirConf != null){
            File file = new File(dirConf, "33.png");
            Intent intent = FileTypesUtils.openFile(mContext, BuildConfig.APPLICATION_ID+".provider", file.getAbsolutePath());
            mContext.startActivity(intent);
        }
    }
}
