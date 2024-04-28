import java.util.Comparator;
import java.util.Objects;
public abstract class Producte {
    //TODO: implements Comparable
    private String nom, codibarres;
    private float preu;

    //
    public Producte(String nom, float preu, String codibarres) {
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

    public String getCodibarres() {
        return codibarres;
    }

    public void setCodibarres(String codibarres) {
        this.codibarres = codibarres;
    }

    public int numProductes() {
        //Todo: calcular numero de productes per cada nom
        return 1;
    }

    public float pvp() {
        return preu + (preu * (21 / 100));
    }

    public String toString() {
        return nom + " -> " + numProductes() + "    " + pvp();

    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producte producte)) return false;

        return Objects.equals(getCodibarres(), producte.getCodibarres()) && Objects.equals(getPreu(), producte.getPreu());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCodibarres());
    } public static class ComparadorNom implements Comparator<Producte> {
        @Override
        public int compare(Producte p1, Producte p2) {
            return p1.getNom().compareTo(p2.getNom());
        }
}



}
