package com.panda.youtube_search_engine.search_engine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.panda.youtube_search_engine.R;
import com.panda.youtube_search_engine.data.search_response.Item;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ahmed Kamal on 21-11-2017.
 */

public class Search_Result_adapter extends RecyclerView.Adapter<MyViewHolder_Search_Result_Adapter> {
    private List<Item> movies;
    private Context c;
    private OnSrelection Call_back;
    private final int MAX_TEXT_SIZE = 45;
    static ArrayList<Integer> colorList = new ArrayList<Integer>()
        {
        {
            add(R.color.carbon_yellow_a400);
            add(R.color.carbon_amber_400);
            add(R.color.carbon_green_500);
            add(R.color.carbon_blue_400);
            add(R.color.carbon_blue_500);
            add(R.color.carbon_brown_400);
        }
        };

    protected Search_Result_adapter(@NonNull List<Item> movies, OnSrelection Call_back) {
        this.movies = movies;
        this.Call_back = Call_back;
                                     }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyViewHolder_Search_Result_Adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.search_list_item, parent, false);
        c = parent.getContext();
        MyViewHolder_Search_Result_Adapter viewHolder = new MyViewHolder_Search_Result_Adapter(view);
        viewHolder.itemView.setOnClickListener(view1 -> Call_back.selection(viewHolder.getAdapterPosition()));
        // remove View Click Listner From OnBindView
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_Search_Result_Adapter holder, int position) {
        Item movie = movies.get(position);
        OnBind(movie, holder);
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }
    public void replaceData(List<Item> movies)
    {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback_thumps((ArrayList<Item>) movies, (ArrayList<Item>) this.movies));
        this.movies.clear();
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(Search_Result_adapter.this);
    }
    public void updateData(ArrayList<Item> movies) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback_thumps((ArrayList<Item>) movies, (ArrayList<Item>) this.movies));
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(this);
    }

    public Item getItem(int position) {
        if (position < 0 || position >= movies.size()) {
            throw new InvalidParameterException("INVALID IDX");
        }
        return movies.get(position);
          }

    public void clearData() {
        movies.clear();
        notifyDataSetChanged();
    }

    private void OnBind(Item data, MyViewHolder_Search_Result_Adapter viewHolder) {
        viewHolder.descreption.setText(data.getSnippet().getDescription());
        viewHolder.main_Vedio_title.setText(data.getSnippet().getTitle());
        Glide.with(c).load(data.getSnippet().getThumbnails().getMedium().getUrl()).into(viewHolder.thump);
    }

    public List<Item> getItems() {
        return movies;
    }

    public interface OnSrelection {
        void selection(int id);
    }
}
