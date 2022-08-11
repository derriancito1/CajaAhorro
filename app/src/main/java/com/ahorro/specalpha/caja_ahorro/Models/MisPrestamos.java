package com.ahorro.specalpha.caja_ahorro.Models;

import com.google.gson.annotations.SerializedName;

public class MisPrestamos {

//    private String nombre;
    private String prestamo;
    /*@SerializedName("fecha_prestamo")
    private String fechaPrestamo;
    @SerializedName("fecha_pago")
    private String fechaPago;
    @SerializedName("cantidad_pagada")
    private String cantidadPagada;
    private String meses;
    private String dias;
    private String intereses;
    @SerializedName("interes_deuda")
    private String interesDeuda;*/

    public MisPrestamos() {
    }

    public MisPrestamos(/*String nombre,*/ String prestamo/*, String fechaPrestamo, String fechaPago, String cantidadPagada, String meses, String dias, String intereses, String interesDeuda*/) {
       /* this.nombre = nombre;*/
        this.prestamo = prestamo;
        /*this.fechaPrestamo = fechaPrestamo;
        this.fechaPago = fechaPago;
        this.cantidadPagada = cantidadPagada;
        this.meses = meses;
        this.dias = dias;
        this.intereses = intereses;
        this.interesDeuda = interesDeuda;*/
    }

    /*public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }*/

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

   /* public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getCantidadPagada() {
        return cantidadPagada;
    }

    public void setCantidadPagada(String cantidadPagada) {
        this.cantidadPagada = cantidadPagada;
    }

    public String getMeses() {
        return meses;
    }

    public void setMeses(String meses) {
        this.meses = meses;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public String getInteresDeuda() {
        return interesDeuda;
    }

    public void setInteresDeuda(String interesDeuda) {
        this.interesDeuda = interesDeuda;
    }*/
}
