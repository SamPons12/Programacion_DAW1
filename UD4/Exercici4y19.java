import java.util.Scanner;

public class Exercici4y19 {
        public static void main(String[] args) {
            Scanner lector = new Scanner(System.in);

            System.out.print("Introdueix un nombre: ");
            int numero = lector.nextInt();

            if (numero <= 1) {
                System.out.println(numero + " no és un nombre primer.");
            } else {
                boolean esPrimer = true;

                for (int i = 2; i < numero; i++) {
                    if (numero % i == 0) {
                        esPrimer = false;
                        break;
                    }
                }

                if (esPrimer) {
                    System.out.println(numero + " és un nombre primer.");
                } else {
                    System.out.println(numero + " no és un nombre primer.");
                }
            }
        }
    }
