
package com.panda.youtube_search_engine.data.search_response;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Thumbnails {

    @SerializedName("default")
    private Default mDefault;
    @SerializedName("high")
    private High mHigh;
    @SerializedName("medium")
    private Medium mMedium;

    public Default getDefault() {
        return mDefault;
    }

    public void setDefault(Default mdefault) {
        mDefault = mdefault;
    }

    public High getHigh() {
        return mHigh;
    }

    public void setHigh(High high) {
        mHigh = high;
    }

    public Medium getMedium() {
        return mMedium;
    }

    public void setMedium(Medium medium) {
        mMedium = medium;
    }

}
