import java.util.Scanner;
public class Endevinar {
    public static void main (String[] args) {
        Scanner lector = new Scanner(System.in);
        final int VALOR_SECRET = 4;
        int INTENTOS = 5;
        System.out.println("Comencem el joc.");
        System.out.println("Endevina el valor enter, entre 0 i 10.");
        boolean haEncertat = false;
        while (haEncertat == false && INTENTOS > 0) {
            System.out.print("Quin valor creus que és? ");
            INTENTOS--;
            int valorUsuari = lector.nextInt();
            lector.nextLine();
            if (VALOR_SECRET != valorUsuari) {

                System.out.print("Has fallat! Torna a intentar-ho...\n");

            } else {
                haEncertat = true;
                System.out.println("Enhorabona. Per fi l'has encertat!");

            }

        }
    }
}
