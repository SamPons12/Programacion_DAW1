import java.util.Scanner;

public class Exercici4z17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Conversió de nombre sencer a binari ");
        System.out.println("Introdueix el nombre deciaml: ");
        int nombre = sc.nextInt();

        if (nombre == 0) {
            System.out.println("El nombre en binari es 0");
        }else if (nombre < 0){
            System.out.println("El nombre ha de ser positiu");
        }else{
            String binari = "";
            while (nombre > 0) {
                binari = (nombre % 2) + binari;
                nombre = nombre / 2;
            }
            System.out.println("Nombre es binari = " + binari);
        }
    }
}
