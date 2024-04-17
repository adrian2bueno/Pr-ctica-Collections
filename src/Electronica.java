public class Electronica extends Producte{
    int garantia;

    //
    public Electronica(String nom, float preu, int codibarres, int garantia) {
        super(nom, preu, codibarres);
        this.garantia = garantia;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }
    public String toString() {
        return super.toString() +
                ",\nPVP= " + pvp();
    }
}
