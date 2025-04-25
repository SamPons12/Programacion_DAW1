import java.util.Scanner;

public class CaracWhile {
    public static void main (String[] args){
        int i = 0;
        int repeticions;
        Scanner sc = new Scanner (System.in);

        System.out.println("Quants de caracters vols que sortin per pantalla?: ");
        repeticions = sc.nextInt();

        while (i < repeticions) {
            System.out.println("-");
            i++;
        }
        System.out.println();
    }
}
