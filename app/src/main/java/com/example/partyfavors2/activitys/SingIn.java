package com.example.partyfavors2.activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partyfavors2.database.DataBase;
import com.example.partyfavors2.R;
import com.example.partyfavors2.utilidades.Utilidades;

import java.util.regex.Pattern;

public class SingIn extends AppCompatActivity {

    EditText RegUsuario, RegNombre, RegApellido, RegPassword;
    String USUARIO, NOMBRE, APELLIDO, CONTRASEÑA;
    DataBase conexionSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        RegUsuario = findViewById(R.id.RegUsuario);
        RegNombre = findViewById(R.id.RegNombre);
        RegApellido = findViewById(R.id.RegApellido);
        RegPassword = findViewById(R.id.RegPass);
        conexionSQLiteHelper = new DataBase(this, "db_partyfavors", null, 1);
    }

    public void validarCampos(View view) {
        USUARIO = RegUsuario.getText().toString();
        NOMBRE = RegNombre.getText().toString();
        APELLIDO = RegApellido.getText().toString();
        CONTRASEÑA = RegPassword.getText().toString();
        if (!USUARIO.equals("") && !NOMBRE.equals("") && !APELLIDO.equals("") && !CONTRASEÑA.equals("")) {
            if (validarContraseña(CONTRASEÑA)) {
                usuarioDisponible(USUARIO);
            } else {
                Toast.makeText(this, "La contraseña no es valida:\nDebe contener al menos 8 caracteres\nDebe contener al menos 1 minuscula\nDebe contener al menos 1 mayuscula\nDebe contener al menos 1 numero", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(SingIn.this, "Todos los campos deben estar completos.", Toast.LENGTH_SHORT).show();
        }
    }

    public void usuarioDisponible(String usuario) {
        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIOS + " WHERE " + Utilidades.CAMPO_USUARIO + " = '" + usuario + "'", null);
        if (cursor.getCount() <= 0) {
            GuardarUsuario();
        }
        else{
            Toast.makeText(SingIn.this, "El usuario ya esta en uso.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarContraseña(String contraseña) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\S{8,}$");
        return pattern.matcher(contraseña).matches();
    }


    public void GuardarUsuario() {
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

    public void salirSingIn(View view) {
        onBackPressed();
        finish();
    }
}