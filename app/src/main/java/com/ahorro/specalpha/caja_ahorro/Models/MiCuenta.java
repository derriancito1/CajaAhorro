package com.ahorro.specalpha.caja_ahorro.Models;

import com.google.gson.annotations.SerializedName;

public class MiCuenta {
    @SerializedName("interes_total")
    private String interesTotal;
    @SerializedName("cantidad_actual")
    private String cantidadActual;
    @SerializedName("ahorro_mensual")
    private String ahorroMensual;
    private String nombre;
    private String telefono;
    private String tarjeta;
    private String email;

    public MiCuenta() {
    }

    public MiCuenta(String interesTotal, String cantidadActual, String ahorroMensual, String nombre, String telefono, String tarjeta, String email) {
        this.interesTotal = interesTotal;
        this.cantidadActual = cantidadActual;
        this.ahorroMensual = ahorroMensual;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tarjeta = tarjeta;
        this.email = email;
    }

    public String getInteresTotal() {
        return interesTotal;
    }

    public void setInteresTotal(String interesTotal) {
        this.interesTotal = interesTotal;
    }

    public String getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(String cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public String getAhorroMensual() {
        return ahorroMensual;
    }

    public void setAhorroMensual(String ahorroMensual) {
        this.ahorroMensual = ahorroMensual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
