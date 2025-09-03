package com.example.appcomunicadosespol;

/**
 * La clase Anuncio representa un anuncio  y extiende de Comunicado.
 */

public class Anuncio extends Comunicado {
    private String nivelUrgencia;

    /**
     * Constructor de Anuncio.
     *
     * @param ID Identificador del comunicado.
     * @param tipo Tipo de comunicado.
     * @param area Área a la que pertenece.
     * @param titulo Título del anuncio.
     * @param audiencia Audiencia que va dirigido el anuncio.
     * @param descripcion Descripción del anuncio.
     * @param imagenURL Imagen del anuncio.
     * @param nivelUrgencia Nivel de urgencia del anuncio.
     */

    public Anuncio(String ID, String tipo, String area, String titulo, String audiencia, String descripcion, String imagenURL, String nivelUrgencia) {
        super(ID, tipo, area, titulo, audiencia, descripcion, imagenURL);
        this.nivelUrgencia = nivelUrgencia;
    }

    /**
     * Devuelve el nivel de urgencia.
     *
     * @return String del nivel de urgencia del anuncio.
     */

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    /**
     * Cambia el nivel de urgencia del anuncio.
     *
     * @param nivelUrgencia Nuevo nivel de urgencia.
     */

    public void setNivelUrgencia(String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    /**
     * Devuelve una cadena de texto del anuncio.
     *
     * @return String con los datos del anuncio.
     */

    @Override
    public String toString() {
        return super.toString() + "," + nivelUrgencia;
    }
}
