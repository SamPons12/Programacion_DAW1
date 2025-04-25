import java.util.Scanner;

public class DosNombresSecret {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int NOMBRE_SECRET1 = 30;
        final int NOMBRE_SECRET2 = 40;
        int nombreUsuari;

        System.out.print("Introdueix el nombre que trobes que és: ");
        nombreUsuari = sc.nextInt();

        if (nombreUsuari == NOMBRE_SECRET1) {
            System.out.println("Has endevinat el primer nombre secret");

        }else if (nombreUsuari == NOMBRE_SECRET2) {
            System.out.println("No Has endevinat el segon nombre secret");
        }else {
            System.out.println("No Has endevinat el nombre secret");
        }

        System.out.println("Fin del programa");
    }
}

