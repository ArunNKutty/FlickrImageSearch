package com.imagesearch.loader;

import android.content.Context;

import com.imagesearch.apiclient.AppClient;
import com.lib.model.FlickrResponse;

import java.io.IOException;

import retrofit2.Call;


/**
 * @author akutty
 */
public class ImageListLoader extends BaseRetrofitTaskLoader<FlickrResponse> {

    private String mSearch;
    private String mPagination;
    private String mFormat;
    private String mJsonCallback;

    public ImageListLoader(Context context,String search, String pagination, String format, String jsonCallback) {
        super(context);
        this.mSearch = search;
        this.mPagination = pagination;
        this.mFormat =format;
        this.mJsonCallback =jsonCallback;
    }

    @Override
    protected Call<FlickrResponse> call() throws IOException {
        AppClient.IFlickrImageService service = AppClient.getRetrofitInstance().create(AppClient.IFlickrImageService.class);
        return service.getAllImages(mSearch,mPagination,mFormat,mJsonCallback);
    }
}
