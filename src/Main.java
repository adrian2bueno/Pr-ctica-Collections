import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int opcio,opcioP;

        Scanner scan = new Scanner(System.in);
        Supermercat supermercat = new Supermercat();

        try {
            System.out.println("\nBENVINGUT AL SAPAMERCAT");
            do {

                System.out.println("------------- \n--- INICI ---\n-------------");
                System.out.println("1. Introduir producte");
                System.out.println("2. Passar per caixa");
                System.out.println("3. Mostrar carret de compra");

                System.out.println("0. Sortir");
                opcio = scan.nextInt();
                switch (opcio) {
                    case 1:
                        do {
                            System.out.println("---------------- \n--- PRODUCTE ---\n----------------");
                            System.out.println("1) Alimentació");
                            System.out.println("2) Tèxtil");
                            System.out.println("3) Electrònica");
                            System.out.println("0) Sortir");
                            opcioP = scan.nextInt();
                            switch (opcioP) {
                                case 1:
                                    supermercat.afegirProducte("Alimentacio");
                                    break;
                                case 2:
                                    supermercat.afegirProducte("Textil");
                                    break;
                                case 3:
                                    supermercat.afegirProducte("Electronica");
                                    break;
                                default: break;
                            }
                        } while (opcioP != 0);
                        break;
                    case 2:
                        supermercat.passarCaixa();
                        System.out.println("Programa finalitzat");
                        System.exit(0);
                        break;
                    case 3:
                        supermercat.mostrarCarret();
                        break;
                    case 0:
                        System.out.println("Programa finalitzat");
                    default: break;
                }
            } while (opcio != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}