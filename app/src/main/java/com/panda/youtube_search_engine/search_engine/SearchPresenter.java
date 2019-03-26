package com.panda.youtube_search_engine.search_engine;

import android.gesture.Prediction;
import android.support.annotation.NonNull;
import com.panda.youtube_search_engine.data.search_response.SearchResponse;
import com.panda.youtube_search_engine.data.source_repository.SearchRepositry;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM;
import qenawi.panda.a_predator.network_Handeler.A_Predator_Throwable;
import qenawi.panda.a_predator.network_Handeler.CService_DBase;
import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchPresenter implements SearchContract.Presnter, A_Predator_NWM.RequistResuiltCallBack {
    private final SearchRepositry mSearchRepositry;
    private final SearchContract.View mView;
    private final boolean mFirstLoad = true;
    private String Query = "naruto";

    public SearchPresenter(@NonNull SearchRepositry mSearchRepositry, @NonNull SearchContract.View mView)
    {
        this.mSearchRepositry = checkNotNull(mSearchRepositry);
        this.mView = checkNotNull(mView);
        mView.setPresenter(this);
    }

    @Override
    public void loadSearchResult(boolean fromRemote) {
        /// case from remote load data with out clearing the
        loadSearchResult(fromRemote || mFirstLoad, true);
    }
    @Override
    public void refresh(String query)
    {
        if (query != null) {
            this.Query = query;
        }
        mSearchRepositry.refresh();// Clear cache and reload data
    }

    @Override
    public void paginate() {
        mSearchRepositry.paginate();
    }

    private void loadSearchResult(@NonNull boolean fromRemote, @NonNull boolean showLoading) {
        // load data from online repo
        mSearchRepositry.getSearchResponse(Query, null, this);
    }

    @Override
    public void start() {
        loadSearchResult(false);
    }

    @Override
    public <T extends CService_DBase> void Sucess(T Resposne) {
        Timber.tag("SearchSz").v("%d", ((SearchResponse) Resposne).getItems().size());
        if(((SearchResponse)Resposne).getItems().isEmpty())
        {
            mView.showNoResult();
        }else
        mView.showSearchResult(((SearchResponse) Resposne).getItems());
    }

    @Override
    public void Faild(A_Predator_Throwable error) {
        Timber.tag("SearchSz_throw").v(error.getACtion());
    }

}
