import java.util.Scanner;

public class NotaAlumne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double nota;

        System.out.println("Introdueix la nota: ");
        nota = sc.nextDouble();

        if (nota <= 10 && nota >= 0){
            if (nota >= 9) {
                System.out.println("Excel·lent");
            }else if (nota >= 6.5) {
                System.out.println("Notable");
            }else if (nota >= 5) {
                System.out.println("Suficient");
            }else {
                System.out.println("Suspès");
            }
        }else{
            System.out.println("Introdueix un nombre vàlid");
        }
    }
}
