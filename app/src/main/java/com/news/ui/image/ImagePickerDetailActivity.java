package com.news.ui.image;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.library.adapter.ListViewDataAdapter;
import com.library.adapter.ViewHolderBase;
import com.library.adapter.ViewHolderCreator;
import com.library.picker.ImageItem;
import com.library.utils.CommonUtils;
import com.news.R;
import com.news.app.base.BaseActivity;
import com.news.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImagePickerDetailActivity extends BaseActivity {

    public static final String KEY_BUNDLE_RESULT_IMAGE_PATH = "KEY_BUNDLE_RESULT_IMAGE_PATH";

    @BindView(R.id.common_image_picker_detail_grid_view)
    GridView commonImagePickerDetailGridView;

    private ListViewDataAdapter<ImageItem> mGridViewAdapter = null;
    private List<ImageItem> mGridListData = null;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_image_picker_detail;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mGridListData = extras.getParcelableArrayList(ImagePickerListActivity
                .KEY_BUNDLE_ALBUM_PATH);

        String title = extras.getString(ImagePickerListActivity.KEY_BUNDLE_ALBUM_NAME);
        if (!CommonUtils.isEmpty(title)) {
            setTitle(title);
        }
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.BOTTOM;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {
        mGridViewAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<ImageItem>() {
            @Override
            public ViewHolderBase<ImageItem> createViewHolder(int position) {
                return new ViewHolderBase<ImageItem>() {

                    ImageView mItemImage;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View convertView = layoutInflater.inflate(R.layout.item_grid_image_picker, null);
                        mItemImage = ButterKnife.findById(convertView, R.id.grid_item_common_image_picker_image);
                        return convertView;
                    }

                    @Override
                    public void showData(int position, ImageItem itemData) {
                        if (null != itemData) {
                            String imagePath = itemData.getImagePath();
                            if (!CommonUtils.isEmpty(imagePath)) {
                                ImageLoader.getInstance().displayImage("file://" + imagePath,
                                        mItemImage, ImageLoaderHelper.getInstance(mContext).getDisplayOptions());
                            }
                        }
                    }
                };
            }
        });
        mGridViewAdapter.getDataList().addAll(mGridListData);
        commonImagePickerDetailGridView.setAdapter(mGridViewAdapter);

        commonImagePickerDetailGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != mGridViewAdapter && null != mGridViewAdapter.getDataList() &&
                        !mGridViewAdapter.getDataList().isEmpty() &&
                        position < mGridViewAdapter.getDataList().size()) {

                    Intent intent = new Intent();
                    intent.putExtra(KEY_BUNDLE_RESULT_IMAGE_PATH,
                            mGridViewAdapter.getDataList().get(position).getImagePath());

                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
