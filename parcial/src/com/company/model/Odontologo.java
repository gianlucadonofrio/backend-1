package com.company.model;

public class Odontologo {
    private Long id;
    private String nombre;
    private String apellido;
    private int nroMatricula;

    public Odontologo(String nombre, String apellido, int nroMatricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroMatricula = nroMatricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNroMatricula() {
        return nroMatricula;
    }

    public void setNroMatricula(int nroMatricula) {
        this.nroMatricula = nroMatricula;
    }
}
