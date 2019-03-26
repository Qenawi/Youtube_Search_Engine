package com.panda.youtube_search_engine.data.source_repository.remote;

import android.content.Context;
import com.panda.youtube_search_engine.data.search_response.Item;
import com.panda.youtube_search_engine.data.search_response.SearchResponse;
import com.panda.youtube_search_engine.data.source_repository.SearchDataSource;
import io.reactivex.Single;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NetWorkManger;
import qenawi.panda.a_predator.network_Handeler.A_Predator_Throwable;
import qenawi.panda.a_predator.network_Handeler.CService_DBase;

import java.util.HashMap;
import java.util.List;

/*SingleTone*/
public class SearchDataSourceRemote implements SearchDataSource
{
    private static SearchDataSourceRemote INSTANCE;
    private static final int RequestTimeOut = 300;
    private static final int Max_per_search = 15;
    private static final String Part = "snippet";
    private static final String Type = "video";
    private static final String MyKey = "AIzaSyAcmzApxYnW0DRoZXPS8ZQav6f5UbMmspM";

    private A_Predator_NetWorkManger a_predator_netWorkManger;

    public static SearchDataSourceRemote getinistance(Context c, A_Predator_NWM.BaseActionHandeler apredtor_callBack) {
        if (INSTANCE == null) {
            INSTANCE = new SearchDataSourceRemote(c, apredtor_callBack);
        }
        return INSTANCE;
    }

    private SearchDataSourceRemote(Context context, A_Predator_NWM.BaseActionHandeler apredtor_callBack) {
        a_predator_netWorkManger = new A_Predator_NetWorkManger(context, apredtor_callBack);
    }

    ;

    @Override
    public void getSearchResponse(@NotNull final String q, @Nullable final String t, A_Predator_NWM.RequistResuiltCallBack callBack) {
        HashMap<String, String> headers = new HashMap<String, String>();
        HashMap<String, Object> body = new HashMap<String, Object>() {{
            put("part", Part);
            put("maxResults", Max_per_search);
            put("q", q);
            put("key", MyKey);
            put("type", Type);
        }};
        if (t != null) {
            body.put("pageToken", t);
        }
        String Url = "https://www.googleapis.com/youtube/v3/search";
        //---------------
        a_predator_netWorkManger.FetchData(new SearchResponse(), headers, Url, body, callBack);
    }

    @Override
    public void refresh()
    {


    }
    @Override
    public void paginate() {

    }
}
