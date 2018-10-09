package com.news.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.hint.listener.OnChooseListener;
import com.news.R;
import com.news.ui.home.bean.NewsListItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<NewsListItemBean> data = new ArrayList<>();
    private OnChooseListener mListener;

    public NewsListItemAdapter(Context context) {
        this.mContext = context;
    }

    public void setRowsBeans(List<NewsListItemBean> rowsBeans, boolean loadMore) {
        if(loadMore){
            this.data.addAll(rowsBeans);
        }else{
            this.data = new ArrayList<>();
            this.data.addAll(rowsBeans);
        }
        notifyDataSetChanged();
    }

    public void setListener(OnChooseListener listener){
        this.mListener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.all_item_news_temp, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsListItemBean userModel = data.get(position);
        List<String> indexArticleAttach = userModel.allImages;
        if(indexArticleAttach != null && indexArticleAttach.size() != 0){
            holder.recyclerView.setVisibility(View.VISIBLE);
            ArrayList<String> data = new ArrayList<String>();
            for(String url : indexArticleAttach){
                data.add(url);
            }
            NewsRecyclerAdapter mAdapter = new NewsRecyclerAdapter(mContext, data);

            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.recyclerView.setLayoutManager(manager);
            holder.recyclerView.setAdapter(mAdapter);
        }else{
            holder.recyclerView.setVisibility(View.GONE);
        }
        holder.ivItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onPositive(position);
                }
            }
        });
        holder.recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        if(mListener != null){
                            mListener.onPositive(position);
                        }
                        break;
                }
                return true;
            }
        });
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public NewsListItemBean getItem(int pos) {
        if (data == null || pos >= data.size()) {
            return null;
        }
        return data.get(pos);
    }

    public List<NewsListItemBean> getData() {
        return data;
    }

    class ViewHolder {
        @BindView(R.id.iv_itemview)
        View ivItemView;
        @BindView(R.id.iv_itemTitle)
        TextView ivItemTitle;
        @BindView(R.id.tv_origin)
        TextView tvOrigin;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
