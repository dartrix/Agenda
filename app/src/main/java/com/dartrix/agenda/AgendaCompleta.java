package com.dartrix.agenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AgendaCompleta extends Activity {
    LinearLayout ly;
    SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendacompleta_layout);
        recientes();
    }



    public void recientes(){
        ly = (LinearLayout)findViewById(R.id.linearlayout);
        DBAgenda con = new DBAgenda(this,"Agenda",null,1);

        ArrayList<Informacion> todaInformacion = con.traerTodoDatos();

        if (todaInformacion.size() <= 0){
            TextView nodatos = (TextView)findViewById(R.id.nodatos);
            nodatos.setText("No hay datos en la agenda.");
        }

        for(Informacion i : todaInformacion){
            agregarLista(i);
        }
    }

    private void agregarLista(final Informacion dato){
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.listview_layout, null);

        TextView ttl = (TextView)v.findViewById(R.id.titulo);
        ttl.setText(dato.getTitulo());

        TextView hr = (TextView)v.findViewById(R.id.hora);
        hr.setText(dato.getHora());

        TextView fch = (TextView)v.findViewById(R.id.fecha);
        fch.setText(dato.getFecha());
        ly.addView(v);
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {

                switch(e.getAction()) {
                    case MotionEvent.ACTION_UP:
                        Intent i = new Intent(v.getContext(), MostrarInfo.class);
                        i.putExtra("id",Integer.toString(dato.getId()));
                        startActivity(i);
                        break;
                }
                return true;
            }
        });
    }

    public void eliminarTodo(View v){
        final DBAgenda db = new DBAgenda(this,"Agenda",null,1);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Esta seguro de que quiere borrar todo?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.eliminarTodosDatos();
                        Intent i = new Intent(AgendaCompleta.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }




}
