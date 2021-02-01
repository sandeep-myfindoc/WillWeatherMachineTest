package com.willyweathermachinetest.service;


import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.willyweathermachinetest.view.WillyWeatherApplication;
import com.willyweathermachinetest.util.CommonUtility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Rest client
 */
public class RestClient {
    private static Retrofit retrofit = null;
    private NetworkConnection networkConnection;
    public RestClient(NetworkConnection networkConnection) {
        this.networkConnection = networkConnection;
    }
    public  ApiService getClient(){
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(this.networkConnection.getBaseUrl())
                    .client(getOkHttpClient())// setting regarding connection and handle failure cases
                    .addConverterFactory(GsonConverterFactory.create(gson)) // used to conver json/xml to pojo class.
                    .build();
        }
        ApiService api = retrofit.create(ApiService.class);
        return api;
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder httpClient=null;
        try {
            httpClient = new OkHttpClient.Builder()
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    })
                    .cache(provideCache())
                    .callTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(offLineInterceptor())
                    .addNetworkInterceptor(onLineInterceptor());

            return httpClient.build();
        }catch (Exception e){
            new RuntimeException();
        }
        return httpClient.build();
    }

    private static Cache provideCache(){
        Cache cache=null;
        try{
            int cacheSize = 10 * 1024 * 1024;
            cache = new Cache(new File(WillyWeatherApplication.getInstance().getCacheDir(),"http-cache"),cacheSize);
        }catch(Exception ex){
            Log.d("error creating cache",ex.getMessage()+"");
        }
        return cache;
    }
    private static Interceptor onLineInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("TAG", "network interceptor: called.");

                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(5, TimeUnit.SECONDS)
                        .build();

                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + 60)
                        .removeHeader("Pragma")
                        .build();
            }
        };
    }
    private static Interceptor offLineInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("TAG", "offline interceptor: called.");
                Request request = chain.request();

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!new CommonUtility(WillyWeatherApplication.getInstance()).checkInternetConnection()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();
                    Log.d("cacheControl",cacheControl.toString());
                    request = request.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + 60)
                            .removeHeader("Pragma")
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }
    public static Retrofit getRetrofit(){
        return retrofit;
    }
}
