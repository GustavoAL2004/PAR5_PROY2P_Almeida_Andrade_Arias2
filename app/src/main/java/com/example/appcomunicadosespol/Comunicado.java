package com.example.appcomunicadosespol;

/**
 * La clase Comunicado es una clase abstrata que representa un comunicado,
 * contiene los datos comunes para anuncio y evento.
 */

public abstract class Comunicado {
    private  String area;
    private String audiencia;
    private String titulo;
    private String descripcion;
    private String imagenURL;
    private String tipo;

    private String ID;

    /**
     * Constructor de Comunicado
     *
     * @param ID Identificador único del comunicado.
     * @param tipo Tipo de comunicado.
     * @param area Área a la que pertenece.
     * @param titulo Título del comunicado.
     * @param audiencia Audiencia que va dirigido el comunicado.
     * @param descripcion Descripción del comunicado.
     * @param imagenURL Imagen del comunicado.
     */

    public Comunicado(String ID,String tipo,String area, String titulo, String audiencia, String descripcion, String imagenURL) {
        this.tipo=tipo;
        this.ID=ID;
        this.area = area;
        this.audiencia = audiencia;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenURL = imagenURL;
    }

    /**
     * Devuelve el tipo de comunicado.
     *
     * @return String del tipo del comunicado.
     */

    public String getTipo() {
        return tipo;
    }

    /**
     * Cambia el tipo de comunicado.
     *
     * @param tipo Nuevo tipo de comunicado.
     */

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve el identificador del comunicado.
     *
     * @return String del identificador del comunicado.
     */

    public String getID() {
        return ID;
    }

    /**
     * Cambia el identificador del comunicado.
     *
     * @param ID Nuevo identificador del comunicado.
     */

    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Devuelve el área del comunicado.
     *
     * @return String del área del comunicado.
     */

    public String getArea() {
        return area;
    }

    /**
     * Cambia el área del comunicado.
     *
     * @param area Nueva área del comunicado.
     */

    public void setArea(String area) {
        this.area = area;
    }

    /**
     * Devuelve la audiencia del comunicado.
     *
     * @return String de la audiencia del comunicado.
     */

    public String getAudiencia() {
        return audiencia;
    }

    /**
     * Cambia la audiencia del comunicado.
     *
     * @param audiencia Nueva audiencia del comunicado.
     */

    public void setAudiencia(String audiencia) {
        this.audiencia = audiencia;
    }

    /**
     * Devuelve el título del comunicado.
     *
     * @return String del título del comunicado.
     */

    public String getTitulo() {
        return titulo;
    }

    /**
     * Cambia el título del comunicado.
     *
     * @param titulo Nuevo título del comunicado.
     */

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve la descripción del comunicado.
     *
     * @return String de la descripción del comunicado.
     */

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Cambia la descripción del comunicado.
     *
     * @param descripcion Nueva descripción del comunicado.
     */

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el nombre de la imagen del comunicado.
     *
     * @return String del nombre de la imagen del comunicado.
     */

    public String getImagenURL() {
        return imagenURL;
    }

    /**
     * Cambia el nombre de la imagen del comunicado.
     *
     * @param imagenURL Nuevo nombre de la imagen del comunicado.
     */

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    /**
     * Devuelve una cadena de texto del comunicado.
     *
     * @return String con los datos del comunicado.
     */

    @Override
    public String toString() {
        return getID() + "," + getTipo() + "," + getArea() + "," + getTitulo() + "," +
                getAudiencia() + "," + getDescripcion() + "," + getImagenURL();
    }
}
