package com.example.appcomunicadosespol;

public abstract class Comunicado {
    private  String area;
    private String audiencia;
    private String titulo;
    private String descripcion;
    private String imagenURL;
    private String tipo;

    private String ID;

    public Comunicado(String ID,String tipo,String area, String titulo, String audiencia, String descripcion, String imagenURL) {
        this.tipo=tipo;
        this.ID=ID;
        this.area = area;
        this.audiencia = audiencia;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenURL = imagenURL;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(String audiencia) {
        this.audiencia = audiencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                getID(),
                getTipo(),
                getArea(),
                getTitulo(),
                getAudiencia(),
                getDescripcion(),
                getImagenURL());
    }
}
