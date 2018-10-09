package com.news.ui.home.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.news.R;
import com.news.ui.home.bean.NewsStatus;


public class NewsTabLayoutUtil {

    public static View getTabView(Context content, NewsStatus status) {
        View view = LayoutInflater.from(content).inflate(R.layout.all_tab_layout_news, null);
        TextView tab_title = view.findViewById(R.id.tab_title);
        tab_title.setText(status.getTitle());
        return view;
    }

    public static void setTabTitle(View view, boolean select){
        TextView tab_title = view.findViewById(R.id.tab_title);
        if(tab_title == null){
            return;
        }

        TextPaint paint = tab_title.getPaint();
        if(select){
            paint.setFakeBoldText(true);
            tab_title.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            paint.setFakeBoldText(false);
            tab_title.setTextColor(Color.parseColor("#F5F5F5"));
        }
    }
}
