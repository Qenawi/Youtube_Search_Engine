package com.panda.youtube_search_engine.search_engine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.ImageView;
import carbon.widget.TextView;
import com.panda.youtube_search_engine.R;

public class MyViewHolder_Search_Result_Adapter extends RecyclerView.ViewHolder
{

    @BindView(R.id.thump)
    ImageView thump;
    @BindView(R.id.textView)
    TextView main_Vedio_title;
    @BindView(R.id.desc)
    TextView descreption;
    public MyViewHolder_Search_Result_Adapter(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
     }
