package com.ahorro.specalpha.caja_ahorro.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

//    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public  static final String BASE_URL = "http://cajadeahorro.diversitisystems.com/android/";
    private static Retrofit retrofit = null;
    public static final String APPKREY = "804b2a484447ae6af937f58410715246";

    public static Retrofit getApi(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
