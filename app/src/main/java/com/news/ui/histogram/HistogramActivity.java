package com.news.ui.histogram;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.news.R;

public class HistogramActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);

        Button btn_hv1 = (Button) findViewById(R.id.btn_hv1);
        new WeightUtil(getApplicationContext()).mLayoutParams_Btn(btn_hv1);

        HistogramView hv1 = (HistogramView) findViewById(R.id.hv1);
        hv1.setProgress(0.2);
        new WeightUtil(getApplicationContext()).mLayoutParams_View(hv1);
        hv1.setRateBackgroundColor("#FF8B6F");
        hv1.setOrientation(LinearLayout.HORIZONTAL);

        Button btn_hv2 = (Button) findViewById(R.id.btn_hv2);
        new WeightUtil(getApplicationContext()).mLayoutParams_Btn(btn_hv2);
        double d = 293;
        double b = 700;
        double p2 = d / b;
        System.out.println("p2----->" + p2);

        TextView tv_hv2 = (TextView) findViewById(R.id.tv_hv2);
        tv_hv2.setText("¥ 293.00");
        new WeightUtil(getApplicationContext()).mLayoutParams_Text(tv_hv2, p2);

        HistogramView hv2 = (HistogramView) findViewById(R.id.hv2);
        hv2.setProgress(p2);
        new WeightUtil(getApplicationContext()).mLayoutParams_View(hv2);
        hv2.setRateBackgroundColor("#F8D478");
        hv2.setOrientation(LinearLayout.HORIZONTAL);

        Button btn_hv3 = (Button) findViewById(R.id.btn_hv3);
        new WeightUtil(getApplicationContext()).mLayoutParams_Btn(btn_hv3);

        // 0.6
        HistogramView hv3 = (HistogramView) findViewById(R.id.hv3);
        hv3.setProgress(0.6);
        new WeightUtil(getApplicationContext()).mLayoutParams_View(hv3);
        hv3.setRateBackgroundColor("#72CFF6");
        hv3.setOrientation(LinearLayout.HORIZONTAL);


        double d4 = 553;
        double b4 = 700;
        double p4 = d4 / b4;
        Button btn_hv4 = (Button) findViewById(R.id.btn_hv4);
        new WeightUtil(getApplicationContext()).mLayoutParams_Btn(btn_hv4);

        TextView tv_hv4 = (TextView) findViewById(R.id.tv_hv4);
        new WeightUtil(getApplicationContext()).mLayoutParams_Text(tv_hv4, p4);
        tv_hv4.setText("¥ 553.00");
        HistogramView hv4 = (HistogramView) findViewById(R.id.hv4);
        hv4.setProgress(p4);
        new WeightUtil(getApplicationContext()).mLayoutParams_View(hv4);
        hv4.setRateBackgroundColor("#9F88C7");
        hv4.setOrientation(LinearLayout.HORIZONTAL);

        Button btn_hv5 = (Button) findViewById(R.id.btn_hv5);
        new WeightUtil(getApplicationContext()).mLayoutParams_Btn(btn_hv5);

        HistogramView hv5 = (HistogramView) findViewById(R.id.hv5);
        hv5.setProgress(1);
        new WeightUtil(getApplicationContext()).mLayoutParams_View(hv5);
        hv5.setRateBackgroundColor("#55D99E");
        hv5.setOrientation(LinearLayout.HORIZONTAL);

        Button btn_hv6 = (Button) findViewById(R.id.btn_hv6);
        new WeightUtil(getApplicationContext()).mLayoutParams_Btn(btn_hv6);

        HistogramView hv6 = (HistogramView) findViewById(R.id.hv6);
        hv6.setProgress(0.6);
        new WeightUtil(getApplicationContext()).mLayoutParams_View(hv6);
        hv6.setRateBackgroundColor("#EACFA7");
        hv6.setOrientation(LinearLayout.HORIZONTAL);

    }

    class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

        public int getHeight() {
            return mTarget.getLayoutParams().height;
        }

        public void setHeight(int height) {
            mTarget.getLayoutParams().height = height;
            mTarget.requestLayout();
        }

    }

}
