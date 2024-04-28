import java.util.Comparator;
import java.util.Objects;
public abstract class Producte {
    private String nom, codibarres;
    private float preu;


    public Producte(String nom, float preu, String codibarres) throws IllegalArgumentException{
        this.nom = nom;
        this.preu = preu;
        this.codibarres = codibarres;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getCodiBarres() {
        return codibarres;
    }

    public void setCodiBarres(String codibarres) {
        this.codibarres = codibarres;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producte producte)) return false;

        return Objects.equals(getCodiBarres(), producte.getCodiBarres()) && Objects.equals(getPreu(), producte.getPreu());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCodiBarres());
    }


}
