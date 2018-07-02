package com.example.eltobgy.spotlight.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eltobgy.spotlight.R;
import com.example.eltobgy.spotlight.models.News;

import java.util.ArrayList;

/**
 * Created by Eltobgy on 31-May-18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<News> mNews;

    /**public NewsAdapter(Activity context, ArrayList<News> mNews) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
       super(context,0,mNews);
    }**/
    @NonNull
    @Override


    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        LinearLayout item;
        public final TextView newsTitle, newsDescription;
        public final ImageView newsImg;

        public ViewHolder(View view) {
            super(view);
            //title = itemView.findViewById(R.id.title);
            newsImg = view.findViewById(R.id.list_item_icon);
            newsTitle = view.findViewById(R.id.list_item_title_textview);
            newsDescription = view.findViewById(R.id.list_item_description_textview);
        }
    }
}
