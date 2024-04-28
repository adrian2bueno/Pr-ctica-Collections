import java.util.ArrayList;
import java.util.List;

public class Electronica extends Producte {
    private int diesGarantia;

    public Electronica(String nom, float preu, String codibarres, int diesGarantia) {
        super(nom, preu, codibarres);
        this.diesGarantia = diesGarantia;
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
