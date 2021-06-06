package com.example.partyfavors2.activitys;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.partyfavors2.utilidades.Camera;
import com.example.partyfavors2.database.DataBase;
import com.example.partyfavors2.utilidades.Localizacion;
import com.example.partyfavors2.utilidades.OrientacionImagen;
import com.example.partyfavors2.R;
import com.example.partyfavors2.utilidades.SeleccionarImagen;
import com.example.partyfavors2.utilidades.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Add extends AppCompatActivity {

    boolean menuDesplegado = false;
    public TextView textmenu1, textmenu2, textmenu3, textView1, textView2;
    EditText editTitulo, editNota;
    Button botonIr;
    ImageView previewImagen;
    String NOMBREUSUARIO, TITULO = "", NOTA = "", IMAGEN = "", UBICACION = "", direccion = "";
    double latitud;
    double longitud;
    int IDUSUARIO;
    ConstraintLayout layoutadd;
    LayoutInflater inflaterMenuFlotante;
    View menuFlotanteInflate;
    Camera camara = new Camera();
    SeleccionarImagen seleccionarImagen = new SeleccionarImagen();
    OrientacionImagen orientacionImagen = new OrientacionImagen();


    FloatingActionButton botonadd,botonsalir, boton1, boton2, boton3;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        layoutadd = findViewById(R.id.layoutadd);
        botonadd = findViewById(R.id.botonadd);
        botonsalir=findViewById(R.id.botonsalir);
        textView1 = findViewById(R.id.textView11);
        textView2 = findViewById(R.id.textView22);
        editNota = findViewById(R.id.editNota);
        editTitulo = findViewById(R.id.editTitulo);
        botonIr = findViewById(R.id.botonIr);
        previewImagen = findViewById(R.id.previewImagenItem);
        IDUSUARIO = getIntent().getExtras().getInt("idUsuario");
        NOMBREUSUARIO = getIntent().getExtras().getString("nombreUsuario");

        inflaterMenuFlotante = getLayoutInflater();
        menuFlotanteInflate = inflaterMenuFlotante.inflate(R.layout.menu_flotante, layoutadd, false);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
    }
    public void desplegarMenu(View view) {
        desplegarMenuFlotante();
    }

    public void desplegarMenuFlotante(){

        if(!menuDesplegado) {
            menuDesplegado = true;
            layoutadd.addView(menuFlotanteInflate);
            boton1 = findViewById(R.id.boton1);
            boton2 = findViewById(R.id.boton2);
            boton3 = findViewById(R.id.boton3);
            textmenu1 = findViewById(R.id.textmenu1);
            textmenu2 = findViewById(R.id.textmenu2);
            textmenu3 = findViewById(R.id.textmenu3);

            RotateAnimation rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(200);
            botonadd.startAnimation(rotate);
            botonadd.setImageResource(R.drawable.ic_baseline_close_24);

            TranslateAnimation an1 = new TranslateAnimation(0.0f, 0.0f, 400.0f, 0.0f);
            an1.setDuration(500);
            boton1.startAnimation(an1);

            TranslateAnimation an2 = new TranslateAnimation(0.0f, 0.0f, 300.0f, 0.0f);
            an2.setDuration(400);
            boton2.startAnimation(an2);

            TranslateAnimation an3 = new TranslateAnimation(0.0f, 0.0f, 200.0f, 0.0f);
            an3.setDuration(300);
            boton3.startAnimation(an3);

            TranslateAnimation antxt1 = new TranslateAnimation(200.0f, 0.0f, 0.0f, 0.0f);
            antxt1.setDuration(700);
            textmenu1.startAnimation(antxt1);
            textmenu2.startAnimation(antxt1);
            textmenu3.startAnimation(antxt1);
        }
        else{
            RotateAnimation rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(200);
            botonadd.startAnimation(rotate);
            botonadd.setImageResource(R.drawable.ic_baseline_add_24);

            TranslateAnimation an1 = new TranslateAnimation(0.0f, 0.0f, 0.0f, 400.0f);
            an1.setDuration(300);
            boton1.startAnimation(an1);

            TranslateAnimation an2 = new TranslateAnimation(0.0f, 0.0f, 0.0f, 300.0f);
            an2.setDuration(250);
            boton2.startAnimation(an2);

            TranslateAnimation an3 = new TranslateAnimation(0.0f, 0.0f, 0.0f, 200.0f);
            an3.setDuration(200);
            boton3.startAnimation(an3);

            TranslateAnimation antxt1 = new TranslateAnimation(0.0f, 200.0f, 0.0f, 0.0f);
            antxt1.setDuration(250);
            textmenu1.startAnimation(antxt1);
            textmenu2.startAnimation(antxt1);
            textmenu3.startAnimation(antxt1);
            esperar(250);
        }
    }



    public void esperar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                layoutadd.removeView(menuFlotanteInflate);
                menuDesplegado = false;
            }
        }, milisegundos);
    }
    public void salirAdd (View view){
        onBackPressed();
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setAdd(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
        textView1.setText("Localización agregada");
        textView2.setText("Geolocalizando..");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                latitud = loc.getLatitude();
                longitud = loc.getLongitude();

                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    textView2.setText(DirCalle.getAddressLine(0));
                    direccion = DirCalle.getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void obtenerUbicacion(View view){
        desplegarMenuFlotante();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else{
                locationStart();
                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                botonIr.setVisibility(View.VISIBLE);
        }
    }
    public void abrirMaps(View view){
        //String geo = "http://maps.google.co.in/maps?q=" + direccion;
        String geo = "http://maps.google.com/maps?q=loc:" + latitud + "," + longitud + "(Buenos Aires)";
        Uri intentUri = Uri.parse(geo);
        Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
        startActivity(intent);
    }

    public void abrirCamara(View view) {
        desplegarMenuFlotante();
        camara.setActivity(this);
        camara.abrirCamara();
    }
    public void seleccionarImagen(View vista) {
        desplegarMenuFlotante();
        seleccionarImagen.setActivity(this);
        seleccionarImagen.seleccionarImagen();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            IMAGEN = camara.getRutaImagen();
            Bitmap bitmap = orientacionImagen.orientarImagen(IMAGEN);
            previewImagen.setImageBitmap(bitmap);
        }
        if (resultCode == RESULT_OK && requestCode == 200 && data != null) {
            seleccionarImagen.seleccionarImagenResult(data);
            IMAGEN = seleccionarImagen.getRutaImagen();
            Bitmap bitmap = orientacionImagen.orientarImagen(IMAGEN);
            previewImagen.setImageBitmap(bitmap);
        }
    }

    public void guardarRecuerdo(View view) {
        TITULO = editTitulo.getText().toString();
        NOTA = editNota.getText().toString();
        UBICACION = direccion;
        if(!TITULO.equals("")) {
            DataBase conexionSQLiteHelper = new DataBase(this, "db_partyfavors", null, 1);
            SQLiteDatabase db = conexionSQLiteHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.ID_USUARIO_RECUERDO, IDUSUARIO);
            values.put(Utilidades.CAMPO_TITULO, TITULO);
            values.put(Utilidades.CAMPO_NOTA, NOTA);
            values.put(Utilidades.CAMPO_IMAGEN, IMAGEN);
            values.put(Utilidades.CAMPO_UBICACION, UBICACION);
            db.insert(Utilidades.TABLA_RECUERDOS, Utilidades.CAMPO_TITULO, values);
            Toast.makeText(getApplicationContext(), "El recuerdo fue guardado exitosamente.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Feed.class);
            Bundle bundle = new Bundle();
            bundle.putInt("idUsuario", IDUSUARIO);
            bundle.putString("nombreUsuario", NOMBREUSUARIO);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "El recuerdo debe tener titulo.", Toast.LENGTH_SHORT).show();
        }
    }
}