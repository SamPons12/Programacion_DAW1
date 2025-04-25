import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sortir = false;
        
        while (!sortir) {
            System.out.println("1. Provocar InputMismatchException");
            System.out.println("2. Provocar IndexOutOfBoundsException");
            System.out.println("3. Provocar ArithmeticException");
            System.out.println("4. Sortir");
            int opcio = scanner.nextInt();

            try {
                
                switch (opcio) {
                    case 1:
                        System.out.print("Introdueix un número enter: ");
                        int num = scanner.nextInt();
                        break;
                    case 2:
                        int[] array = {1, 2, 3};
                        System.out.println("Valor fora de rang: " + array[5]);
                        break;
                    case 3:
                        System.out.print(7/0);
                        break;
                    case 4:
                        sortir = true;
                        break;
                    default:
                        System.out.println("Opció no vàlida. Torna a intentar-ho.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no vàlida. Has d'introduir un número enter.");
                e.printStackTrace();
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Intent d'accedir a una posició inexistent de l'array.");
                e.printStackTrace();
            } catch (ArithmeticException e) {
                System.out.println("No es pot dividir per zero.");
                e.printStackTrace();
            } 
        }
    }
}
