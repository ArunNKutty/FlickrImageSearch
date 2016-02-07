package com.imagesearch.loader.callback;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.imagesearch.loader.ImageListLoader;
import com.imagesearch.loader.LoaderId;
import com.imagesearch.loader.LoaderResult;
import com.lib.listener.INetworkListener;
import com.lib.model.FlickrResponse;


/**
 * @author akutty
 */
public class ImageListLoaderCallback extends BaseLoaderCallback<FlickrResponse> {

    private static final String ARG_SEARCH_STRING = "search";
    private static final String ARG_PAGINATION = "pagination";
    private static final String ARG_FORMAT = "format";
    private static final String ARG_JSON_CALLBACK = "json_callback";


    private ImageListLoaderCallback(Context context, INetworkListener<FlickrResponse> listener) {
        super(context, listener);
    }

    //ToDo- Pass Object if time permits
    public static void initLoader(Context context, LoaderManager manager,
                                  INetworkListener<FlickrResponse> listener,String search,
                                  String pagination,String format ,String jsonCallBack) {

        Bundle bundle = new Bundle();
        bundle.putString(ARG_SEARCH_STRING, search);
        bundle.putString(ARG_PAGINATION, pagination);
        bundle.putString(ARG_FORMAT, format);
        bundle.putString(ARG_JSON_CALLBACK, jsonCallBack);

        ImageListLoaderCallback callback = new ImageListLoaderCallback(context, listener);
        manager.initLoader(LoaderId.FLICKR_IMAGE_LOADER, bundle, callback);
    }

    public static void destroyLoader(LoaderManager manager) {
        manager.destroyLoader(LoaderId.FLICKR_IMAGE_LOADER);
    }

    @Override
    public Loader<LoaderResult<FlickrResponse>> onCreateLoader(int id, Bundle args) {

        String searchString = args.getString(ARG_SEARCH_STRING);
        String pagination = args.getString(ARG_PAGINATION);
        String format = args.getString(ARG_FORMAT);
        String jsonCallback = args.getString(ARG_JSON_CALLBACK);

        return new ImageListLoader(getContext(),searchString,pagination,format,jsonCallback);
    }
}
