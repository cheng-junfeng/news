package com.news.ui.charts;

import android.os.Bundle;
import android.view.View;

import com.news.R;
import com.news.app.base.BaseSwipeBackActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartActivity extends BaseSwipeBackActivity {

    @BindView(R.id.piechart)
    PieChartView piechart;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_chart_pie;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {
        setTitle("Pie");
        piechart.setViewportCalculationEnabled(true);//设置饼图自动适应大小
//        piechart.setChartRotation(90, true);//设置饼图旋转角度，且是否为动画
        piechart.setChartRotationEnabled(true);//设置饼图是否可以手动旋转
//        pieChart.setCircleFillRatio(float fillRatio);//设置饼图其中的比例
//        pieChart.setCircleOval(RectF orginCircleOval);//设置饼图成椭圆形

        PieChartData pd = new PieChartData();//实例化PieChartData对象
        pd.setHasLabelsOutside(true);//设置饼图外面是否显示值
        pd.setHasCenterCircle(true);//设置饼图中间是否有第二个圈
//        pd.setCenterCircleColor(int centerCircleColor);//设置饼图中间圈的颜色
        pd.setCenterCircleScale(0.5f);////设置第二个圈的大小比例
        pd.setCenterText1("Pie");//设置文本
//        pd.setCenterText1Color(int centerText1Color);//设置文本颜色
//        pd.setCenterText1FontSize(int centerText1FontSize);//设置文本大小
//        pd.setCenterText1Typeface(Typeface text1Typeface);//设置文本字体
//        pd.setCenterText2(String centerText2);//设置第二个圈文本
//        pd.setCenterText2Color(int centerText2Color);//设置第二个圈文本颜色
//        pd.setCenterText2Typeface(Typeface text2Typeface);//设置第二个圈文本字体
//        pd.setValueLabelsTextColor(int valueLabelTextColor);//设置显示值的字体颜色
//        pd.setSlicesSpacing(int sliceSpacing);//设置数据间的间隙
        pd.setHasLabelsOnlyForSelected(true);//设置当值被选中才显示
        List<SliceValue> sliceList = new ArrayList<SliceValue>();

        sliceList.add(new SliceValue(0.1f, mContext.getResources().getColor(R.color.gplus_color_1)).setLabel("A" + 0));
        sliceList.add(new SliceValue(0.2f, mContext.getResources().getColor(R.color.gplus_color_2)).setLabel("A" + 1));
        sliceList.add(new SliceValue(0.3f, mContext.getResources().getColor(R.color.gplus_color_3)).setLabel("A" + 2));
        sliceList.add(new SliceValue(0.4f, mContext.getResources().getColor(R.color.alpha_45_black)).setLabel("A" + 3));
        pd.setValues(sliceList);//为饼图添加数据

        piechart.setPieChartData(pd);//将数据设置给饼图
    }
}
