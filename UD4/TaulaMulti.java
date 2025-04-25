import java.util.Scanner;

public class TaulaMulti {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int limit;
        int resultat = 0;

        System.out.print("Fins a quin valor vols sumar múltiples de 3?");
        limit = sc.nextInt();

        for(int i = 0; i <= limit; i += 3) {
            System.out.println("Afegim " + i + "...");
            resultat = resultat + i;
        }
        System.out.println("El resultat final és " + resultat + ".");

    }
}
