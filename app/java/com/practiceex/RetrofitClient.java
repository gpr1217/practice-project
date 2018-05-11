package com.practiceex;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Pratyu on 12/3/2017.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient(String baseUrl){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
