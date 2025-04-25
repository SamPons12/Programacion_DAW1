import java.util.Scanner;

public class TaulaMultiplicar {
    public static void main (String[] args){
        Scanner sc = new Scanner (System.in);
        int multiplicador = 10;
        int num;

        System.out.println("Nombre que vols multiplicar:");
        num = sc.nextInt();

        while (multiplicador > 0){
            System.out.println(num * multiplicador);
            multiplicador--;
        }
        System.out.println();
    }
}
