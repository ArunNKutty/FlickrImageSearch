package com.lib.listener;


/**
 * Created by akutty
 */
public interface INetworkListener<T> {

    public void onSuccess(T data);

    public void onError(Exception ex);

}
