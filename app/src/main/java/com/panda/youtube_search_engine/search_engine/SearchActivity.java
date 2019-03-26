package com.panda.youtube_search_engine.search_engine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.Carbon;
import carbon.widget.EditText;
import com.panda.youtube_search_engine.R;
import com.panda.youtube_search_engine.data.search_response.Item;
import com.panda.youtube_search_engine.data.source_repository.SearchRepositry;
import com.panda.youtube_search_engine.data.source_repository.remote.SearchDataSourceRemote;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, A_Predator_NWM.BaseActionHandeler {
    SearchContract.Presnter mPresenter;// call back to use from any fragment with in activity
    SearchPresenter presenter;
    @BindView(R.id.search_rv)
    carbon.widget.RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.search_query)
    EditText searchQuery;
    private Search_Result_adapter adapter;
    boolean is_lastPage = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        presenter = new SearchPresenter(SearchRepositry.getInistance(SearchDataSourceRemote.getinistance(this, this)), this);
        presenter.start();
        initview();
    }

    private void initview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        recyclerView.setHasFixedSize(true);
        adapter = new Search_Result_adapter(new ArrayList<>(), id ->
        {
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setPagination(new carbon.widget.RecyclerView.Pagination((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            protected boolean isLoading() {
                return refreshLayout.isRefreshing();
            }

            @Override
            protected boolean isLastPage() {
                return is_lastPage;
            }

            @Override
            protected void loadNextPage() {
                refreshLayout.setRefreshing(true);
                mPresenter.paginate();//enable paginate Flag
                mPresenter.loadSearchResult(true);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                               @Override
                                               public void onRefresh() {
                                                   is_lastPage = false;
                                                   refreshLayout.setRefreshing(true);
                                                   mPresenter.refresh(null);
                                                   mPresenter.loadSearchResult(true);
                                               }
                                           }
        );
    }
    @OnClick(R.id.search)
    public void OnSearch_Click(View view) {
        is_lastPage = false;
        mPresenter.refresh(searchQuery.getText().toString());
        mPresenter.loadSearchResult(true);
    }
    @Override
    public void showSearchResult(List<Item> data)
        {
        refreshLayout.setRefreshing(false);
        if (data.size() < 15)
        {
            is_lastPage = true;
        }
        adapter.replaceData(data);
        }
    @Override
    public void showNoResult()
      {
         Timber.tag("SnBar").v("No Result. .. ");
         Toast.makeText(SearchActivity.this, "No Search Result Avail For This Search Result", Toast.LENGTH_SHORT).show();
       }
    @Override
    public void setPresenter(SearchContract.Presnter presenter)
    {
        mPresenter = presenter;
    }
    //..............................
    @Override
    public void ShowSnackBar(String t)
    {
        Timber.tag("SnBar").v(t);
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HandelUnAuthAction() {

    }

    @Override
    public void DetacHHandelers() {

    }
}
