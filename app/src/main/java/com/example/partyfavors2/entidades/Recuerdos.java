package com.example.partyfavors2.entidades;

import java.io.Serializable;

public class Recuerdos implements Serializable {

    private int id;
    private int idUsuario;
    private String titulo;
    private String nota;
    private String imagen;
    private String ubicacion;

    public Recuerdos(int id, int idUsuario, String titulo, String nota, String imagen, String ubicacion){
    this.id = id;
    this.idUsuario = idUsuario;
    this.titulo = titulo;
    this.nota = nota;
    this.imagen = imagen;
    this.ubicacion = ubicacion;
    }

    public Recuerdos() {

    }

    public int getId() { return id; }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNota() {
        return nota;
    }

    public String getImagen() {
         return imagen;
     }

    public String getUbicacion() {
         return ubicacion;
     }


    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
