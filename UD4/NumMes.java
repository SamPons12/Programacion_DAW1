import java.util.Scanner;
public class NumMes {
    public static void main (String[] args) {
        Scanner lector = new Scanner(System.in);
        int valorUsuari = 0;

        do {
            System.out.print("Introdueix un valor enter entre 1 i 12:");
            valorUsuari = lector.nextInt();
            lector.nextLine();

        } while ((valorUsuari < 1)||(valorUsuari > 12));
        System.out.println("Dada correcta. Has escrit " + valorUsuari);
    }
}
