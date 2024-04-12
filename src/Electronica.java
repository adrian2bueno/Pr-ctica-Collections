public class Electronica extends Producte{
    int garantia;

    public Electronica(String nom, float preu, int codibarres, int garantia) {
        super(nom, preu, codibarres);
        this.garantia = garantia;
    }
}
