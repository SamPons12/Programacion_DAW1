import java.util.Scanner;

public class IntervalNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1;
        int n2;
        System.out.print("Introdueix el nombre 1: ");
        n1 = sc.nextInt();
        System.out.print("Introdueix el nombre 2: ");
        n2 = sc.nextInt();
        if(n1 < n2){
            for(int i = n1; i < n2; i++){
                System.out.println(i);
            }
        }else {
            System.out.println("ERROR DE CODI");
        }
    }
}
