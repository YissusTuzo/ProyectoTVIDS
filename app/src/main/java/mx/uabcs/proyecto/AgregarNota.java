package mx.uabcs.proyecto;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarNota extends AppCompatActivity {
    Button Add;
    EditText TITLE,CONTENT;
    private static final int SALIR = Menu.FIRST;
    String type, getTitle;
    AdapterBD DB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_notas);
        TITLE = findViewById(R.id.edittitulo);
        CONTENT = findViewById(R.id.edditcontenido);
        Add = findViewById(R.id.button_agregar);


        Bundle bundle = this.getIntent().getExtras();
        String content;
        getTitle= bundle.getString("title");
        content= bundle.getString("content");

        type = bundle.getString("type");

        if(type.equals("add")){

            Add.setText("agregar nota");
        }else{
            if(type.equals("edit")){
                TITLE.setText(getTitle);
                CONTENT.setText(content);
                Add.setText("Actualizar Notas");
            }
        }

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUpdateNote();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.mi_menu,menu);

        menu.add(1,SALIR,0,R.string.Menu_salir);

        super.onCreateOptionsMenu(menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        switch (id){

            case SALIR:
                CookieSyncManager.createInstance(this);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();

                Intent intent = new Intent(AgregarNota.this, NoteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    public void addUpdateNote(){
        DB = new AdapterBD(this);
        String title,content,msj;
        title = TITLE.getText().toString();
        content= CONTENT.getText().toString();

        if(type.equals("add")){
            if (title.equals("")){
                msj="Ingrese Un Titulo";
                TITLE.requestFocus();
                Mensaje(msj);
            }
            else{
                if (content.equals("")){
                    msj="Ingrese La Nota";
                    CONTENT.requestFocus();
                    Mensaje(msj);
                }else{
                    Cursor c= DB.getNote(title);
                    String gettitle="";

                    if(c.moveToFirst()){

                        do{
                            gettitle = c.getString(1);
                        }while (c.moveToNext());
                    }
                    if(gettitle.equals(title)){
                        TITLE.requestFocus();
                        msj="El Titulo De La Nota Ya Existe";
                        Mensaje(msj);
                    }else{
                        DB.addNote(title,content);
                        actividad(title,content);
                    }
                }
            }
        }
        else{
            if(type.equals("edit")){
                Add.setText("Actualizar Nota");
                if(title.equals("")){
                    msj="Ingrese Un Titulo";
                    TITLE.requestFocus();
                    Mensaje(msj);

                }
                else{
                    if (content.equals("")){
                        msj="Ingrese La Nota";
                        CONTENT.requestFocus();
                        Mensaje(msj);
                    }else{
                        DB.updateNote(title,content,getTitle);
                        actividad(title,content);
                    }
                }
            }
        }
    }

    public void Mensaje(String msj){

        Toast toast = Toast.makeText(this,msj, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

    public void actividad(String title, String content){

        Intent intent= new Intent(AgregarNota.this,VerNota.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        startActivity(intent);
    }
}
