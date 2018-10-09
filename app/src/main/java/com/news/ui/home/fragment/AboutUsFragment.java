package com.news.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.library.adapter.ListViewDataAdapter;
import com.library.adapter.ViewHolderBase;
import com.library.adapter.ViewHolderCreator;
import com.library.base.BaseWebActivity;
import com.library.utils.CommonUtils;
import com.news.BuildConfig;
import com.news.R;
import com.news.app.base.BaseFragment;
import com.news.net.bean.AboutListEntity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutUsFragment extends BaseFragment {

    @BindView(R.id.about_us_list)
    ListView mListView;

    private String[] mAboutArray = null;
    private AboutListEntity mItemData = null;
    private ListViewDataAdapter<AboutListEntity> mListViewDataAdapter = null;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_about_us;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {
        mAboutArray = getResources().getStringArray(R.array.about_list);

        mListViewDataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<AboutListEntity>() {
            @Override
            public ViewHolderBase<AboutListEntity> createViewHolder(int position) {
                return new ViewHolderBase<AboutListEntity>() {

                    TextView mTitle;
                    TextView mSubTitle;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View convertView = layoutInflater.inflate(R.layout.item_list_about, null);
                        mTitle = ButterKnife.findById(convertView, R.id.list_item_about_title);
                        mSubTitle = ButterKnife.findById(convertView, R.id.list_item_about_sub_title);
                        return convertView;
                    }

                    @Override
                    public void showData(int position, AboutListEntity itemData) {
                        if (null != itemData) {
                            if (!CommonUtils.isEmpty(itemData.getTitle())) {
                                mTitle.setText(itemData.getTitle());
                            }

                            if (!CommonUtils.isEmpty(itemData.getSubTitle())) {
                                mSubTitle.setText(itemData.getSubTitle());
                            }
                        }
                    }
                };
            }
        });

        mListView.setAdapter(mListViewDataAdapter);

        mItemData = new AboutListEntity();
        mItemData.setTitle(mAboutArray[0]);
        mItemData.setSubTitle(String.format(getResources().getString(R.string.splash_version), BuildConfig.VERSION_NAME));
        mListViewDataAdapter.getDataList().add(mItemData);

        mItemData = new AboutListEntity();
        mItemData.setTitle(mAboutArray[1]);
        mItemData.setSubTitle("android_asset/open_source");
        mListViewDataAdapter.getDataList().add(mItemData);

        mListViewDataAdapter.notifyDataSetChanged();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras = new Bundle();
                switch (position) {
                    case 0:
                        // version
                        break;
                    case 1:
                        // open source description
                        extras.putString(BaseWebActivity.BUNDLE_KEY_TITLE, mAboutArray[1]);
                        extras.putBoolean(BaseWebActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR, false);
                        extras.putString(BaseWebActivity.BUNDLE_KEY_URL, "file:///android_asset/open_source.html");

                        readyGo(BaseWebActivity.class, extras);
                        break;

                }
            }
        });
    }
}
