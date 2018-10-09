package com.news.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.custom.group.WrapContentListView;
import com.hint.listener.OnChooseListener;
import com.hint.utils.ToastUtils;
import com.library.widgets.XSwipeRefreshLayout;
import com.news.R;
import com.news.app.base.BaseFragment;
import com.news.ui.home.adapter.NewsListItemAdapter;
import com.news.ui.home.bean.NewsListItemBean;
import com.news.ui.home.bean.NewsStatus;
import com.news.ui.home.presenter.NewsImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


public class NewsListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "NewsListFragment";
    private static final String NEWS_STATUS = "news.status";

    Unbinder unbinder;
    @BindView(R.id.scroll_home)
    ScrollView scrollHome;
    @BindView(R.id.fragment_banner)
    Banner fragmentBanner;
    @BindView(R.id.fragment_images_list_swipe_layout)
    XSwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    WrapContentListView listView;
    @BindView(R.id.view_empty)
    FrameLayout viewEmpty;

    Context mContext;
    NewsStatus mStatus;
    NewsListItemAdapter mAdapter;

    List<String> allImages;
    List<String> allTitles;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_news_temp;
    }

    @Override
    protected View getLoadingTargetView() {
        return mSwipeRefreshLayout;
    }

    @Override
    protected void initViews() {
        mContext = this.getContext();

        mStatus = (NewsStatus) getArguments().getSerializable(NEWS_STATUS);
        setCarouse();
        initListView();

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public static NewsListFragment newInstance(NewsStatus status) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NEWS_STATUS, status);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void updateArguments(NewsStatus status) {
        this.mStatus = status;
        Bundle args = getArguments();
        if (args != null) {
            args.putSerializable(NEWS_STATUS, status);
        }
    }

    @Override
    public void onRefresh() {
    }

    private void setCarouse() {
        allImages = new ArrayList<>();
        allTitles = new ArrayList<>();
        if (mStatus.getIndex() == 0) {
            allImages.add("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=f35c32332b34349b600b66d7a8837eab/7e3e6709c93d70cfedb67924fbdcd100baa12b4f.jpg");
            allTitles.add("中国光谷");

            allImages.add("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=9816be9e51b5c9ea76fe0bb1b450dd65/d1a20cf431adcbefda84f693a6af2edda2cc9ff7.jpg");
            allTitles.add("未来科技城");
        }

        if (allImages.size() > 0) {
            fragmentBanner.setVisibility(View.VISIBLE);

            //设置banner样式
            fragmentBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器
            fragmentBanner.setImageLoader(new NewsImageLoader());
            //设置图片集合
            fragmentBanner.setImages(allImages);
            //设置banner动画效果
            fragmentBanner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            fragmentBanner.setBannerTitles(allTitles);
            //设置自动轮播，默认为true
            fragmentBanner.isAutoPlay(true);
            //设置轮播时间
            fragmentBanner.setDelayTime(3000);
            //设置指示器位置（当banner模式中有指示器时）
            fragmentBanner.setIndicatorGravity(BannerConfig.RIGHT);
            fragmentBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    ToastUtils.showToast(mContext, "click:" + position);
                }
            });
            //banner设置方法全部调用完毕时最后调用
            fragmentBanner.start();
        } else {
            fragmentBanner.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        fragmentBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        fragmentBanner.stopAutoPlay();
    }

    private void initListView() {
        mAdapter = new NewsListItemAdapter(mContext);
        mAdapter.setListener(new OnChooseListener() {
            @Override
            public void onPositive(int pos) {
                ToastUtils.showToast(mContext, "item:" + pos);
            }
        });

        NewsListItemBean a1 = new NewsListItemBean();
        NewsListItemBean a2 = new NewsListItemBean();
        List<NewsListItemBean> allData = new ArrayList<>();
        allData.add(a1);
        allData.add(a2);
        if (mStatus.getIndex() != 0) {
            NewsListItemBean a3 = new NewsListItemBean();
            a3.allImages = new ArrayList<>();
            a3.allImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539080227041&di=3ea28a068ee13c34ac52c29839fb9fcf&imgtype=0&src=http%3A%2F%2Fs3.sinaimg.cn%2Fbmiddle%2F001qa3VOzy7j5NFMV2y32%26690");
            a3.allImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539080227041&di=3ea28a068ee13c34ac52c29839fb9fcf&imgtype=0&src=http%3A%2F%2Fs3.sinaimg.cn%2Fbmiddle%2F001qa3VOzy7j5NFMV2y32%26690");
            allData.add(a3);
        }
        mAdapter.setRowsBeans(allData, false);
        listView.setAdapter(mAdapter);
        if (mStatus.getIndex() == 1) {
            scrollHome.setVisibility(View.GONE);
            viewEmpty.setVisibility(View.VISIBLE);
        } else {
            viewEmpty.setVisibility(View.GONE);
            scrollHome.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
