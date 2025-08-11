public class Anuncio extends Comunicado{
    private String nivelUrgencia;

    public Anuncio(String area, String audiencia, String titulo, String descripcion, String imagenURL, String nivelUrgencia) {
        super(area, audiencia, titulo, descripcion, imagenURL);
        this.nivelUrgencia = nivelUrgencia;
    }

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }
}
