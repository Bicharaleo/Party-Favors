package com.example.partyfavors2.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.partyfavors2.utilidades.OrientacionImagen;
import com.example.partyfavors2.R;
import com.example.partyfavors2.entidades.Recuerdos;

import java.io.File;
import java.util.List;

public class ListAdap extends ArrayAdapter {
    private List<Recuerdos> list;
    private Context context;
    private int resourceLayout;
    OrientacionImagen orientacionImagen = new OrientacionImagen();

    public ListAdap(@NonNull Context context, int resource, List<Recuerdos> objects) {
        super(context, resource, objects);
        this.list = objects;
        this.context = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
           @SuppressLint("ViewHolder") View view =  LayoutInflater.from(context).inflate(resourceLayout, null);

            Recuerdos recuerdos = list.get(position);
            TextView titulo = view.findViewById(R.id.tituloItem);
            titulo.setText(recuerdos.getTitulo());
            TextView nota = view.findViewById(R.id.notaItem);
            nota.setText(recuerdos.getNota());
            TextView ubicacion = view.findViewById(R.id.ubicacionItem);
            ubicacion.setText(recuerdos.getUbicacion());
            ImageView imagen = view.findViewById(R.id.previewImagenItem);
            String rutaImagen = recuerdos.getImagen();
            File imagenFile = new File(rutaImagen);
            if (!rutaImagen.equals("") && imagenFile.exists()) {
                Bitmap bitmap = orientacionImagen.orientarImagen(rutaImagen);
                //Bitmap bitmap = BitmapFactory.decodeFile(rutaImagen);
                imagen.setImageBitmap(bitmap);
            }
        return view;
    }
}
