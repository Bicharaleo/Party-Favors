package com.example.partyfavors2.utilidades;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class Camera extends AppCompatActivity {
    int TOMAR_FOTO = 100;
    String RUTA_IMAGEN, NOMBREIMAGEN;
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void AbrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;
        try {
            imagenArchivo = CrearImagen();
        } catch (IOException ex) {
            Log.e("Error", ex.toString());
        }
        if (imagenArchivo != null) {
            Uri imagenUri = FileProvider.getUriForFile(this, "com.example.partyfavors2.fileprovider", imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
            activity.startActivityForResult(intent, TOMAR_FOTO);
        }
    }

    private File CrearImagen() throws IOException {
        String nombreImagen = "Foto_";
        File directorio = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);
        RUTA_IMAGEN = imagen.getAbsolutePath();
        NOMBREIMAGEN = imagen.getName();
        return imagen;
    }

    public String getRutaImagen(){
        return RUTA_IMAGEN;
    }
    public String getNombreImagen(){
        return NOMBREIMAGEN;
    }
}
