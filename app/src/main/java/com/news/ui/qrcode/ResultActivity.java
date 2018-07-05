package com.news.ui.qrcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.utils.CommonUtils;
import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;
import com.news.ui.qrcode.decode.DecodeThread;
import com.news.ui.qrcode.decode.DecodeUtils;

import butterknife.BindView;

public class ResultActivity extends BaseSwipeBackActivity {

    public static final String BUNDLE_KEY_SCAN_RESULT = "BUNDLE_KEY_SCAN_RESULT";
    @BindView(R.id.result_image)
    ImageView resultImage;
    @BindView(R.id.result_type)
    TextView resultType;
    @BindView(R.id.result_content)
    TextView resultContent;

    private Bitmap mBitmap;
    private int mDecodeMode;
    private String mResultStr;
    private String mDecodeTime;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_result;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        if (extras != null) {
            byte[] compressedBitmap = extras.getByteArray(DecodeThread.BARCODE_BITMAP);
            if (compressedBitmap != null) {
                mBitmap = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
                mBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
            }

            mResultStr = extras.getString(BUNDLE_KEY_SCAN_RESULT);
            mDecodeMode = extras.getInt(DecodeThread.DECODE_MODE);
            mDecodeTime = extras.getString(DecodeThread.DECODE_TIME);
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {
        setTitle("Result");

        StringBuilder sb = new StringBuilder();
        sb.append("扫描方式:\t\t");
        if (mDecodeMode == DecodeUtils.DECODE_MODE_ZBAR) {
            sb.append("ZBar扫描");
        } else if (mDecodeMode == DecodeUtils.DECODE_MODE_ZXING) {
            sb.append("ZXing扫描");
        }

        if (!CommonUtils.isEmpty(mDecodeTime)) {
            sb.append("\n\n扫描时间:\t\t");
            sb.append(mDecodeTime);
        }
        sb.append("\n\n扫描结果:");

        resultType.setText(sb.toString());
        resultContent.setText(mResultStr);

        if (null != mBitmap) {
            resultImage.setImageBitmap(mBitmap);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mBitmap && !mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }
}
