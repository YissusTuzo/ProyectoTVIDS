package mx.uabcs.proyecto;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity2 extends AppCompatActivity {
    private static final int ADD = Menu.FIRST;
    private static final int DELETE = Menu.FIRST + 1;
    private static final int EXIT = Menu.FIRST + 2;

    ListView lista;
    TextView textoLista;
    AdapterBD2 DB1;
    List<String> item = null;
    String getTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        textoLista = findViewById(R.id.lista);
        lista = findViewById(R.id.listanotas);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getTitle=(String) lista.getItemAtPosition(i);
                alert("list");
            }
        });




        showNotes();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mi_menu, menu);

        menu.add(1, ADD, 0, R.string.Menu_crear);
        menu.add(2, DELETE, 0, R.string.Menu_eliminar_todas);
        menu.add(3, EXIT, 0, R.string.Menu_salir);
        super.onCreateOptionsMenu(menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case ADD:
                actividad("add");
                return true;

            case DELETE:
                alert("deletes");
                return true;

            case EXIT:
                finish();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    private void showNotes() {
        DB1 = new AdapterBD2(this);
        Cursor c = DB1.getNotes();
        item = new ArrayList<>();
        String title = "";

        if (c.moveToFirst() == false) {
            textoLista.setText("No Hay Notas");
        } else {

            do {

                title = c.getString(1);
                item.add(title);

            } while (c.moveToNext());
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, item);
        lista.setAdapter(adaptador);

    }

    public String getNote() {
        String type = "", content = "";

        DB1 = new AdapterBD2(this);
        Cursor c = DB1.getNote(getTitle);

        if (c.moveToFirst()) {

            do {

                content = c.getString(2);

            } while (c.moveToNext());
        }

        return content;
    }

    public void actividad(String act) {

        String type = "", content = "";

        if (act.equals("add")) {

            type = "add";
            Intent intent = new Intent(NoteActivity2.this, AgregarNota2.class);
            intent.putExtra("type", type);
            startActivity(intent);
        } else {

            if (act.equals("edit")) {
                type = "edit";
                content = getNote();
                Intent intent = new Intent(NoteActivity2.this, AgregarNota2.class);
                intent.putExtra("type", type);
                intent.putExtra("title",getTitle);
                intent.putExtra("content",content);
                startActivity(intent);
            }else{
                if(act.equals("see")){
                    content = getNote();
                    Intent intent = new Intent(NoteActivity2.this, VerNota2.class);
                    intent.putExtra("title",getTitle);
                    intent.putExtra("content",content);
                    startActivity(intent);

                }
            }
        }
    }

    public void alert(String f){

        AlertDialog alerta;

        AlertDialog alert = new AlertDialog.Builder(this).create();
        if(f.equals("list")){
            alert.setTitle("Titulo de la nota: "+ getTitle);
            alert.setMessage("¿Que Accion Deseas Realizar?");
            alert.setButton(Dialog.BUTTON_POSITIVE,"Ver Nota",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    actividad("see");
                }
            });
            alert.setButton(Dialog.BUTTON_NEGATIVE,"Borrar Nota",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    delete("delete");
                    Intent intent= getIntent();
                    startActivity(intent);

                }
            });
            alert.setButton(Dialog.BUTTON_NEUTRAL,"Editar Nota",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    actividad("edit");
                }
            });
        }else{
            if(f.equals("deletes")){
                alert.setTitle("Confirmacion De Mensaje");
                alert.setMessage("¿Que Accion Deseas Realizar?");
                alert.setButton(Dialog.BUTTON_POSITIVE,"Cancelar",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alert.setButton(Dialog.BUTTON_NEGATIVE,"Borrar Notas",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete("deletes");
                        Intent intent= getIntent();
                        startActivity(intent);

                    }
                });
            }
        }
        alert.show();
    }
    private void delete(String f){
        DB1 = new AdapterBD2(this);
        if(f.equals("delete")){
            DB1.deleteNote(getTitle);

        }else{
            if(f.equals("deletes")){
                DB1.deleteNotes();
            }
        }

    }
}
