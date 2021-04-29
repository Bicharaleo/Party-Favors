package com.example.partyfavors2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class Utilidades {
    public final static String TABLA_USUARIOS = "usuarios";
    public final static String ID_USUARIO = "id";
    public final static String CAMPO_USUARIO = "usuario";
    public final static String CAMPO_NOMBRE = "nombre";
    public final static String CAMPO_APELLIDO = "apellido";
    public final static String CAMPO_CONTRASEÑA = "contraseña";
    public final static String CREAR_TABLA_USUARIOS = "CREATE TABLE "+TABLA_USUARIOS+" ("+ID_USUARIO+" INTEGER PRIMARY KEY, "+CAMPO_USUARIO+" TEXT, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_APELLIDO+" TEXT, "+CAMPO_CONTRASEÑA+" TEXT)";
}