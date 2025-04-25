import java.util.Scanner;

public class DescompteNext {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double preu = 0;
        final double PREU_MINIM = 80;
        final double DESCOMPTE = 10;

        System.out.println("Introdueix preu: ");

        if (sc.hasNextDouble()) {
            preu = sc.nextDouble();
        }
        else {
            System.out.println("Nomes dades numeriques");
        }

        if(preu > 0){

            if (preu >= PREU_MINIM) {
                preu = preu - preu * DESCOMPTE/100;
            }else if (preu < 30) {
                preu = preu + 2;
            }

            System.out.println("El preu final és: " + preu);
        }else {
            System.out.println("EL preu ha de ser positiu");
        }
    }
}
