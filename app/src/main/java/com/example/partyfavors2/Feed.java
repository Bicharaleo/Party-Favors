package com.example.partyfavors2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Feed extends AppCompatActivity {

    TextView usuarioView;
    String NOMBREUSUARIO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        usuarioView = findViewById(R.id.usuarioView);
        NOMBREUSUARIO = getIntent().getExtras().getString("nombreUsuario");
        usuarioView.setText(NOMBREUSUARIO);
    }
}
