package com.ahorro.specalpha.caja_ahorro.Models;

import com.google.gson.annotations.SerializedName;

public class MiAhorro {
    private String ahorro;
    private String acciones;
    @SerializedName("valor_accion")
    private String valorAccion;
    @SerializedName("mi_dinero")
    private String miDinero;
    @SerializedName("ganancia_total")
    private String gananciaTotal;

    public MiAhorro() {
    }

    public MiAhorro(String ahorro, String acciones, String valorAccion, String miDinero, String gananciaTotal) {
        this.ahorro = ahorro;
        this.acciones = acciones;
        this.valorAccion = valorAccion;
        this.miDinero = miDinero;
        this.miDinero = gananciaTotal;
    }

    public String getAhorro() {
        return ahorro;
    }

    public void setAhorro(String ahorro) {
        this.ahorro = ahorro;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getValorAccion() {
        return valorAccion;
    }

    public void setValorAccion(String valorAccion) {
        this.valorAccion = valorAccion;
    }

    public String getMiDinero() {
        return miDinero;
    }

    public void setMiDinero(String miDinero) {
        this.miDinero = miDinero;
    }

    public String getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(String gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }
}
