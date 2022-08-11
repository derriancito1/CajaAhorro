package com.ahorro.specalpha.caja_ahorro.API.APIServices;

import com.ahorro.specalpha.caja_ahorro.Models.Ahorro;
import com.ahorro.specalpha.caja_ahorro.Models.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SPEC ALPHA on 02/11/2018.
 */

public interface WebService {

    @GET("weather")
    Call<City> getCity(@Query("q") String city, @Query("appid") String key);


    @GET("caja_ahorro")
    Call<Ahorro> getAhorro(@Query("id") String id);

}
