package com.lib.model;

import com.google.gson.annotations.SerializedName;

public class FlickrResponse {

    @SerializedName("photos")
    private FlickrImageList flickrImageList;


    /**
     * @return The photos
     */
    public FlickrImageList getFlickrImageList() {
        return flickrImageList;
    }

    /**
     * @param flickrImageList The photos
     */
    public void setFlickrImageList(FlickrImageList flickrImageList) {
        this.flickrImageList = flickrImageList;
    }


}