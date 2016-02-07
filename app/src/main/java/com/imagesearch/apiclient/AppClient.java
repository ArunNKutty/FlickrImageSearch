package com.imagesearch.apiclient;


import com.lib.model.FlickrResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Class to access web services and paring data into model objects
 *
 * @author akutty
 */
public class AppClient implements APIConstants {

    private static Retrofit mRetrofit;
    /**
     * @return retofit instance
     */
    public static Retrofit getRetrofitInstance() {
        if (mRetrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            mRetrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }

    public interface IFlickrImageService {
        @GET(FLICK_IMAGE_SEARCH)
        Call<FlickrResponse> getAllImages(@Query("text") String searchString,
                                          @Query("per_page") String pagination, @Query("format") String format,
                                          @Query("nojsoncallback") String jsonCallBack);
    }

}
