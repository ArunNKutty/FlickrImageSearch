package com.imagesearch.apiclient;

/**
 * @author akutty
 */
public interface APIConstants extends BaseConstants {

    String BASE_URL = "https://api.flickr.com/services/rest/";
    String FLICKR_API_KEY = "7bef783ac5d0f22bf1c828b27fd9de15";
    String FLICK_IMAGE_SEARCH = "?method=flickr.photos.search&api_key=" + FLICKR_API_KEY;
    boolean DEBUG = true;
}
