import java.util.Scanner;

public class DescompteRest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double preu;
        final double PREU_MINIM = 80;
        final double DESCOMPTE = 10;

        System.out.println("Introdueix preu: ");
        preu = sc.nextDouble();

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

