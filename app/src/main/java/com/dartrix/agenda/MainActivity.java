package com.dartrix.agenda;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout ly;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recientes();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        recientes();


    }

    public void recientes(){
        ly = (LinearLayout)findViewById(R.id.lnrlyt);
        DBAgenda con = new DBAgenda(this,"Agenda",null,1);

        ArrayList<Informacion> todaInformacion = con.traerTodoDatos();

        int e=0;
        for(Informacion i : todaInformacion){
            e++;
            agregarLista(i);
            if (e >= 6){
                break;
            }
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

    public void agregar(View v){
        Intent a = new Intent(v.getContext(), AgregarInformacion.class);
        startActivity(a);
    }
    public void verAgenda(View v){
        Intent a = new Intent(v.getContext(), AgendaCompleta.class);
        startActivity(a);
    }
    public void verInfo(View v){
        Intent a = new Intent(v.getContext(), AboutClass.class);
        startActivity(a);
    }



}
