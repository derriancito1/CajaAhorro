package com.ahorro.specalpha.caja_ahorro.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Prestamo {

    private String id;
    @SerializedName("prestamos")
    private List<MisPrestamos> misprestamos;

    public Prestamo() {
    }

    public Prestamo(String id, List<MisPrestamos> misprestamos) {
        this.id = id;
        this.misprestamos = misprestamos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MisPrestamos> getMisprestamos() {
        return misprestamos;
    }

    public void setMisprestamos(List<MisPrestamos> misprestamos) {
        this.misprestamos = misprestamos;
    }

    public static MisPrestamos parseJSON(String response){
        Gson gson = new GsonBuilder().create();
        MisPrestamos misprestamos = gson.fromJson(response, MisPrestamos.class);
        return misprestamos;
    }
}
