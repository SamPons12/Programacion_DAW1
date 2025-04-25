import java.util.Scanner;

public class CercarDivisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        boolean primo = false;

        System.out.print("Introdueix un nombre: ");
        num = sc.nextInt();

        if(num > 1){
            for(int i = num - 1; i > 1; i--){
                if(num % i == 0 ){
                    System.out.println("Divisor es " + i);
                    primo = true;
                    break;
                }
            }
        }else{
            System.out.println("El nombre ha de ser major que 1 o positiu");
        }

        if (!primo){
            System.out.println("NO, ES UN PRIMO");
        }

    }
}
