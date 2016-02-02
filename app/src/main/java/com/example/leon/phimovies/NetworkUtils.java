package com.example.leon.phimovies;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * @author Leon
 */
public class NetworkUtils {

    public static final int READ_TIMEOUT = 60;
    public static final int CONNECT_TIMEOUT = 120;
    public static final int WRITE_TIMEOUT = 40;
    private final Retrofit mRetrofit;

    private NetworkUtils() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkUtils getInstance() {
        return Holder.INSTANCE;
    }

    private static OkHttpClient buildClient() {
        final OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        client.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        client.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        return client;
    }

    public <T> T getService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    private static class Holder {
        private static final NetworkUtils INSTANCE = new NetworkUtils();
    }
}
