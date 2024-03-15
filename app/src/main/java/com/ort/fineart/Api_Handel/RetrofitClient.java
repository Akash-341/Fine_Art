package com.ort.fineart.Api_Handel;


import static com.ort.fineart.Api_Handel.All_URL.BASE_URL;

import com.ort.fineart.Api_Handel.All_URL;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String url= BASE_URL;
    public static RetrofitClient client;
    public static Retrofit retrofit;

    RetrofitClient(){
        retrofit=new Retrofit.Builder()

                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (client==null)
            client=new RetrofitClient();
        return client;
    }
    public ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
