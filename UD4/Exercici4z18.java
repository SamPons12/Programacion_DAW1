import java.util.Scanner;

public class Exercici4z18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int exponent = 0;
        int[] array = new int[100];
        int index = 0;

        System.out.println("Introdueix el nombre deciaml: ");
        int nombre = sc.nextInt();
        System.out.println("Introdueix la base per transformar el nombre: ");
        int base = sc.nextInt();

        if (nombre == 0) {
            System.out.println("El nombre en binari es 0");
        } else if (nombre < 0) {
            System.out.println("El nombre ha de ser positiu");
        } else {
            while (nombre > base) {
                array[index++] = nombre % base;
                nombre = nombre / base;
            }
            array[index++] = nombre;
            for (int i = index; i >= 0; i--) {
                System.out.print(array[i] + " * " + "(" + base + "^" + i + ")" + " + ");
            }
        }
    }
}



