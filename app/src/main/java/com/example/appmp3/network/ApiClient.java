package com.example.appmp3.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // https://firebasestorage.googleapis.com/v0/b/appmp3-4861b.appspot.com/o/mp3%2FzGnnSoHcBueKQPpPCjB4GxzGc5P2-mp3-1631879615242?alt=media&token=c7566b2d-f4b9-4a66-a063-c419d0fce2f8
    private static final String BASE_URL = "https://firebasestorage.googleapis.com/v0/b/appmp3-4861b.appspot.com/o/mp3%2F";
    private static Retrofit retrofitInstance;

    private static ApiInterface ApiClient() {
        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnsafeOkHttpClient())
                    .build();
        }
        return retrofitInstance.create(ApiInterface.class);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }
}
