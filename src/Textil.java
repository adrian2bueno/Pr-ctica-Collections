public class Textil extends Producte{
    String composicio;

    //
    public Textil(String nom, float preu, int codibarres, String composicio) {
        super(nom, preu, codibarres);
        this.composicio = composicio;
    }

    public String getComposicio() {
        return composicio;
    }

    public void setComposicio(String composicio) {
        this.composicio = composicio;
    }
    public String toString() {
        return super.toString() +
                ",\nPVP= " + pvp();
    }
}
