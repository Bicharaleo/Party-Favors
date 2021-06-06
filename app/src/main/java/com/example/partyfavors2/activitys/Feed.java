package com.example.partyfavors2.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.partyfavors2.database.DataBase;
import com.example.partyfavors2.adaptadores.ListAdap;
import com.example.partyfavors2.R;
import com.example.partyfavors2.entidades.Recuerdos;
import com.example.partyfavors2.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Feed extends AppCompatActivity {

    TextView usuarioView, textoAlertDialog;
    String NOMBREUSUARIO;
    int IDUSUARIO;
    boolean canExitApp = false;
    List<Recuerdos> listaRecuerdos = new ArrayList<>();
    ListAdap adaptador;
    ListView listViewRecuerdos;
    Button botonCancelar, botonAceptar;
    LayoutInflater inflaterAlertDialog;
    ConstraintLayout feed;
    View  alertDialogInflate;
    DataBase conexionSQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        listViewRecuerdos = findViewById(R.id.listViewRecuerdos);
        usuarioView = findViewById(R.id.usuarioView);
        IDUSUARIO = getIntent().getExtras().getInt("idUsuario");
        NOMBREUSUARIO = getIntent().getExtras().getString("nombreUsuario");
        usuarioView.setText("Bienvenido " + NOMBREUSUARIO + "!!");
        feed = findViewById(R.id.feed);
        conexionSQLiteHelper = new DataBase(this, "db_partyfavors", null, 1);
        inflaterAlertDialog = getLayoutInflater();
        alertDialogInflate = inflaterAlertDialog.inflate(R.layout.alert_dialog, feed, false);
        cargarLista();
        listViewRecuerdos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                eliminarRecuerdo(pos);
                return true;
            }
        });
    }

    public void cargarLista(){
        listViewRecuerdos.setAdapter(null);
        listaRecuerdos.clear();
        adaptador = new ListAdap(this, R.layout.list_view, listaRecuerdos);
        listViewRecuerdos.setAdapter(adaptador);
        consultarRecuerdos();
    }

    public void abrirAdd (View view) {
        Intent intent = new Intent(this, Add.class);
        Bundle bundle = new Bundle();
        bundle.putInt("idUsuario", IDUSUARIO);
        bundle.putString("nombreUsuario", NOMBREUSUARIO);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void consultarRecuerdos() {
        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

        Recuerdos recuerdos = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_RECUERDOS + " WHERE " + Utilidades.ID_USUARIO_RECUERDO + " = " + IDUSUARIO, null);

        while (cursor.moveToNext()) {
            recuerdos = new Recuerdos();
            recuerdos.setId(cursor.getInt(0));
            recuerdos.setTitulo(cursor.getString(2));
            recuerdos.setNota(cursor.getString(3));
            recuerdos.setImagen(cursor.getString(4));
            recuerdos.setUbicacion(cursor.getString(5));
            listaRecuerdos.add(recuerdos);
        }
        Collections.reverse(listaRecuerdos);
        listViewRecuerdos.setAdapter(adaptador);
    }

    public void eliminarRecuerdo(int pos) {
        feed.addView(alertDialogInflate);
        botonAceptar = findViewById(R.id.botonAceptar);
        botonCancelar = findViewById(R.id.botonCancelar);
        textoAlertDialog = findViewById(R.id.textoAlertDialog);
        textoAlertDialog.setText("Esta seguro que desea eliminar este recuerdo?");
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarEliminarRecuerdo(pos);
                feed.removeView(alertDialogInflate);
            }
        });
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feed.removeView(alertDialogInflate);
            }
        });
    }

    public void confirmarEliminarRecuerdo(int pos){
        int idRecuerdo = listaRecuerdos.get(pos).getId();
        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();
        db.execSQL("DELETE FROM " + Utilidades.TABLA_RECUERDOS + " WHERE " + Utilidades.ID_RECUERDO + " = " +  idRecuerdo);
        cargarLista();
    }

    @Override
    public void onBackPressed() {
        if (!canExitApp) {
            canExitApp = true;
            Toast.makeText(this, getString(R.string.app_back_pressed_log_in), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    canExitApp = false;
                }
            }, 2000);
        } else {
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
            finish();
        }
    }
}






