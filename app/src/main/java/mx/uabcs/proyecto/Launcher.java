package mx.uabcs.proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Launcher extends AppCompatActivity {

    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usuario = sharedPreferences.getString("nameKey","");

        if(!usuario.equals(""))
            startActivity(new Intent(Launcher.this, MiUbicacion.class));
        else
            startActivity(new Intent(Launcher.this, Login.class));
    }
}
