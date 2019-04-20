package com.muzi.retrofitexample.http;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.platform.Platform.WARN;

/**
 * 作者: lipeng
 * 时间: 2019/3/4
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class RxHttp {

    // 网络请求超时时间值(s)
    private static final int DEFAULT_TIMEOUT = 30;

    private static RxHttp instance;
    private Retrofit retrofit;

    protected static RxHttp getInstance() {
        if (instance == null) {
            instance = new RxHttp();
        }
        return instance;
    }

    public RxHttp() {
        //创建一个OkHttpClient
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                //超时时间
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                //打印参数
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Platform.get().log(WARN, message, null);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                // 失败后尝试重新请求
                .retryOnConnectionFailure(true);


        //构建Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com/")
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 根据接口获取对应api
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getApi(Class<T> clazz) {
        T service = RxHttp.getInstance().retrofit.create(clazz);
        return service;
    }

}
