package com.example.partyfavors2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SingIn extends AppCompatActivity {

    EditText RegUsuario, RegNombre, RegApellido,RegPassword;
    Button btnRegRegistrar,btnCancela;
    String USUARIO, NOMBRE, APELLIDO, CONTRASEÑA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        RegUsuario = findViewById(R.id.RegUsuario);
        RegNombre = findViewById(R.id.RegNombre);
        RegApellido = findViewById(R.id.RegApellido);
        RegPassword = findViewById(R.id.RegPass);
        btnRegRegistrar = findViewById(R.id.btnRegRegistrar);
        btnCancela = findViewById(R.id.btnCancela);

        btnRegRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USUARIO = RegUsuario.getText().toString();
                NOMBRE = RegNombre.getText().toString();
                APELLIDO = RegApellido.getText().toString();
                CONTRASEÑA = RegPassword.getText().toString();
                if (USUARIO.equals("") && NOMBRE.equals("") && APELLIDO.equals("") && CONTRASEÑA.equals("")) {
                    Toast.makeText(SingIn.this, "Todos los campos deben estar completos.", Toast.LENGTH_SHORT).show();
                } else {
                    GuardarUsuario();
                }
            }
        });
    }
        public void GuardarUsuario () {
            DataBase conexionSQLiteHelper = new DataBase(this, "db_partyfavors", null, 1);
            SQLiteDatabase db = conexionSQLiteHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_USUARIO, USUARIO);
            values.put(Utilidades.CAMPO_NOMBRE, NOMBRE);
            values.put(Utilidades.CAMPO_APELLIDO, APELLIDO);
            values.put(Utilidades.CAMPO_CONTRASEÑA, CONTRASEÑA);

            Long idResultante = db.insert(Utilidades.TABLA_USUARIOS, Utilidades.CAMPO_NOMBRE, values);
            Toast.makeText(getApplicationContext(), "Usuario Nro° " + idResultante + " fue registrado exitosamente.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
            finish();
        }
}