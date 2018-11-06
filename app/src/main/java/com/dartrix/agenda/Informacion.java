package com.dartrix.agenda;

public class Informacion {

    private int id;
    private String titulo,hora,fecha,descripcion;

    public Informacion(int id, String titulo, String hora, String fecha, String descripcion){
        this.id = id;
        this.titulo=titulo;
        this.hora=hora;
        this.fecha=fecha;
        this.descripcion=descripcion;
    }
    public Informacion(){
        this.id = -1;
        this.titulo="";
        this.hora="";
        this.fecha="";
        this.descripcion="";
    }

    public int getId(){
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getHora() {
        return hora;
    }
    public String getFecha(){return fecha;}
    public String getTitulo(){
        return titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha){this.fecha=fecha;}

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
