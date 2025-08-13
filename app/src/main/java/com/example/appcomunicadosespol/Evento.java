package com.example.appcomunicadosespol;

public class Evento extends Comunicado {
    private String lugar;
    private String fecha;

    public Evento(String ID, String tipo, String area, String titulo, String audiencia, String descripcion, String imagenURL, String lugar, String fecha) {
        super(ID, tipo, area, titulo, audiencia, descripcion, imagenURL);
        this.lugar = lugar;
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return super.toString() + "," + lugar + "," + fecha;
    }
}
