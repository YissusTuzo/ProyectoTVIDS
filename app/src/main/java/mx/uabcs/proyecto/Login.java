package mx.uabcs.proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText mail;
    EditText password;
    Button login;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final userClass user1 = new userClass();
        user1.setUser("isidro_15");
        user1.setPassword("siiauabcs");
        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((user1.getUser()).equals(mail.getText().toString().trim()))&&((user1.getPassword()).equals(password.getText().toString().trim()))) {
                    Intent i = new Intent(Login.this,MiUbicacion.class);
                    String n  = password.getText().toString();
                    String e  = mail.getText().toString();

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Name, n);
                    editor.putString(Email, e);
                    editor.apply();
                    startActivity(i);
                }else
                    Toast.makeText(getApplicationContext(),"Uusario o contraseña incorrecto ",Toast.LENGTH_LONG).show();
            }
        });
    }
}
