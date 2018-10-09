package com.news.ui.home.fragment;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.news.R;
import com.news.app.base.BaseFragment;
import com.news.ui.home.bean.NewsStatus;
import com.news.ui.home.utils.NewsTabLayoutUtil;
import com.news.utils.DensityHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


public class NewsFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private final static String TAG = "NewsFragment";
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    private int oldIndex = 0;
    private Context mContext;
    private List<NewsStatus> mDatas = new ArrayList<>();
    private InnerAdapter mAdapter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {
        DensityHelper.resetDensity(getContext(), 720);
        mContext = this.getContext();
        mDatas = new ArrayList<>();

        mDatas = new ArrayList<>();
        mDatas.add(new NewsStatus.Builder(0).title("今日头条").build());
        mDatas.add(new NewsStatus.Builder(1).title("公司公告").build());
        mDatas.add(new NewsStatus.Builder(2).title("实时新闻").build());
        mDatas.add(new NewsStatus.Builder(3).title("国际政要").build());
        mDatas.add(new NewsStatus.Builder(4).title("企业新闻").build());
        mDatas.add(new NewsStatus.Builder(5).title("关于我们").build());

        initViewPage();
    }

    private void initViewPage() {
        if (oldIndex >= mDatas.size()) {
            oldIndex = 0;//while remove the last one, back to first
        }
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mAdapter = new InnerAdapter(getFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(oldIndex);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < mDatas.size(); i++) {
            NewsStatus status = mDatas.get(i);
            tabLayout.getTabAt(i).setCustomView(NewsTabLayoutUtil.getTabView(mContext, status));
        }
        View oldView = tabLayout.getTabAt(oldIndex).getCustomView();
        NewsTabLayoutUtil.setTabTitle(oldView, true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (oldIndex != position) {
            View oldView = tabLayout.getTabAt(oldIndex).getCustomView();
            View newView = tabLayout.getTabAt(position).getCustomView();
            NewsTabLayoutUtil.setTabTitle(oldView, false);
            NewsTabLayoutUtil.setTabTitle(newView, true);
            oldIndex = position;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class InnerAdapter extends FragmentPagerAdapter {
        public InnerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            NewsListFragment fragment = (NewsListFragment) super.instantiateItem(container, position);
            fragment.updateArguments(mDatas.get(position));
            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return NewsListFragment.newInstance(mDatas.get(position));
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (mDatas != null) {
                return mDatas.get(position).getTitle();
            }
            return null;
        }
    }
}
