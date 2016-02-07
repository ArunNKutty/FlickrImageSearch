package com.imagesearch.loader.callback;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.imagesearch.loader.LoaderResult;
import com.lib.listener.INetworkListener;
import com.lib.util.NetworkUtil;

import java.lang.ref.WeakReference;


/**
 * @author akutty
 */
public abstract class BaseLoaderCallback<T> implements LoaderManager.LoaderCallbacks<LoaderResult<T>> {

    private final Context mContext;
    private final WeakReference<INetworkListener<T>> mListener;

    public BaseLoaderCallback(Context context, INetworkListener<T> listener) {
        mContext = context;
        mListener = new WeakReference<INetworkListener<T>>(listener);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onLoadFinished(Loader<LoaderResult<T>> loader, LoaderResult<T> data) {
        INetworkListener<T> listener = mListener.get();
        if (listener == null) {
            return;
        }
        if (data != null) {
            if (data.getData() != null) {
                listener.onSuccess(data.getData());
            } else if (data.getException() != null) {
                listener.onError(data.getException());
            }
        } else {
            // Worst case if both data and exception are null.
            listener.onError(new NetworkUtil.RetrofitLoaderException());
        }
    }

    @Override
    public void onLoaderReset(Loader<LoaderResult<T>> loader) {
        // do nothing
    }

}
