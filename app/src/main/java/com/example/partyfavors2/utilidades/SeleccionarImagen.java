package com.example.partyfavors2.utilidades;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.partyfavors2.utilidades.CopiarArchivo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SeleccionarImagen extends AppCompatActivity {
    int SELEC_IMAGEN = 200;
    String RUTA_IMAGEN, NOMBREIMAGEN;
    Activity activity;
    CopiarArchivo copiarArchivo = new CopiarArchivo();

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void SeleccionarImagen() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        activity.startActivityForResult(galeria, SELEC_IMAGEN);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void SeleccionarImagenResult(Intent data){
        Uri uri = data.getData();
        File file = new File(uri.getPath());
        NOMBREIMAGEN = file.getName();
        try {
            File imagenArchivo = null;
            try {
                imagenArchivo = CrearImagenSeleccionada(NOMBREIMAGEN);
            } catch (IOException ex) {
            }
            InputStream inputStream = activity.getApplicationContext().getContentResolver().openInputStream(data.getData());
            FileOutputStream fileOutputStream = new FileOutputStream(imagenArchivo);
            copiarArchivo.copyStream(inputStream, fileOutputStream);
            fileOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public File CrearImagenSeleccionada(String nombreImagen) throws IOException {
        File directorio = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = new File(directorio, nombreImagen);
        RUTA_IMAGEN = imagen.getAbsolutePath();
        return imagen;
    }

    public String getRutaImagen(){
        return RUTA_IMAGEN;
    }
    public String getNombreImagen(){
        return NOMBREIMAGEN;
    }
}
