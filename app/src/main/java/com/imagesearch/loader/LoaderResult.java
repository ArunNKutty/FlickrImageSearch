package com.imagesearch.loader;


/**
 * @author akutty
 */
public class LoaderResult<T> {

    private Exception mException;

    private T mData;

    public void setException(Exception exception) {
        mException = exception;
    }

    public Exception getException() {
        return mException;
    }

    public void setData(T data) {
        mData = data;
    }

    public T getData() {
        return mData;
    }

}
