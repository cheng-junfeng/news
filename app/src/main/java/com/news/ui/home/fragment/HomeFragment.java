package com.news.ui.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.news.R;
import com.news.app.adapter.BaseDelegateAdapter;
import com.news.app.base.BaseFragment;
import com.news.ui.home.contract.HomeFragmentContract;
import com.news.ui.home.presenter.HomeFragmentPresenter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private HomeFragmentContract.Presenter presenter;
    private List<DelegateAdapter.Adapter> mAdapters;

    DelegateAdapter delegateAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected View getLoadingTargetView() {
        return recyclerView;
    }

    @Override
    protected void initViews() {
        mAdapters = new LinkedList<>();
        initRecyclerView();
    }

    private void initRecyclerView() {
        presenter = new HomeFragmentPresenter(this.getActivity());
        delegateAdapter = presenter.initRecyclerView(recyclerView);
        BaseDelegateAdapter bannerAdapter = presenter.initMenu1();
        mAdapters.add(bannerAdapter);

        //设置适配器
        delegateAdapter.setAdapters(mAdapters);
    }
}
