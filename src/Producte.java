public abstract class Producte {
    String nom;
    float preu;
    int codibarres;

    //
    public Producte(String nom, float preu, int codibarres) {
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

    public int getCodibarres() {
        return codibarres;
    }

    public void setCodibarres(int codibarres) {
        this.codibarres = codibarres;
    }

    public int numProductes() {
        //Todo: calcular numero de productes per cada nom
        return 1;
    }

    public float pvp() {
        return preu + (preu * (21 / 100));
    }
    public  String getInfo() {
        return nom + " -> " + numProductes() + "    " + pvp();

    }
}
