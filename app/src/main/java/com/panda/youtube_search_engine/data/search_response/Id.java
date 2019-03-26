
package com.panda.youtube_search_engine.data.search_response;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Id {

    @SerializedName("kind")
    private String mKind;
    @SerializedName("videoId")
    private String mVideoId;

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public String getVideoId() {
        return mVideoId;
    }

    public void setVideoId(String videoId) {
        mVideoId = videoId;
    }

}
