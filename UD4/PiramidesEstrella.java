import java.util.Scanner;

public class PiramidesEstrella {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        System.out.print("Introdueix un valor per a l'altura de la piràmide: ");
        int altura = lector.nextInt();

        for (int i = 1; i <= altura; i++) {
            for (int j = 1; j <= altura - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
