package mx.uabcs.proyecto;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.uabcs.proyecto.Interface.HorariosApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class horarios extends AppCompatActivity {

    TableLayout tableLayout;
    TextView relleno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        tableLayout = findViewById(R.id.idtable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Horario");
        crearColumnas();
        getHorarios();
        relleno=findViewById(R.id.datos);
    }

    public void crearColumnas(){
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // Id Column
        TextView textViewDia = new TextView(this);
        textViewDia.setText("Dia");
        textViewDia.setTextSize(16);
        textViewDia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewDia.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewDia.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewDia);

        // Name Column
        TextView textViewHora = new TextView(this);
        textViewHora.setText("Hora Entrada");
        textViewHora.setTextSize(16);
        textViewHora.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewHora.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewHora.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewHora);

        // Name Column
        TextView textViewHoraS = new TextView(this);
        textViewHoraS.setText("Hora Salida");
        textViewHoraS.setTextSize(16);
        textViewHoraS.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewHoraS.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewHoraS.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewHoraS);

        // Price Column
        TextView textViewMateria = new TextView(this);
        textViewMateria.setText("Materia");
        textViewMateria.setTextSize(16);
        textViewMateria.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewMateria.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewMateria.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewMateria);

        // Profesor Column
        TextView textViewTeacher = new TextView(this);
        textViewTeacher.setText("Profesor");
        textViewTeacher.setTextSize(16);
        textViewTeacher.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewTeacher.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewTeacher.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewTeacher);

        // Price Column
        TextView textViewSalon = new TextView(this);
        textViewSalon.setText("Salon");
        textViewSalon.setTextSize(16);
        textViewSalon.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewSalon.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewSalon.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewSalon);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


    }

    public void Datos(List<Horariosc> horariosSemestres){
        for(Horariosc horarios : horariosSemestres){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            // Id Column
            TextView textViewDia = new TextView(this);
            textViewDia.setText(horarios.getDay());
            textViewDia.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewDia.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewDia);

            // Name Column
            TextView textViewHora = new TextView(this);
            textViewHora.setText(horarios.getBegin_time());
            textViewHora.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewHora.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewHora);

            // Name Column
            TextView textViewHoras = new TextView(this);
            textViewHoras.setText(horarios.getEnd_time());
            textViewHoras.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewHoras.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewHoras);

            // Price Column
            TextView textViewMateria = new TextView(this);
            textViewMateria.setText(horarios.getSubject());
            textViewMateria.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewMateria.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewMateria);

            // Profesor Column
            TextView textViewTeacher = new TextView(this);
            textViewTeacher.setText(horarios.getTeacher());
            textViewTeacher.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewTeacher.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewTeacher);

            // Price Column
            TextView textViewSalon = new TextView(this);
            textViewSalon.setText(horarios.getInstallment());
            textViewSalon.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewSalon.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewSalon);

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    public void getHorarios(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sevuabcs.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HorariosApi horariosApi = retrofit.create(HorariosApi.class);

        Call<List<Horariosc>> call = horariosApi.getHorarios(1);
        call.enqueue(new Callback<List<Horariosc>>() {
            @Override
            public void onResponse(Call<List<Horariosc>> call, Response<List<Horariosc>> response) {
                    if(!response.isSuccessful()) {


                        }
                List<Horariosc> horarios = response.body();
                        for(Horariosc horariosc:horarios){
                            String content="";


                            content +="Dia:"+ horariosc.getDay()+"\n";
                            content +="Hora Entrada:"+ horariosc.getBegin_time()+"\n";
                            content +="Hora Salida:"+ horariosc.getEnd_time()+"\n";
                            content +="Materia:"+ horariosc.getSubject()+"\n";
                            content +="Maestro:"+ horariosc.getTeacher()+"\n";
                            content +="Edificio:"+ horariosc.getInstallment()+"\n\n";
                            Log.i("resultado:",horariosc.getDay());
                            relleno.append(content);

                        }


            }

            @Override
            public void onFailure(Call<List<Horariosc>> call, Throwable t) {

                Log.i("resultado","getDay" );
            }
        });
    }
}
