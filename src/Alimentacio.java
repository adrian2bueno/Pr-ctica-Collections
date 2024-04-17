import java.text.SimpleDateFormat;
import java.util.Date;

public class Alimentacio extends Producte{
    int dataCaducitat;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Alimentacio(String nom, float preu, int codibarres, int dataCaducitat) {
        super(nom, preu, codibarres);
        this.dataCaducitat = dataCaducitat;
    }

    public float pvp(){
      //  int dataActual = Integer.parseInt(dateFormat.format(new Date()));
        int dataActual = 20042024;
        return (float) (preu - preu*(1/(dataCaducitat-dataActual+1)) + (preu * 0.1));
    }

    public int getDataCaducitat() {
        return dataCaducitat;
    }

    public void setDataCaducitat(int dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }
    @Override
    public String toString() {
        return super.toString() +
                ",\nPVP= " + pvp();
    }
}
