import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Textil extends Producte implements Comparable<Textil>{
    private String composicio;

    public Textil(String nom, float preu, String codibarres, String composicio) {
        super(nom, preu, codibarres);
        this.composicio = composicio;
    }

    public String getComposicio() {
        return composicio;
    }

    public void setComposicio(String composicio) {
        this.composicio = composicio;
    }

    public int compareTo(Textil t){

        return this.getComposicio().compareTo(t.getComposicio());
    }
    //TODO
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Textil textil)) return false;
        if (!super.equals(o)) return false;

        return Objects.equals(getCodiBarres(), textil.getCodiBarres());
    }
}
