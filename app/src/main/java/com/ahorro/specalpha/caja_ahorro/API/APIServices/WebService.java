package com.ahorro.specalpha.caja_ahorro.API.APIServices;

import com.ahorro.specalpha.caja_ahorro.Models.Ahorro;
import com.ahorro.specalpha.caja_ahorro.Models.MiAhorro;
import com.ahorro.specalpha.caja_ahorro.Models.Prestamo;
import com.ahorro.specalpha.caja_ahorro.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SPEC ALPHA on 02/11/2018.
 */

public interface WebService {

    @GET("caja_ahorro")
    Call<Ahorro> getAhorro(@Query("id") String id);

    @GET("caja_ahorro")
    Call<User> getUser(@Query("username") String username, @Query("password") String password);

    @GET("caja_ahorro")
    Call<MiAhorro> getMiAhorro(@Query("miahorro") String miahorro, @Query("username") String username);

    @GET("caja_ahorro")
    Call<Prestamo> getMisPrestamos(@Query("misprestamos") String misprestamos, @Query("username") String username);

}
