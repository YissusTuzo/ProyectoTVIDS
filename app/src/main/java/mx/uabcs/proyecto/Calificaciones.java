package mx.uabcs.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Calificaciones extends AppCompatActivity {
    TextView mat1,mat2,mat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificaciones);

        mat1 = findViewById(R.id.materia1);
        mat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calificaciones.this,NoteActivity3.class));
            }
        });
        mat2 = findViewById(R.id.materia2);
        mat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calificaciones.this,NoteActivity2.class));
            }
        });
        mat3 = findViewById(R.id.materia3);
        mat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calificaciones.this,NoteActivity1.class));
            }
        });
    }
}
