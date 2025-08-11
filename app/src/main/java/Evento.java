public class Evento extends Comunicado{
    private String lugar;
    private String fecha;

    public Evento(String area, String audiencia, String titulo, String descripcion, String imagenURL, String lugar, String fecha) {
        super(area, audiencia, titulo, descripcion, imagenURL);
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

    @Override
    public String toString() {
        return
                "Evento{" +
                "lugar='" + lugar + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
