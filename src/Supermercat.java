import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Supermercat {
    private List<Producte> llistaProductes = new ArrayList<>(100);
    private List<Alimentacio> llistaAliments = new ArrayList<>();
    private List<Textil> llistaTextils = new ArrayList<>();
    private List<Electronica> llistaElectronics = new ArrayList<>();

    public void afegirProducte(String tipusProducte) throws Exception {
        if (llistaProductes.size() == 100) {
            recollirExcepcions("Has arribat al maxim de 100 productes");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("\nAfegir " + tipusProducte);

        System.out.print("Nom producte (15 caracters max.): ");
        String nomProducte = scan.nextLine();

        if (nomProducte.length() > 15) recollirExcepcions("El nom no pot superar els 15 caracters");

        if (!(nomProducte.matches("[a-zA-Z]+"))) recollirExcepcions("El nom te que estar compost per lletres: ");


        System.out.print("Preu: ");
        String preu = scan.next();

        float preuFinal = 0;
        try {
            preuFinal = Float.parseFloat(preu);
        } catch (NumberFormatException e) {
            recollirExcepcions("Preu incorrecte: ");
        }

        System.out.print("Codi de barres (6 caracters): ");
        String codiBarres = scan.next().toUpperCase();

        if (!(codiBarres.matches("[A-Z0-9]{6}"))) recollirExcepcions("Codi de barres incorrecte (6 caracters): ");

        Producte producte = null;
        switch (tipusProducte) {
            case "Alimentacio":
                System.out.print("Data caducitat (dd/mm/aaaa): ");
                String data = scan.next();

                int dataCaducitat = 0;
                try {
                    dataCaducitat = Integer.parseInt(data.replace("/", ""));

                } catch (DateTimeParseException e) {
                    recollirExcepcions("Data introduida incorrecte: ");
                }

                producte = new Alimentacio(nomProducte, preuFinal, codiBarres, dataCaducitat);
                llistaAliments.add((Alimentacio) producte);
                break;
            case "Textil":
                System.out.print("Composició: ");
                String composicio = scan.next();

                if (!composicio.matches("[A-Z][a-z]+")) recollirExcepcions("Composicio incorrecte: ");

                producte = new Textil(nomProducte, preuFinal, codiBarres, composicio);
                llistaTextils.add((Textil) producte);
                break;
            case "Electronica":
                System.out.print("Dies garantia: ");
                String diesString = scan.next();

                int diesGarantia = 0;
                try {
                    diesGarantia = Integer.parseInt(diesString);
                    if (diesGarantia < 0) recollirExcepcions("Els dies de garantia no poden ser inferiors a 0");
                } catch (NumberFormatException e) {
                    recollirExcepcions("Dies de garantia incorrecte: " + diesString);
                }

                producte = new Electronica(nomProducte, preuFinal, codiBarres, diesGarantia);
                llistaElectronics.add((Electronica) producte);
                break;

        }
        llistaProductes.add(producte);
    }

    public void passarCaixa() throws Exception {
        List<Producte> llistaProductes2 = new ArrayList<>();

        Set<Alimentacio> aliments = new HashSet<>(llistaAliments);
        llistaProductes2.addAll(aliments);

        Set<Textil> textils = new HashSet<>(llistaTextils);
        llistaProductes2.addAll(textils);

        Set<Electronica> electronics = new HashSet<>(llistaElectronics);
        llistaProductes2.addAll(electronics);

        if (!llistaProductes2.isEmpty()) {
            String dataAvui = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.println("---------------------");
            System.out.println("SAPAMERCAT");
            System.out.println("---------------------");
            System.out.println("Data: " + dataAvui);
            System.out.println("---------------------");

            float total = 0;
            for (Producte producte : llistaProductes2) {
                int unitats = 0;
                float subTotal;

                if (producte instanceof Alimentacio) {
                    unitats = Collections.frequency(llistaAliments, producte);
                } else if (producte instanceof Textil) {
                    comprovarPreuTextil(producte);
                    unitats = Collections.frequency(llistaTextils, producte);
                } else if (producte instanceof Electronica) {
                    unitats = Collections.frequency(llistaElectronics, producte);
                }

                if (unitats > 1) {
                    subTotal = unitats * producte.getPreu();
                    System.out.println(producte.getNom() + "   " + unitats + "   " + producte.getPreu() + "   " + subTotal);
                } else {
                    subTotal = producte.getPreu();
                    System.out.println(producte.getNom() + "   " + unitats + "   " + subTotal);
                }
                total += subTotal;

            }
            System.out.println("---------------------");
            System.out.println("Total: " + total);

            llistaAliments.clear();
            llistaTextils.clear();
            llistaElectronics.clear();

        } else recollirExcepcions("No hi han productes al carro");
    }

    public void mostrarCarret() throws Exception {
        Map<String, Integer> carret = new HashMap<>();

        if (!llistaProductes.isEmpty()) {
            System.out.println("Carret");

            for (Producte producte : llistaProductes) {
                if (carret.containsKey(producte.getCodiBarres())) {
                    carret.put(producte.getCodiBarres(), carret.get(producte.getCodiBarres()) + 1);
                } else {
                    carret.put(producte.getCodiBarres(), 1);
                }
            }

            carret.forEach((codi, quantitat) -> {
                System.out.println(buscarProductes(codi) + " --> " + quantitat);
            });
            carret.clear();

        } else recollirExcepcions("El carret no pot estar buit");
    }

    private String buscarProductes(String codiBarres) {
        Optional<Producte> producteTrobat = llistaProductes.stream()
                .filter(producte -> producte.getCodiBarres().equals(codiBarres))
                .findFirst();

        if (producteTrobat.isPresent()) {
            return producteTrobat.get().getNom();
        } else {
            return "No s'ha trobat el producte";
        }
    }

    private void comprovarPreuTextil(Producte producte) throws Exception {
        try {
            Scanner preusTextils = new Scanner(new File("./updates/UpdateTextilPrices.dat"));

            while (preusTextils.hasNextLine()) {
                String linia = preusTextils.nextLine();
                String codiBarresCorrecte = linia.split(":")[0];
                String preuCorrecte = linia.split(":")[1];

                if (codiBarresCorrecte.equals(producte.getCodiBarres())) {
                    producte.setPreu(Float.parseFloat(preuCorrecte));
                    preusTextils.close();
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            recollirExcepcions("L'arxiu de preus de textil no s'ha trobat");
        }
    }

    private void recollirExcepcions(String missatge) throws Exception {
        File carpetaLogs = new File("./logs");
        File arxiuLogs = new File("./logs/Exceptions.log");

        do {
            carpetaLogs.mkdirs();
        } while (!carpetaLogs.exists());

        do {
            try {
                arxiuLogs.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!arxiuLogs.exists());

        try {
            FileWriter escritor = new FileWriter(arxiuLogs, true);
            escritor.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " - " + missatge + '\n');
            escritor.close();
            throw new Exception("S'ha produit un error. Revisa el fitxer de logs");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}




