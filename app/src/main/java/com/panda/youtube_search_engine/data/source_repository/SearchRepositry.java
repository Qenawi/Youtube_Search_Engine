package com.panda.youtube_search_engine.data.source_repository;

import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;
import android.util.Log;
import com.panda.youtube_search_engine.data.search_response.SearchResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NetWorkManger;
import qenawi.panda.a_predator.network_Handeler.A_Predator_Throwable;
import qenawi.panda.a_predator.network_Handeler.CService_DBase;
import timber.log.Timber;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public class SearchRepositry implements SearchDataSource {
    private static SearchRepositry INISTANCE;
    private final SearchDataSource mRemoteDataSource;
    SearchResponse searchCache;
    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    private boolean mCacheIsDirty = false;
    private boolean mPaginate = false;
    private SearchRepositry(@NonNull SearchDataSource remoteDataSource)
    {
        mRemoteDataSource = checkNotNull(remoteDataSource);
                        }
    public static SearchRepositry getInistance(@NonNull SearchDataSource remoteDataSource) {
        if (INISTANCE == null) {
            INISTANCE = new SearchRepositry(remoteDataSource);
        }
        return INISTANCE;
    }
    @Override
    public void getSearchResponse(@NotNull String q, @Nullable String next_page_token, A_Predator_NWM.RequistResuiltCallBack callBack)
    {
        Timber.tag("AhemdTAG").v(q);
        checkNotNull(q);
        checkNotNull(callBack);
        if ((searchCache != null && !mCacheIsDirty) && !mPaginate) {
            /*
             cache is not empty & load from remote is not active && pagination flag is not active
             */
            callBack.Sucess(searchCache);
            return;
        } else if (mPaginate) {
            checkNotNull(searchCache); // throw null Pointer exception .->....<-.
            getSearchResponseFromNW(q, checkNotNull(searchCache.getNextPageToken()), callBack);
        } else if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getSearchResponseFromNW(q, null, callBack);
        } else {
            // load from local Db and populate cache
        }
    }

    private void getSearchResponseFromNW(@NotNull String q, @Nullable String next_page_token, @NonNull final A_Predator_NWM.RequistResuiltCallBack callBack) {
        mRemoteDataSource.getSearchResponse(q, next_page_token, new A_Predator_NWM.RequistResuiltCallBack() {
            @Override
            public <T extends CService_DBase> void Sucess(T Resposne) {
                refreshCache((SearchResponse) Resposne);
                callBack.Sucess(searchCache);
            }

            @Override
            public void Faild(A_Predator_Throwable error)
            {
                callBack.Faild(error);
            }
        });
    }

    @Override
    public void refresh() {
        mCacheIsDirty = true;
        mPaginate = false;
    }

    @Override
    public void paginate() {
        mPaginate = true;
    }

    /**
     * Used to force {@link #getInistance(SearchDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INISTANCE = null;
    }

    private void refreshCache(SearchResponse mResponse)
    {
        if (searchCache == null) {
            searchCache = new SearchResponse();
        }
        if (mPaginate) {
            searchCache.getItems().addAll(mResponse.getItems());
            searchCache.setNextPageToken(mResponse.getNextPageToken());
        } else
            searchCache = mResponse;
        mCacheIsDirty = false;
    }
}
