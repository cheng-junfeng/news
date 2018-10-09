package com.news.ui.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.library.utils.file.FileTypesUtils;
import com.library.utils.url.UrlValidator;
import com.news.BuildConfig;
import com.news.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ToolsTestActivity extends AppCompatActivity {

    @BindView(R.id.url_check)
    TextView urlCheck;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_test);
        ButterKnife.bind(this);

        mContext = this;
        initView();
    }

    private void initView(){
        List<Integer> images = new ArrayList();
        images.add(R.drawable.afternoon);
        images.add(R.drawable.morning);
        List<String> titles = new ArrayList();
        titles.add("afternoon");
        titles.add("morning");

        String url = "http://www";
        UrlValidator urlValidator = UrlValidator.getInstance();
        boolean isFine = urlValidator.isValid(url);
        urlCheck.setText(isFine?"true ": "false");
    }

    @OnClick(R.id.open_file)
    public void onViewClicked() {
        File dirConf = null;
        if(Environment.isExternalStorageEmulated()){
            dirConf = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
        if(dirConf != null){
            File file = new File(dirConf, "33.png");
            Intent intent = FileTypesUtils.openFile(mContext, BuildConfig.APPLICATION_ID+".provider", file.getAbsolutePath());
            mContext.startActivity(intent);
        }
    }
}
