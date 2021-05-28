package com.example.partyfavors2.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partyfavors2.database.DataBase;
import com.example.partyfavors2.adaptadores.ListAdap;
import com.example.partyfavors2.R;
import com.example.partyfavors2.entidades.Recuerdos;
import com.example.partyfavors2.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Feed extends AppCompatActivity {

    TextView usuarioView;
    String NOMBREUSUARIO;
    int IDUSUARIO;
    List<Recuerdos> listaRecuerdos = new ArrayList<>();
    ListAdap adaptador;
    ListView listViewRecuerdos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        listViewRecuerdos = findViewById(R.id.listViewRecuerdos);
        adaptador = new ListAdap(this, R.layout.list_view, listaRecuerdos);
        usuarioView = findViewById(R.id.usuarioView);
        IDUSUARIO = getIntent().getExtras().getInt("idUsuario");
        NOMBREUSUARIO = getIntent().getExtras().getString("nombreUsuario");
        usuarioView.setText("Bienvenido " + NOMBREUSUARIO + "!!");
        consultarRecordatorios();
    }

    public void abrirAdd (View view) {
        Intent intent = new Intent(this, Add.class);
        Bundle bundle = new Bundle();
        bundle.putInt("idUsuario", IDUSUARIO);
        bundle.putString("nombreUsuario", NOMBREUSUARIO);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void consultarRecordatorios() {
        DataBase conexionSQLiteHelper = new DataBase(this, "db_partyfavors", null, 1);
        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

        Recuerdos recuerdos = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_RECUERDOS + " WHERE " + Utilidades.ID_USUARIO_RECUERDO + " = " + IDUSUARIO, null);

        while (cursor.moveToNext()) {
            recuerdos = new Recuerdos();
            recuerdos.setTitulo(cursor.getString(2));
            recuerdos.setNota(cursor.getString(3));
            recuerdos.setImagen(cursor.getString(4));
            recuerdos.setUbicacion(cursor.getString(5));
            listaRecuerdos.add(recuerdos);
        }
        Collections.reverse(listaRecuerdos);
        listViewRecuerdos.setAdapter(adaptador);
    }
}






