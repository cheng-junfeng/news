package com.news.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.news.R;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> datas;
    private LayoutInflater inflater;
    private RequestOptions imgOptions;

    public NewsRecyclerAdapter(Context mainActivity, List<String> data) {
        this.context = mainActivity;
        this.datas = data;
        inflater = LayoutInflater.from(context);
        imgOptions = new RequestOptions()
                .placeholder(R.mipmap.default_error)
                .error(R.mipmap.default_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lv_news_image, parent,false);
        ViewHoders viewHoders = new ViewHoders(view);
        return viewHoders;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(datas.get(position)).apply(imgOptions).into(((ViewHoders) holder).imageView);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public String getItem(int position){
        return datas.get(position);
    }

    class ViewHoders extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public ViewHoders(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }
}
