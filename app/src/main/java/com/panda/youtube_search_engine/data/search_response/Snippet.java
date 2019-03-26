
package com.panda.youtube_search_engine.data.search_response;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Snippet {

    @SerializedName("channelId")
    private String mChannelId;
    @SerializedName("channelTitle")
    private String mChannelTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("liveBroadcastContent")
    private String mLiveBroadcastContent;
    @SerializedName("publishedAt")
    private String mPublishedAt;
    @SerializedName("thumbnails")
    private Thumbnails mThumbnails;
    @SerializedName("title")
    private String mTitle;

    public String getChannelId() {
        return mChannelId;
    }

    public void setChannelId(String channelId) {
        mChannelId = channelId;
    }

    public String getChannelTitle() {
        return mChannelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        mChannelTitle = channelTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLiveBroadcastContent() {
        return mLiveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        mLiveBroadcastContent = liveBroadcastContent;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        mPublishedAt = publishedAt;
    }

    public Thumbnails getThumbnails() {
        return mThumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        mThumbnails = thumbnails;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
