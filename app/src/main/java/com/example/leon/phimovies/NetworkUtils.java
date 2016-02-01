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
    private static final String TAG = NetworkUtils.class.getName();
    private final Retrofit mRetrofit;

    private NetworkUtils() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
//        client.interceptors().add(chain -> {
//            Response response = chain.proceed(chain.request());
//            final HttpUrl httpUrl = chain.request().httpUrl();
//            Log.d(TAG, "intercept: " + httpUrl.toString());
//            return response;
//        });
        return client;
    }

    public <T> T getService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    private static class Holder {
        private static final NetworkUtils INSTANCE = new NetworkUtils();
    }
}
