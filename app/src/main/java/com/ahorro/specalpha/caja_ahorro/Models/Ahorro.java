package com.ahorro.specalpha.caja_ahorro.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SPEC ALPHA on 31/10/2018.
 */

public class Ahorro {
    @SerializedName("cantidad_actual")
    private String name;

    public Ahorro() {
    }

    public Ahorro(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
