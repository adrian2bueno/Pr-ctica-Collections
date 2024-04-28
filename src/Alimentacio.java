import java.text.SimpleDateFormat;
import java.util.Date;

public class Alimentacio extends Producte{
    private int dataCaducitat;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Alimentacio(String nom, float preu, String codibarres, int dataCaducitat) {
        super(nom, preu, codibarres);
        this.dataCaducitat = dataCaducitat;
    }

    public int getDataCaducitat() {
        return dataCaducitat;
    }

    public void setDataCaducitat(int dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    public float getPreu() {
        return (float) ((super.getPreu() - super.getPreu() * (1 / (this.dataCaducitat -
                (Integer.parseInt(dateFormat.format(new Date())))) + 1)) + (super.getPreu() * 0.1));
    }
}
