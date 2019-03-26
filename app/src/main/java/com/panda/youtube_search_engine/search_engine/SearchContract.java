package com.panda.youtube_search_engine.search_engine;

import com.panda.youtube_search_engine.BasePresenter;
import com.panda.youtube_search_engine.BaseView;
import com.panda.youtube_search_engine.data.search_response.Item;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView<Presnter> {
        void showSearchResult(List<Item> my_data);

        void showNoResult();
    }

    interface Presnter extends BasePresenter {
        void loadSearchResult(boolean fromRemote);
        void refresh(String query);
        void paginate();
    }
}
