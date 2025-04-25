import java.util.Scanner;

public class Exercici4y25 {
    static Scanner sc = new Scanner(System.in);
    static String num;
    public static void main(String[] args) {
       LlegirNombres();
       CalculPosicio();
    }

    public static void LlegirNombres(){
        System.out.println("Introdueix un nombre positiu amb longitud màxima de 15: ");
        num = sc.nextLine();
    }

    public static void CalculPosicio(){
        int max = num.charAt(0);
        int min = num.charAt(0);
        int posicioMax = 0;
        int posicioMin = 0;

        if (num.length() > 15) {
            System.out.println("La longitud debe ser menor que 15");
        }else {
            for(int i = 1; i < num.length(); i++) {
                if (num.charAt(i) > max) {
                    max = num.charAt(i);
                    posicioMax = i;
                }
                if (num.charAt(i) < min) {
                    min = num.charAt(i);
                    posicioMin = i;
                }
            }
            System.out.println("El nombre mes gran es " + (char)max + " y la seva posicio es " + (posicioMax +1));
            System.out.println("El nombre mes petit es " + (char)min + " y la seva posicio es " + (posicioMin +1));
        }
    }

}
