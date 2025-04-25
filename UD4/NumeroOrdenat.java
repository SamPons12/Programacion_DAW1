import java.util.Scanner;

public class NumeroOrdenat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1;
        int num2;
        int num3;

        System.out.print("Introdueix el primer nombre: ");
        num1 = sc.nextInt();
        System.out.print("Introdueix el segon nombre: ");
        num2 = sc.nextInt();
        System.out.print("Introdueix el tercer nombre: ");
        num3 = sc.nextInt();

        if (num1 > num2 && num1 > num3 && num2 > num3) {
            System.out.println("El nombre mes gran es " + num1 + "-" + num2 + "-" + num3);
        }else if (num2 > num1 && num2 > num3 && num1 > num3) {
            System.out.println("El nombre mes gran es " + num2 + "-" + num1 + "-" + num3);
        }else if (num3 > num2 && num3 > num1 && num2 > num1) {
            System.out.println("El nombre mes gran es " + num3 + "-" + num2 + "-" + num1);
        }else if (num1 > num2 && num1 > num3 && num3 > num2) {
            System.out.println("El nombre mes gran es " + num1 + "-" + num3 + "-" + num2);
        }else if (num2 > num1 && num2 > num3 && num3 > num1) {
            System.out.println("El nombre mes gran es " + num2 + "-" + num3 + "-" + num1);
        }else {
            System.out.println("El nombre mes gran es " + num3 + "-" + num1 + "-" + num2);
        }
    }
}
