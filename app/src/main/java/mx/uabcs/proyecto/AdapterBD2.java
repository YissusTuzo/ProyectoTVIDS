package mx.uabcs.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdapterBD2 extends SQLiteOpenHelper {

    public static final String TABLE_ID="idNote2";
    public static final String TITLE="title2";
    public static final String CONTENT="content2";

    public static final String DATABASE="idNote2";
    public static final String TABLE="idNote2";


    public AdapterBD2(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = " CREATE TABLE " + TABLE + "(" +
                TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE + " TEXT, " +
                CONTENT + " TEXT)";

        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS "+TABLE;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }

    public void addNote(String title, String content){
        ContentValues valores = new ContentValues();
        valores.put(TITLE,title);
        valores.put(CONTENT,content);
        this.getWritableDatabase().insert(TABLE,null,valores);
    }

    public Cursor getNote(String condition){

        String columnas[] = {TABLE_ID,TITLE,CONTENT};
        String[] args = new String[] {condition};

        Cursor c= this.getReadableDatabase().query(TABLE,columnas,TITLE+"=?",args,null,null,null);
        return c;
    }

    public void deleteNote(String condition){
        String args[] = {condition};
        this.getWritableDatabase().delete(TABLE,TITLE + "=?",args);
    }

    public void updateNote(String title,String content,String condition){
        String args[] = {condition};
        ContentValues valores = new ContentValues();
        valores.put(TITLE,title);
        valores.put(CONTENT,content);
        this.getWritableDatabase().update(TABLE,valores,TITLE + "=?",args);
    }

    public Cursor getNotes(){
        String columnas[] = {TABLE_ID,TITLE,CONTENT};
        Cursor c= this.getReadableDatabase().query(TABLE,columnas,null,null,null,null,null);
        return c;
    }

    public void deleteNotes(){
        this.getWritableDatabase().delete(TABLE,null,null);
    }


}
