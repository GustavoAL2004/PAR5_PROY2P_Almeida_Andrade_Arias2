package com.example.appcomunicadosespol;

/**
 * La clase Evento representa un evento y extiende de Comunicado.
 */

public class Evento extends Comunicado {
    private String lugar;
    private String fecha;

    /**
     * Constructor de evento.
     *
     * @param ID Identificador del comunicado.
     * @param tipo Tipo de comunicado.
     * @param area Área a la que pertenece.
     * @param titulo Título del evento.
     * @param audiencia Audiencia que va dirigido el evento.
     * @param descripcion Descripción del evento.
     * @param imagenURL Imagen del anuncio.
     * @param lugar Lugar donde se hará el evento.
     * @param fecha Fecha del evento.
     */

    public Evento(String ID, String tipo, String area, String titulo, String audiencia, String descripcion, String imagenURL, String lugar, String fecha) {
        super(ID, tipo, area, titulo, audiencia, descripcion, imagenURL);
        this.lugar = lugar;
        this.fecha = fecha;
    }

    /**
     * Devuelve el lugar del evento.
     *
     * @return String del lugar del evento.
     */

    public String getLugar() {
        return lugar;
    }

    /**
     * Cambia el lugar del evento.
     *
     * @param lugar Nuevo lugar del evento.
     */

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Devuelve la fecha del evento.
     *
     * @return String de la fecha del evento.
     */

    public String getFecha() {
        return fecha;
    }

    /**
     * Cambia la fecha del evento.
     *
     * @param fecha Nueva fecha del evento.
     */

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve una cadena de texto del evento.
     *
     * @return String con los datos del eventos.
     */

    public String toString() {
        return super.toString() + "," + lugar + "," + fecha;
    }
}
