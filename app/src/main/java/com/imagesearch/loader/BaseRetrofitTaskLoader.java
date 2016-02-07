
package com.imagesearch.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lib.model.APIError;
import com.lib.util.NetworkUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author akutty
 */
public abstract class BaseRetrofitTaskLoader<T> extends AsyncTaskLoader<LoaderResult<T>> {

    private LoaderResult<T> mResult;

    public BaseRetrofitTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mResult != null) {
            deliverResult(mResult);
        }
        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(LoaderResult<T> data) {
        mResult = data;
        if (mResult == null) {
            mResult = buildEmptyResult();
        }

        if (isStarted()) {
            super.deliverResult(mResult);
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();

        mResult = null;
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    public LoaderResult<T> loadInBackground() {
        try {
            Response<T> response = call().execute();

            if (response.isSuccess()) {
                LoaderResult<T> result = new LoaderResult<>();
                result.setData(response.body());
                return result;
            } else {
                // If we have valid error body, try to convert it, else return message we got from retrofit.
                if (response.errorBody() != null) {
                    APIError apiError = new Gson().fromJson(response.errorBody().string(), APIError.class);
                    if (apiError != null && !TextUtils.isEmpty(apiError.message)) {
                        return buildFailureResult(new NetworkUtil.RetrofitLoaderException(apiError.message, apiError.statusCode));
                    }
                }
                return buildFailureResult(new NetworkUtil.RetrofitLoaderException(response.message(), response.code()));
            }

        } catch (IOException e) {
            return buildFailureResult(e);
        }
    }

    protected abstract Call<T> call() throws IOException;

    protected LoaderResult<T> buildEmptyResult() {
        return null;
    }

    protected LoaderResult<T> buildFailureResult(Exception e) {
        LoaderResult<T> result = new LoaderResult<>();
        result.setException(e);
        return result;
    }
}
