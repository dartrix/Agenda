package com.dartrix.agenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;



public class MostrarInfo extends Activity {

    TextView titulo,hora,fecha,desc;
    String idinfo;
    DBAgenda con;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarinfo_layout);

        titulo = (TextView)findViewById(R.id.ttl);
        hora = (TextView)findViewById(R.id.hora);
        fecha = (TextView)findViewById(R.id.fecha);
        desc = (TextView)findViewById(R.id.descripcion);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        con = new DBAgenda(this,"Agenda",null,1);

        if(b!=null)
        {
            idinfo =(String) b.get("id");

            Informacion info = con.traeDato(idinfo);

            llenarDatos(info);

        }
    }

    private void llenarDatos(Informacion info){
        titulo.setText(info.getTitulo());
        hora.setText(info.getHora());
        fecha.setText(info.getFecha());
        desc.setText(info.getDescripcion());

    }
    public void eliminarDato(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Esta seguro de que desea borrar esta nota?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        con.eliminarDato(idinfo);
                        Intent i = new Intent(MostrarInfo.this, MainActivity.class);
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

    public void editarDato(View v){
        Intent i = new Intent(MostrarInfo.this, EditarInfo.class);
        i.putExtra("id",idinfo);
        startActivity(i);
    }
}
