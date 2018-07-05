package com.news.ui.image;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.library.adapter.ListViewDataAdapter;
import com.library.adapter.ViewHolderBase;
import com.library.adapter.ViewHolderCreator;
import com.library.picker.ImageBucket;
import com.library.picker.ImagePickerHelper;
import com.library.utils.CommonUtils;
import com.news.R;
import com.news.app.base.BaseActivity;
import com.news.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagePickerListActivity extends BaseActivity {

    private static final int IMAGE_PICKER_DETAIL_REQUEST_CODE = 200;

    public static final String KEY_BUNDLE_ALBUM_PATH = "KEY_BUNDLE_ALBUM_PATH";
    public static final String KEY_BUNDLE_ALBUM_NAME = "KEY_BUNDLE_ALBUM_NAME";

    @BindView(R.id.common_image_picker_list_view)
    ListView commonImagePickerListView;

    private ListViewDataAdapter<ImageBucket> mListViewAdapter = null;
    private AsyncTask<Void, Void, List<ImageBucket>> mAlbumLoadTask = null;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_image_picker_list;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.BOTTOM;
    }

    @Override
    protected View getLoadingTargetView() {
        return commonImagePickerListView;
    }

    @Override
    protected void initViews() {
        setTitle(getResources().getString(R.string.title_image_picker));

        mListViewAdapter = new ListViewDataAdapter<ImageBucket>(new ViewHolderCreator<ImageBucket>() {
            @Override
            public ViewHolderBase<ImageBucket> createViewHolder(int position) {
                return new ViewHolderBase<ImageBucket>() {

                    ImageView mItemImage;
                    TextView mItemTitle;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View convertView = layoutInflater.inflate(R.layout
                                .item_list_image_picker, null);
                        mItemImage = ButterKnife.findById(convertView, R.id
                                .list_item_common_image_picker_thumbnail);
                        mItemTitle = ButterKnife.findById(convertView, R.id
                                .list_item_common_image_picker_title);
                        return convertView;
                    }

                    @Override
                    public void showData(int position, ImageBucket itemData) {
                        if (null != itemData) {
                            String imagePath = itemData.bucketList.get(0).getImagePath();
                            if (!CommonUtils.isEmpty(imagePath)) {
                                ImageLoader.getInstance().displayImage("file://" + imagePath,
                                        mItemImage,
                                        ImageLoaderHelper.getInstance(mContext).getDisplayOptions());
                            }

                            int count = itemData.count;
                            String title = itemData.bucketName;

                            if (!CommonUtils.isEmpty(title)) {
                                mItemTitle.setText(title + "(" + count + ")");
                            }
                        }
                    }
                };
            }
        });
        commonImagePickerListView.setAdapter(mListViewAdapter);

        commonImagePickerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != mListViewAdapter && null != mListViewAdapter.getDataList() &&
                        !mListViewAdapter.getDataList().isEmpty() &&
                        position < mListViewAdapter.getDataList().size()) {

                    Bundle extras = new Bundle();
                    extras.putParcelableArrayList(KEY_BUNDLE_ALBUM_PATH, mListViewAdapter
                            .getDataList().get(position).bucketList);
                    extras.putString(KEY_BUNDLE_ALBUM_NAME, mListViewAdapter.getDataList().get
                            (position).bucketName);

                    readyGoForResult(ImagePickerDetailActivity.class,
                            IMAGE_PICKER_DETAIL_REQUEST_CODE, extras);
                }
            }
        });

        mAlbumLoadTask = new AsyncTask<Void, Void, List<ImageBucket>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                toggleShowLoading(true, null);
                ImagePickerHelper.getHelper().init(mContext);
            }

            @Override
            protected List<ImageBucket> doInBackground(Void... params) {
                return ImagePickerHelper.getHelper().getImagesBucketList();
            }

            @Override
            protected void onPostExecute(List<ImageBucket> list) {
                toggleShowLoading(false, null);

                mListViewAdapter.getDataList().addAll(list);
                mListViewAdapter.notifyDataSetChanged();
            }
        };

        mAlbumLoadTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mAlbumLoadTask && !mAlbumLoadTask.isCancelled()) {
            mAlbumLoadTask.cancel(true);
            mAlbumLoadTask = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == IMAGE_PICKER_DETAIL_REQUEST_CODE) {
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
