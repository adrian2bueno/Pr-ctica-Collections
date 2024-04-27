import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

//
public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Producte> productes = new ArrayList<>(100);
    private static HashMap<String, Object> carret = new HashMap<>(100);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int opcio;

        //TODO: try catch con finally .close para los ficheros
        //TODO: quitar archivo dades

        do {
            System.out.println("BENVINGUT AL SAPAMERCAT");
            System.out.println("------------- \n--- INICI ---\n-------------");
            System.out.println("1) Introduir producte");
            System.out.println("2) Passar per caixa");
            System.out.println("3) Mostrar carret de compra");
            System.out.println("0) Acabar");
            opcio = scan.nextInt();
            //scan.nextLine();

            switch (opcio) {
                case 1:
                    try {
                        menuProductes();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    passarCaixa();
                    break;
                case 3:
                    mostrarCarret();
                    break;
                default:
                    System.out.println("Has de introduir un numero entre 0 i 3");
                    break;
            }
            // scan.nextLine();
        } while (opcio != 0);
    }


    public static void menuProductes() throws IOException {
        int opcioP;
        try {
            do {
                System.out.println("---------------- \n--- PRODUCTE ---\n----------------");
                System.out.println("1) Alimentació");
                System.out.println("2) Tèxtil");
                System.out.println("3) Electrònica");
                System.out.println("0) Tornar");
                opcioP = scan.nextInt();
                //scan.nextLine();
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
                    default:
                        System.out.println("Has de introduir un numero entre 0 i 3");
                        break;
                }

                //scan.nextLine();
            } while (opcioP != 0);
        } finally {
            //TODO: HACER QUE SE BORREN LAS OPCIONES?
        }
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

    private static void mostrarCarret() {
        System.out.println("Carret");
        carret.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });
    }

    private static void afegirAliments() throws IOException {
        try {
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
            String data = scan.next().trim();
            dataCaducitat = Integer.parseInt(data.replace("/", ""));

            nom = comprovarLlargadaNom(nom);

            new Alimentacio(nom, preu, codibarres, dataCaducitat);

            carret.put(nom, new Alimentacio(nom, preu, codibarres, dataCaducitat));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void afegirTextil() {
        try {
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

            nom = comprovarLlargadaNom(nom);

            new Textil(nom, preu, codibarres, composicio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void afegirElectronica() {
        try {
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
            nom = comprovarLlargadaNom(nom);

            new Electronica(nom, preu, codibarres, diesGarantia);
            // afegirProducte();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String comprovarLlargadaNom(String nom) throws Exception {
        if (nom.length() > 15) {
            throw new Exception("El nom torna a ser massa llarg.");
        }
        return nom;
    }


}