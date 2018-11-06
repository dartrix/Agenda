package com.dartrix.agenda;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBAgenda extends SQLiteOpenHelper {

    String query = "Create Table Datos(id integer primary key autoincrement, titulo text not null, hora text not null, fecha text not null, descripcion text not null);";

    public DBAgenda(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Datos");
    }


    //Create
    public void agregarDato(String titulo, String hora, String fecha, String desc){
        SQLiteDatabase db = getWritableDatabase();
        if (db !=null) {
            db.execSQL("insert into Datos (titulo, hora, fecha, descripcion) values ('"+titulo + "','" + hora + "','" + fecha + "','" + desc + "');");
            Log.d("E", "SE AGREGO UN DATO GGWP");
        }
        db.close();

    }

    //Read
    public ArrayList<Informacion> traerTodoDatos(){
        return traerTodoDatos("DESC");
    }

    public ArrayList<Informacion> traerTodoDatos(String orderby){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Datos ORDER BY id "+orderby,null);

        ArrayList<Informacion> datos = new ArrayList<>();

        while(c.moveToNext())   {
            Informacion d = new Informacion(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
            datos.add(d);
        }
        c.close();
        db.close();
        return datos;
    }

    //Read
    public Informacion traeDato(String id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("Select * from Datos where id = "+id+" Limit 1",null);

        Informacion dato = new Informacion();

        while(c.moveToNext())   {
            dato = new Informacion(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));

        }
        c.close();
        db.close();
        return dato;
    }

    //Update
    public void actualizarDato(String id, String titulo, String fecha, String hora, String descripcion){
        SQLiteDatabase db = getWritableDatabase();
        if (db !=null) {
            db.execSQL("Update Datos SET titulo = '"+titulo+"', fecha = '"+fecha+"', hora = '"+hora+"', descripcion = '"+descripcion+"' Where id = "+id);
            Log.d("E", "SE EDITO UN DATO GGWP");
        }
        db.close();
    }

    //Delete
    public void eliminarDato(String id){
        SQLiteDatabase db = getWritableDatabase();
        if (db !=null) {
            db.execSQL("DELETE FROM Datos WHERE id = "+id);
            Log.d("E", "SE ELIMINO UN DATO GGWP");
        }
        db.close();

    }

    //Delete (Truncate)
    public void eliminarTodosDatos(){
        SQLiteDatabase db = getWritableDatabase();
        if (db !=null) {
            db.execSQL("DELETE FROM Datos");
            Log.d("E", "SE BORRARON TODOS LOS DATOS GGWP");
        }
        db.close();
    }
}
