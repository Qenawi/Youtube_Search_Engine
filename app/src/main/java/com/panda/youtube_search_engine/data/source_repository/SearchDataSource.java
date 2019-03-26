package com.panda.youtube_search_engine.data.source_repository;

import com.panda.youtube_search_engine.data.search_response.Item;
import io.reactivex.Single;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM;

import java.util.List;

public interface SearchDataSource
{
    /*getData Based On Search query*/
    /*Model Role Fetch Data and Notify Presenter That Data Is Ready   */
    /*   Fetching Data include online and offline                     */
    /*   if cache is dirty(remote) reload all data again              */
    /*   if pagination is enabled append to prev cache and return them */
    /*  data is cache is not cleared append  list to the prev data    */
    void getSearchResponse(@NotNull String q, @Nullable String next_page_token, A_Predator_NWM.RequistResuiltCallBack callBack);
   /**
    *
   refresh()-> set cache is dirty and cache is going to be cleared  to pe populated with new fresh data
   paginate()-> over look if remote or not and load data and them to them to current list
    */
    void refresh();
    void paginate();
                              }
