package mx.uabcs.proyecto;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.TextView;

public class VerNota2 extends AppCompatActivity {

    String title,content;
    TextView TITLE,CONTENT;

    private static final int EDITAR = Menu.FIRST;
    private static final int BORRAR = Menu.FIRST+1;
    private static final int SALIR = Menu.FIRST+2;

    AdapterBD1 DB2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_nota);

        Bundle bundle = this.getIntent().getExtras();
        title = bundle.getString("title");
        content = bundle.getString("content");

        TITLE = findViewById(R.id.textViewTitulo);
        CONTENT = findViewById(R.id.textViewContenido);

        TITLE.setText(title);
        CONTENT.setText(content);

    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.mi_menu,menu);

        menu.add(1,EDITAR,0,R.string.Menu_editar);
        menu.add(2,BORRAR,0,R.string.Menu_eliminar);
        menu.add(3,SALIR,0,R.string.Menu_salir);
        super.onCreateOptionsMenu(menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        switch (id){

            case EDITAR:
              actividad("edit");
             return true;

            case BORRAR:
                alert();
            return true;
            case SALIR:
                actividad("delete");
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    public void actividad(String f){

        if(f.equals("edit")){
            String type = "edit";
            Intent intent = new Intent(VerNota2.this,AgregarNota2.class);
            intent.putExtra("type",type);
            intent.putExtra("title",title);
            intent.putExtra("content",content);
            startActivity(intent);
        }else{
            if(f.equals("delete")){
                CookieSyncManager.createInstance(this);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();

                Intent intent = new Intent(VerNota2.this, NoteActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        }
    }

    private void alert(){
        AlertDialog alerta;

        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Mensaje De Confirmacion");
        alert.setMessage("¿Deseas Borrar La Nota?");
        alert.setButton(Dialog.BUTTON_POSITIVE,"Borrar Nota",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete();
            }
        });
        alert.setButton(Dialog.BUTTON_NEGATIVE,"Cancelar",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        alert.show();

    }

    private void delete(){

        DB2 = new AdapterBD1(this);
        DB2.deleteNote(title);
        actividad("delete");

    }
}
