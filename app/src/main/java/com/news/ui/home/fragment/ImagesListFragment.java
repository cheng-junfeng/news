package com.news.ui.home.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.adapter.ListViewDataAdapter;
import com.library.adapter.ViewHolderBase;
import com.library.adapter.ViewHolderCreator;
import com.library.pla.PLAAdapterView;
import com.library.pla.PLAImageView;
import com.library.utils.CommonUtils;
import com.library.widgets.XSwipeRefreshLayout;
import com.news.R;
import com.news.app.base.BaseFragment;
import com.news.net.api.ApiConstants;
import com.news.net.bean.ImagesListEntity;
import com.news.net.bean.ResponseImagesListEntity;
import com.news.ui.home.contract.ImageContract;
import com.news.ui.home.presenter.ImagePresenter;
import com.news.ui.image.ImagesDetailActivity;
import com.news.utils.NetUtils;
import com.news.utils.UriHelper;
import com.news.widgets.PLALoadMoreListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ImagesListFragment extends BaseFragment implements ImageContract.View,
        PLALoadMoreListView.OnLoadMoreListener, PLAAdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.fragment_images_list_list_view)
    PLALoadMoreListView mListView;
    @BindView(R.id.fragment_images_list_swipe_layout)
    XSwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;

    private int mCurrentPage = 0;

    private ImagePresenter mImagesListPresenter = null;
    private ListViewDataAdapter<ImagesListEntity> mListViewAdapter = null;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_images_list;
    }

    @Override
    protected View getLoadingTargetView() {
        return mSwipeRefreshLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initFirstData();
        return rootView;
    }

    @Override
    protected void initViews() {
        mListViewAdapter = new ListViewDataAdapter<ImagesListEntity>(new ViewHolderCreator<ImagesListEntity>() {
            @Override
            public ViewHolderBase<ImagesListEntity> createViewHolder(int position) {
                return new ViewHolderBase<ImagesListEntity>() {

                    PLAImageView mItemImage;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View convertView = layoutInflater.inflate(R.layout.item_list_images_list, null);
                        mItemImage = ButterKnife.findById(convertView, R.id.list_item_images_list_image);

                        return convertView;
                    }

                    @Override
                    public void showData(int position, ImagesListEntity itemData) {
                        int width = itemData.getThumbnailWidth();
                        int height = itemData.getThumbnailHeight();

                        String imageUrl = itemData.getThumbnailUrl();

                        if (!CommonUtils.isEmpty(imageUrl)) {
                            ImageLoader.getInstance().displayImage(imageUrl, mItemImage);
                        }

                        mItemImage.setImageWidth(width);
                        mItemImage.setImageHeight(height);
                    }
                };
            }
        });

        mListView.setOnItemClickListener(this);
        mListView.setOnLoadMoreListener(this);
        mListView.setAdapter(mListViewAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    protected void initFirstData() {
        mCurrentPage = 0;
        mImagesListPresenter = new ImagePresenter(mContext, this);

        if (NetUtils.isNetworkConnected(mContext)) {
            if (null != mSwipeRefreshLayout) {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mImagesListPresenter.getNetData(mCurrentPage, false);
                    }
                }, ApiConstants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);
            }
        } else {
            toggleNetworkError(true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImagesListPresenter.getNetData(mCurrentPage, false);
                }
            });
        }
    }

    @Override
    public void refreshListData(ResponseImagesListEntity responseImagesListEntity) {
        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if (null != responseImagesListEntity) {
            if (null != mListViewAdapter) {
                mListViewAdapter.getDataList().clear();
                mListViewAdapter.getDataList().addAll(responseImagesListEntity.getImgs());
                mListViewAdapter.notifyDataSetChanged();
            }

            if (null != mListView) {
                if (UriHelper.getInstance().calculateTotalPages(responseImagesListEntity.getTotalNum()) > mCurrentPage) {
                    mListView.setCanLoadMore(true);
                } else {
                    mListView.setCanLoadMore(false);
                }
            }
        }
    }

    @Override
    public void addMoreListData(ResponseImagesListEntity responseImagesListEntity) {
        if (null != mListView) {
            mListView.onLoadMoreComplete();
        }

        if (null != responseImagesListEntity) {
            if (null != mListViewAdapter) {
                mListViewAdapter.getDataList().addAll(responseImagesListEntity.getImgs());
                mListViewAdapter.notifyDataSetChanged();
            }

            if (null != mListView) {
                if (UriHelper.getInstance().calculateTotalPages(responseImagesListEntity.getTotalNum()) > mCurrentPage) {
                    mListView.setCanLoadMore(true);
                } else {
                    mListView.setCanLoadMore(false);
                }
            }
        }
    }

    @Override
    public void navigateToImagesDetail(int position, ImagesListEntity entity, int x, int y, int width, int height) {
        Bundle extras = new Bundle();
        extras.putString(ImagesDetailActivity.INTENT_IMAGE_URL_TAG, entity.getThumbnailUrl());
        extras.putInt(ImagesDetailActivity.INTENT_IMAGE_X_TAG, x);
        extras.putInt(ImagesDetailActivity.INTENT_IMAGE_Y_TAG, y);
        extras.putInt(ImagesDetailActivity.INTENT_IMAGE_W_TAG, width);
        extras.putInt(ImagesDetailActivity.INTENT_IMAGE_H_TAG, height);
        readyGo(ImagesDetailActivity.class, extras);
        getActivity().overridePendingTransition(0, 0);
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mImagesListPresenter.getNetData(mCurrentPage, false);
    }

    @Override
    public void onLoadMore() {
        mCurrentPage++;
        mImagesListPresenter.getNetData(mCurrentPage, true);
    }

    @Override
    public void showError(String msg) {
        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        toggleShowError(true, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImagesListPresenter.getNetData(mCurrentPage, false);
            }
        });
    }

    @Override
    public void onItemClick(PLAAdapterView<?> parent, View view, int position, long id) {
        Rect frame = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        location[1] += statusBarHeight;

        int width = view.getWidth();
        int height = view.getHeight();

        if (null != mListViewAdapter) {
            if (position >= 0 && position < mListViewAdapter.getDataList().size()) {
                navigateToImagesDetail(position, mListViewAdapter.getDataList().get(position),
                        location[0],
                        location[1],
                        width,
                        height);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
