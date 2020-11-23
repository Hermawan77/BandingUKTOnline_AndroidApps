package com.example.tugasbesar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<News> mNewsData;
    private Context mContext;

    NewsAdapter(Context context, ArrayList<News> NewsData) {
        this.mNewsData = NewsData;
        this.mContext = context;
    }


    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        News currentNews = mNewsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentNews);
        Glide.with(mContext).load(currentNews.getImageResource()).into(holder.mSportsImage);
    }

    @Override
    public int getItemCount() {
        return mNewsData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        ImageView mSportsImage;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImage = (ImageView) itemView.findViewById(R.id.sportImage);
            itemView.setOnClickListener(this);
        }
        void bindTo(News currentNews){
            // Populate the textviews with data.
            mTitleText.setText(currentNews.getTitle());
            mInfoText.setText(currentNews.getInfo());

        }

        @Override
        public void onClick(View v) {
            News currentNews = mNewsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentNews.getTitle());
            detailIntent.putExtra("image_resource", currentNews.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
