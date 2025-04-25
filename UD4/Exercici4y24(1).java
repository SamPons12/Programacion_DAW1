import java.util.Scanner;

public class Exercici4y24 {
    static int euros = 0;
    static String moneda;
    public static void main(String[] args) {
        llegirEuros();
        llegirMoneda();
        conversor();
        
    }
    public static void llegirEuros(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix euros: ");
        euros = sc.nextInt();
        sc.nextLine();

    }

    public static void llegirMoneda(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix moneda: ");
        moneda = sc.nextLine();
    }

    public static void conversor(){
        switch (moneda){
            case "Lliures":
                System.out.println("En lliures seria: " + euros * 0.85);
                break;
            case "Iens":
                System.out.println("En Iens seria: " + euros * 131.66);
                break;
            case "Dolars":
                System.out.println("En Dolars seria: " + euros * 1.16);
                break;
                default:
                    System.out.println("Entrada Invalida");
                    break;
        }
    }
}
