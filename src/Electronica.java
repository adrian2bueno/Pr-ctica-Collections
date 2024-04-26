public class Electronica extends Producte {
    private int diesGarantia;

    //
    public Electronica(String nom, float preu, int codibarres, int garantia) {
        super(nom, preu, codibarres);
        this.diesGarantia = garantia;
    }

    public int getDiesGarantia() {
        return diesGarantia;
    }

    public void setDiesGarantia(int diesGarantia) {
        this.diesGarantia = diesGarantia;
    }

    public float getPreu() {
        return (float) (super.getPreu() + super.getPreu() * (this.getDiesGarantia() / 365) * 0.1);
    }
}
