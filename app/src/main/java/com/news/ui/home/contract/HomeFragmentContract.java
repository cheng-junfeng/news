package com.news.ui.home.contract;


import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.news.app.adapter.BaseDelegateAdapter;

public interface HomeFragmentContract {

    interface View {
    }

    interface Presenter {
        //初始化
        DelegateAdapter initRecyclerView(RecyclerView recyclerView);

        BaseDelegateAdapter initMenu1();
    }
}
