package com.example.partyfavors2.utilidades;

public class Utilidades {
    public final static String TABLA_USUARIOS = "usuarios";
    public final static String ID_USUARIO = "id";
    public final static String CAMPO_USUARIO = "usuario";
    public final static String CAMPO_NOMBRE = "nombre";
    public final static String CAMPO_APELLIDO = "apellido";
    public final static String CAMPO_CONTRASEÑA = "contraseña";
    public final static String CREAR_TABLA_USUARIOS = "CREATE TABLE "+TABLA_USUARIOS+" ("+ID_USUARIO+" INTEGER PRIMARY KEY, "+CAMPO_USUARIO+" TEXT, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_APELLIDO+" TEXT, "+CAMPO_CONTRASEÑA+" TEXT)";

    public final static String TABLA_RECUERDOS = "recuerdos";
    public final static String ID_RECUERDO = "id_recuerdo";
    public final static String ID_USUARIO_RECUERDO = "id_usuario";
    public final static String CAMPO_TITULO = "titulo";
    public final static String CAMPO_NOTA = "nota";
    public final static String CAMPO_IMAGEN = "imagen";
    public final static String CAMPO_UBICACION = "ubicacion";
    public final static String CREAR_TABLA_RECUERDOS = "CREATE TABLE "+TABLA_RECUERDOS+" ("+ID_RECUERDO+" INTEGER PRIMARY KEY, "+ID_USUARIO_RECUERDO+" INTEGER, "+CAMPO_TITULO+" TEXT, "+CAMPO_NOTA+" TEXT, "+CAMPO_IMAGEN+" TEXT, "+CAMPO_UBICACION+" TEXT)";

}