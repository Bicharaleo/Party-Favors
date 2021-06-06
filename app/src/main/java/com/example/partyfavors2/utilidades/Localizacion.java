package com.example.partyfavors2.utilidades;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.partyfavors2.activitys.Add;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class Localizacion implements LocationListener {
    Add add;
    public void setAdd(Add add) {
        this.add = add;
    }
    @Override
    public void onLocationChanged(Location loc) {
        // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la deteccion de un cambio de ubicacion
       add.setLocation(loc);
    }
    @Override
    public void onProviderDisabled(String provider) {
        // Este metodo se ejecuta cuando el GPS es desactivado
        add.textView1.setText("GPS Desactivado");
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Este metodo se ejecuta cuando el GPS es activado
        add.textView1.setText("GPS Activado");
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                Log.d("debug", "LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }
}

