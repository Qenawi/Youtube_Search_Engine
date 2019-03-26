
package com.panda.youtube_search_engine.data.search_response;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Item {

    @SerializedName("etag")
    private String mEtag;
    @SerializedName("id")
    private Id mId;
    @SerializedName("kind")
    private String mKind;
    @SerializedName("snippet")
    private Snippet mSnippet;

    public String getEtag() {
        return mEtag;
    }

    public void setEtag(String etag) {
        mEtag = etag;
    }

    public Id getId() {
        return mId;
    }

    public void setId(Id id) {
        mId = id;
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public Snippet getSnippet() {
        return mSnippet;
    }

    public void setSnippet(Snippet snippet) {
        mSnippet = snippet;
    }

}
