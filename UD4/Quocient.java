import java.util.Scanner;
public class Quocient {
    public static void main (String[] args) {
        Scanner lector = new Scanner(System.in);

        System.out.print("Quin és el dividend? ");

        int dividend = lector.nextInt();
        lector.nextLine();

        System.out.print("Quin és el divisor? ");

        int divisor = lector.nextInt();
        lector.nextLine();

        while (dividend >= divisor) {
            dividend = dividend - divisor;
            System.out.println("Bucle: per ara el dividend val " +  dividend + ".");
        }
        System.out.println("El resultat final és " + dividend + ".");
        System.out.println("El quocient es " + (dividend/divisor));
    }
}
