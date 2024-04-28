import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

//
public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Producte> productes = new ArrayList<>(100);
    static ArrayList<String> barresProductes = new ArrayList<>(100);
    private static HashMap<String, Object> carret = new HashMap<>(100);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static LinkedHashMap<String, Integer> codis = new LinkedHashMap<>(); //Guarda el nom dels productes i la seva quantitat

    public static void main(String[] args) throws IOException {
        crearFitxers();
        menuPrincipal();
    }

    private static void menuPrincipal() throws IOException {

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
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Has de introduir un numero entre 0 i 3");
                    break;
            }
            // scan.nextLine();
        } while (opcio != 0);
    }


    public static void menuProductes() throws IOException {
        try {
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
                        afegirAliments();
                        break;
                    case 2:
                        afegirTextil();
                        break;
                    case 3:
                        afegirElectronica();
                        break;
                    case 0:
                        menuPrincipal();
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

    private static void afegirAliments() throws IOException {
        try {
            float preu;
            int dataCaducitat;
            String nom, codibarres;
            System.out.println("Afegir aliment");
            System.out.print("Nom producte: ");
            nom = scan.nextLine().trim();
            if (nom.length() > 15) {
                throw new Exception("Nom massa llarg.");
            }
            nom = comprovarLlargadaNom(nom);
            scan.nextLine();

            System.out.print("Preu: ");
            preu = scan.nextFloat();
            System.out.print("Codi de barres: ");
            codibarres = scan.nextLine();
            System.out.print("Data de caducitat (dd/mm/aaaa): ");
            String data = scan.next().trim();
            dataCaducitat = Integer.parseInt(data.replace("/", ""));


            productes.add(new Alimentacio(nom, preu, codibarres, dataCaducitat));
            barresProductes.add(codibarres);
            carret.put(codibarres, nom);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Error: Entrada inválida. Ingresa un número válido para el precio, código de barras y los días de garantía.");
            recollirExcepcions();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            recollirExcepcions();
        }
    }


    private static void afegirTextil() throws IOException {
        try {
            float preu;
            String composicio, nom, codibarres;
            System.out.println("Afegir tèxtil");
            System.out.print("Nom producte: ");
            nom = scan.nextLine().trim();
            scan.nextLine();
            System.out.print("Preu: ");
            preu = scan.nextFloat();
            System.out.print("Codi de barres: ");
            codibarres = scan.nextLine();
            System.out.print("Composició: ");
            composicio = scan.nextLine();

            nom = comprovarLlargadaNom(nom);
            productes.add(new Textil(nom, preu, codibarres, composicio));
            barresProductes.add(codibarres);
            carret.put(codibarres, nom);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Error: Entrada inválida. Ingresa un número válido para el precio, código de barras y los días de garantía.");
            recollirExcepcions();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            recollirExcepcions();
        }

    }

    private static void afegirElectronica() throws IOException {
        try {
            String nom, codibarres;
            float preu;
            int diesGarantia;
            System.out.println("Afegir electrònica");
            System.out.print("Nom producte: ");
            nom = scan.nextLine().trim();
            scan.nextLine();
            System.out.print("Preu: ");
            preu = scan.nextFloat();
            System.out.print("Codi de barres: ");
            codibarres = scan.nextLine();
            System.out.print("Garantia (dies): ");
            diesGarantia = scan.nextInt();
            nom = comprovarLlargadaNom(nom);

            productes.add(new Electronica(nom, preu, codibarres, diesGarantia));
            barresProductes.add(codibarres);
            carret.put(codibarres, nom);
            // afegirProducte();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Error: Entrada inválida. Ingresa un número válido para el precio, código de barras y los días de garantía.");
            recollirExcepcions();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            recollirExcepcions();
        }
    }

    private static void passarCaixa() throws IOException {
        try {
            if (carret.isEmpty()) {
                System.out.println("El carret esta buit");
            } else {
                float[] preuTotal = {0};
                System.out.println("---------------------");
                System.out.println("SAPAMERCAT");
                System.out.println("---------------------");
                System.out.println("Data: " + dateFormat.format(new Date()));
                System.out.println("---------------------");

                float total = calcularTotal(productes);
                productes.forEach(producte -> {
                    String nom = producte.getNom();
                    float preu = producte.getPreu();
                    System.out.printf("%-" + 15 + "s%" + 10 + "s\n", nom, preu);
                    preuTotal[0] += preu;
                });


                System.out.println("Total: " + preuTotal[0] + total);


            }

        } catch (Exception e) {
            //TODO
        } finally {
            //carret.clear();
            //productes.clear();
            menuPrincipal();
        }
    }

    private static void mostrarCarret() throws IOException {
        if (carret.isEmpty()) {
            System.out.println("No hi ha cap article al carro.");
        } else {

            try {

                for (String codi : barresProductes) {
                    codis.put(codi, codis.getOrDefault(codi, 0) + 1);
                }

                codis.forEach((codi, quantitat) -> {
                    String nom = (String) carret.get(codi);
                    System.out.println(nom + " --> " + quantitat);
                });
                menuPrincipal();

            } catch (Exception e) {
                System.out.println("Hi ha hagut un error al mostrar el carro");
                recollirExcepcions();
            }
        }
    }

    public static void recollirExcepcions() throws FileNotFoundException {
        File Exceptions = new File("./logs/Exceptions.dat");
        FileOutputStream errors = new FileOutputStream(Exceptions, true);
        PrintStream output = new PrintStream(errors);
        try {
            output.println("S'ha produit un error al executar el programa");
        } catch (Exception e) {
            System.out.println("S'ha produit un error recollint excepcions");
            recollirExcepcions();
        } finally {
            output.close();
        }
    }

    public static void crearFitxers() throws IOException {
        try {
            File logs = new File("./logs/Exceptions.dat");
            File updates = new File("./updates/UpdateTextilPrices.dat");

            //TODO
            logs.createNewFile();
            updates.createNewFile();
        } catch (Exception e) {
            recollirExcepcions();
        }
    }

    public static float calcularTotal(ArrayList<Producte> listaProductos) {
        float total = 0;
        for (Producte p : listaProductos) {
            total += p.getPreu();
        }
        return total;
    }

    private static String comprovarLlargadaNom(String nom) throws Exception {
        if (nom.length() > 15) {
            throw new Exception("El nom torna a ser massa llarg.");
        }
        return nom;
    }


}