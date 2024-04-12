import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
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
                    System.out.println("---------------- \n--- PRODUCTE ---\n----------------");
                    System.out.println("1) Alimentació");
                    System.out.println("2) Tèxtil");
                    System.out.println("3) Electrònica");
                    System.out.println("0) Tornar");



                case 2:
                    System.out.println("---------------------");
                    System.out.println("SAPAMERCAT");
                    System.out.println("---------------------");
                    System.out.println("Data: ");
                    System.out.println("---------------------");
                    System.out.println("productos..... ");
                    System.out.println("Total: ");


                case 3:
                    System.out.println("Carret");

                default:
                    System.out.println("Entre 0 i 3");
            }
            scan.nextLine();
        } while (opcio != 0);
    }
}