package com.ahorro.specalpha.caja_ahorro.Models;

import com.google.gson.annotations.Expose;

public class User {

    private String nombre;
    private String usuario;


    public User() {
    }

    public User(String nombre, String usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
