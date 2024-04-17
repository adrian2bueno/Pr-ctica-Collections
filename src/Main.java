import java.text.SimpleDateFormat;
import java.util.*;

//
public class Main {
    private static ArrayList<Producte> productes = new ArrayList<>();
    private static HashMap <String, Object> mapaprova = new HashMap<String, Object>();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int opcio;

        do {
            System.out.println("BENVINGUT AL SAPAMERCAT");
            System.out.println("------------- \n--- INICI ---\n-------------");
            System.out.println("1) Introduir producte");
            System.out.println("2) Passar per caixa");
            System.out.println("3) Mostrar carret de compra");
            System.out.println("0) Acabar");
            opcio = scan.nextInt();
            scan.nextLine();
            switch (opcio) {
                case 1:
                    menuProductes();

                case 2:
                    System.out.println("---------------------");
                    System.out.println("SAPAMERCAT");
                    System.out.println("---------------------");
                    System.out.println("Data: " + dateFormat.format(new Date()));
                    System.out.println("---------------------");
                    System.out.println("productos..... ");
                    System.out.println("Total: ");

                case 3:
                    System.out.println("Carret");

                    mostrarLlista(filtrarProductes("Alimentacio"));
                    //mapaprova.containsKey() mapaprova.containsValue()
                   /* Per a poder-lo integrar amb la impressió del carret de la compra
                d’altres aplicacions ja desplegades, ens demanen treballar amb la
                    Collection Map, i ens diuen que serà necessari treballar amb
                    mètodes propis com ara containsKey o containsValue
                        (valoreu quin dels dos casos us serà necessari).
                        El recorregut de les dadesProva s’haurà de fer amb lambda expressions.*/
               // default:
               //     System.out.println("Entre 0 i 3");
            }
            //scan.nextLine();
        } while (opcio != 0);
    }
    public static void menuProductes() {
        String nom= "", composicio = "";
        int preu = 0, codibarres = 0,garantia = 0,dataCaducitat;
        int opcioP;
        do {
            System.out.println("---------------- \n--- PRODUCTE ---\n----------------");
            System.out.println("1) Alimentació");
            System.out.println("2) Tèxtil");
            System.out.println("3) Electrònica");
            System.out.println("0) Tornar");
            opcioP = scan.nextInt();
            scan.nextLine();
        switch (opcioP) {
            case 1:
                System.out.println("Afegir aliment");
                System.out.print("Nom producte: ");
                nom = scan.nextLine().trim();
                System.out.print("Preu: ");
                preu = scan.nextInt();
                System.out.print("Codi de barres: ");
                codibarres = scan.nextInt();
                System.out.print("Data de caducitat (dd/mm/aaaa): ");
                // ToDO: Transforma la data a un número eliminant els "/"
                String data = scan.next().trim();
                dataCaducitat = Integer.parseInt(data.replace("/",""));
                try {
                    afegirProducte(new Alimentacio(nom, preu, codibarres,dataCaducitat));
                    mapaprova.put("a",new Alimentacio(nom, preu, codibarres,dataCaducitat));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

                break;
            case 2:
                System.out.println("Afegir tèxtil");
                System.out.print("Nom producte: ");
                nom = scan.nextLine().trim();
                System.out.print("Preu: ");
                preu = scan.nextInt();
                System.out.print("Codi de barres: ");
                codibarres = scan.nextInt();
                System.out.print("Composició: ");
                composicio = scan.nextLine();

                try {
                    afegirProducte(new Textil(nom, preu, codibarres,composicio));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

                break;
            case 3:
                System.out.println("Afegir tèxtil");
                System.out.print("Nom producte: ");
                nom = scan.nextLine();
                System.out.print("Preu: ");
                preu = scan.nextInt();
                System.out.print("Codi de barres: ");
                codibarres = scan.nextInt();
                System.out.print("Garantia (dies): ");
                garantia = scan.nextInt();
                try {
                    afegirProducte(new Electronica(nom, preu, codibarres,garantia));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

                break;
        }
        } while (opcioP != 0);
    }
    public static void afegirProducte(Producte p) {
        productes.add(p);
    }

    //Informació
    /*public static void eliminarProducte(String n) {
        productes.removeIf(p -> p.nom.equals(n));
    }*/

    //Informació
    public static ArrayList<Producte> filtrarProductes(String tipus) {
        ArrayList<Producte> resultat = new ArrayList<>();
        for (Producte p : productes) {
            if (p.getClass().getSimpleName().equals(tipus)) {
                resultat.add(p);
            }
        }
        return resultat;
    }
    public static void mostrarLlista(ArrayList<Producte> llista) {
        for (Producte p : llista) {
            System.out.println(p.getInfo());
        }
    }
}