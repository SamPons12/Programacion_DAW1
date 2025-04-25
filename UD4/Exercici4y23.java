import java.util.Scanner;

public class Exercici4y23 {
    static int N = 0;
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        System.out.print("Quants nombres primers vols? ");
        N = lector.nextInt();
        CalculPrimo();
    }

    public static void CalculPrimo(){
        int count = 0;
        int num = 2;

        while (count < N) {
            boolean esPrimer = true;

            if (num <= 1) {
                esPrimer = false;
            } else {
                for (int i = 2; i < num; i++) {
                    if (num % i == 0) {
                        esPrimer = false;
                        break;
                    }
                }
            }

            if (esPrimer) {
                System.out.print(num + " ");
                count++;
            }
            num++;
        }
    }
}
