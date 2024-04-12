public class Textil extends Producte{
    String composicio;

    //
    public Textil(String nom, float preu, int codibarres, String composicio) {
        super(nom, preu, codibarres);
        this.composicio = composicio;
    }
}
