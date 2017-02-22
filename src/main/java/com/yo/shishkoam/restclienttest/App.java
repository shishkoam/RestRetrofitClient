package com.yo.shishkoam.restclienttest;

import android.app.Application;

import com.yo.shishkoam.restclienttest.api.EasyPayApi;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 21.02.2017
 */
public class App extends Application {

    private static EasyPayApi easyPayApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Interceptor contentType = (Interceptor.Chain chain) -> {
            Request original = chain.request();
            Request.Builder request = original.newBuilder()
//                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept", "application/json")
                    .header("AppId", "ae075abd-bb3b-4f8d-b96a-81ef3419dad2")
                    .header("RequestedSessionId", "6849322e-7cef-4106-817b-6af8acbc6a38")
                    .header("PageId", " f1b77f45-20a5-4ab5-8c7e-95f7c666feb0");
            request.method(original.method(), original.body());
            return chain.proceed(request.build());
        };

        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(contentType);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://sandbox.easypay.ua:8084/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        easyPayApi = retrofit.create(EasyPayApi.class);
    }

    public static EasyPayApi getApi() {
        return easyPayApi;
    }
}
