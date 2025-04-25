import java.util.Scanner;

public class Exercici4y21 {
    public static void main(String[] args) {
        int[] billetes = {500, 200, 100, 50, 20, 10, 5};
        Scanner in = new Scanner(System.in);
        System.out.println("INtrodueix una quantitat d'euros multiples de 5: ");
        int quantitat = in.nextInt();

        if (quantitat % 5 != 0){
            System.out.println("Ha de ser multiples de 5: ");
        }else {
            System.out.println("Per poder donar" + quantitat + "es necesiten: ");
            for (int billet : billetes){
                int nombreBilletes = quantitat / billet;
                if(nombreBilletes > 0){
                    System.out.println(nombreBilletes + "billet(s) de" + billet);
                    quantitat %= billet;
                }
            }
        }
    }
}
