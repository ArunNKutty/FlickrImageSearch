package com.lib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Util methods for checking network
 *
 * @author akutty
 */
public class NetworkUtil {

    private NetworkUtil() {
        // hide constructor
    }

    /**
     * Checks if access to the network is available on the device.
     *
     * @param context the Context
     * @return true if the network is available.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    /**
     * Exception to be thrown when the network if unavailable.
     */
    public static class NetworkUnavailableException extends Exception {

        private static final long serialVersionUID = 7928189409808992530L;

        public NetworkUnavailableException() {
            super();
        }
    }


    public static class RetrofitLoaderException extends Exception {

        private String message = null;
        private int errorCode;

        public RetrofitLoaderException() {
            super();
        }

        public RetrofitLoaderException(String message, int errorCode) {
            super(message);
            this.message = message;
            this.errorCode = errorCode;
        }

        public RetrofitLoaderException(Throwable cause) {
            super(cause);
        }

        @Override
        public String toString() {
            return message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public int getErrorCode() {
            return errorCode;
        }
    }
}
