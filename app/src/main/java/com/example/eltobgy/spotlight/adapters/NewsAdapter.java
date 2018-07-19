package com.example.eltobgy.spotlight.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eltobgy.spotlight.R;
import com.example.eltobgy.spotlight.models.News;
import com.example.eltobgy.spotlight.utlis.Helper;

import java.util.ArrayList;

/**
 * Created by Eltobgy.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<News> mNews;

    public NewsAdapter(Context mContext, ArrayList<News> mNews) {
        this.mContext = mContext;
        this.mNews = mNews;
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_list_fav, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                   Helper.showToast(mContext, "Add to favourite");
                    return true;
                case R.id.action_play_next:
                    Helper.showToast(mContext, "Add to List");
                    return true;
                default:
            }
            return false;
        }
    }

    @NonNull
    @Override


    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder holder, int position) {
        News news = mNews.get(position);
        if (news.getmTitle() == null) {
            holder.newsTitle.setVisibility(View.GONE);
        } else {

            holder.newsTitle.setText(news.getmTitle());
        }
        if (news.getmDescription() == null) {
            holder.newsDescription.setVisibility(View.GONE);
        } else {
            holder.newsDescription.setText(news.getmDescription() + "");
        }
        if (news.getmImageResourceId().equals("")) {
            holder.newsImg.setVisibility(View.GONE);
        } else {
            Glide.with(mContext).load(news.getmImageResourceId()).into(holder.newsImg);

        }
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        //LinearLayout item;
        public final TextView newsTitle, newsDescription;
        public final ImageView newsImg ,overflow;

        public ViewHolder(View view) {
            super(view);
            newsImg = view.findViewById(R.id.news_img);
            newsTitle = view.findViewById(R.id.news_title);
            newsDescription = view.findViewById(R.id.news_description);
            overflow = view.findViewById(R.id.news_img);
        }
    }
}
