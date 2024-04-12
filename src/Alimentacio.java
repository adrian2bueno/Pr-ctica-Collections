import java.util.Date;

public class Alimentacio extends Producte{
    int dataCaducitat;

    public Alimentacio(String nom, float preu, int codibarres, String dtCaducitat) {
        super(nom, preu, codibarres);
        this.dataCaducitat = dataCaducitat;
    }

    public float pvp(){
        int dataActual = 2000;
        return (float) (preu - preu*(1/(dataCaducitat-dataActual+1)) + (preu * 0.1));
    }

    @Override
    public String toString() {
        return "\n\nNom: " + nom +
                "\nPreu= " + preu +
                ",\nPVP= " + pvp();
    }
}
