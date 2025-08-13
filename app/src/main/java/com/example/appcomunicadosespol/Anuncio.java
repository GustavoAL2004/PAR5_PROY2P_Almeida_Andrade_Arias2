package com.example.appcomunicadosespol;

public class Anuncio extends Comunicado {
    private String nivelUrgencia;

    public Anuncio(String ID, String tipo, String area, String titulo, String audiencia, String descripcion, String imagenURL, String nivelUrgencia) {
        super(ID, tipo, area, titulo, audiencia, descripcion, imagenURL);
        this.nivelUrgencia = nivelUrgencia;
    }

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    @Override
    public String toString() {
        return super.toString() + "," + nivelUrgencia;
    }
}
