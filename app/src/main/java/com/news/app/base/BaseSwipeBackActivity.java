package com.news.app.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.library.base.BaseSwipeBackCompatActivity;
import com.news.NewsApplication;
import com.news.R;
import com.news.app.mvp.BaseView;

import butterknife.ButterKnife;


public abstract class BaseSwipeBackActivity extends BaseSwipeBackCompatActivity implements BaseView {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected NewsApplication getBaseApplication() {
        return (NewsApplication) getApplication();
    }

    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, null);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }
}
