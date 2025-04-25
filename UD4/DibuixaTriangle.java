import java.util.Scanner;

public class DibuixaTriangle {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        System.out.print("Introdueix un valor enter per a l'altura del triangle: ");
        int altura = lector.nextInt();

        for (int i = 1; i <= altura; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("a");
            }
            System.out.println();
        }
    }
}
