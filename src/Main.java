import java.text.SimpleDateFormat;
import java.util.*;

//
public class Main {
    private static ArrayList<Producte> productes = new ArrayList<>(100);
    private static HashMap<String, Object> carret = new HashMap<>(100);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();

    }

    private static void menuPrincipal() {
        int opcio;

        //TODO: try catch con finally .close para los ficheros

        do {
            System.out.println("BENVINGUT AL SAPAMERCAT");
            System.out.println("------------- \n--- INICI ---\n-------------");
            System.out.println("1) Introduir producte");
            System.out.println("2) Passar per caixa");
            System.out.println("3) Mostrar carret de compra");
            System.out.println("0) Acabar");
            opcio = scan.nextInt();

            switch (opcio) {
                case 1:
                    menuProductes();
                    break;

                case 2:
                    passarCaixa();
                    break;

                case 3:
                    System.out.println("Carret");
                    carret.forEach((key, value) -> {
                        System.out.println(key + " -> " + value);
                    });
                    break;

                //mostrarLlista(filtrarProductes("Alimentacio"));
                //mapaprova.containsKey() mapaprova.containsValue()
                   /* Per a poder-lo integrar amb la impressió del carret de la compra
                d’altres aplicacions ja desplegades, ens demanen treballar amb la
                    Collection Map, i ens diuen que serà necessari treballar amb
                    mètodes propis com ara containsKey o containsValue
                        (valoreu quin dels dos casos us serà necessari).
                        El recorregut de les dadesProva s’haurà de fer amb lambda expressions.*/
               /* default:
                    System.out.println("Entre 0 i 3");

                    break;*/
            }
            //scan.nextLine();
        } while (opcio != 0);
    }

    private static void passarCaixa() {
        try {
            if (carret.isEmpty()) {
                System.out.println("El carret esta buit");
            } else {
                System.out.println("---------------------");
                System.out.println("SAPAMERCAT");
                System.out.println("---------------------");
                System.out.println("Data: " + dateFormat.format(new Date()));
                System.out.println("---------------------");
                System.out.println("productos..... ");
                System.out.println("Total: ");


            }

        } catch (Exception e) {
            //TODO
        } finally {
            carret.clear();
            productes.clear();
            menuPrincipal();
        }


    }

    public static void menuProductes() {
        int opcioP;
        try {
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
                        afegirAliments();
                        break;

                    case 2:
                        afegirTextil();
                        break;

                    case 3:
                        afegirElectronica();
                        break;

                }

                scan.nextLine();
            } while (opcioP != 0);
        } finally {
            //TODO: HACER QUE SE BORREN LAS OPCIONES?
        }
    }

    private static void afegirAliments() {
        int preu, codibarres, dataCaducitat;
        String nom;
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
        dataCaducitat = Integer.parseInt(data.replace("/", ""));
        try {
            new Alimentacio(nom, preu, codibarres, dataCaducitat);
            //  afegirProducte();
            carret.put(nom, new Alimentacio(nom, preu, codibarres, dataCaducitat));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void afegirTextil() {
        int preu, codibarres;
        String composicio, nom;
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
            new Textil(nom, preu, codibarres, composicio);
            // afegirProducte();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void afegirElectronica() {
        String nom;
        int preu, diesGarantia, codibarres;
        System.out.println("Afegir electrònica");
        System.out.print("Nom producte: ");
        nom = scan.nextLine();
        System.out.print("Preu: ");
        preu = scan.nextInt();
        System.out.print("Codi de barres: ");
        codibarres = scan.nextInt();
        System.out.print("Garantia (dies): ");
        diesGarantia = scan.nextInt();
        try {
            new Electronica(nom, preu, codibarres, diesGarantia);
            // afegirProducte();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void afegirProducte(Producte p) {
        productes.add(p);
    }

    //Informació
    /*public static void eliminarProducte(String n) {
        productes.removeIf(p -> p.nom.equals(n));
    }*/


}