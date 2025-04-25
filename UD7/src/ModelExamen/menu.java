import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {
    static Scanner sc = new Scanner(System.in);
    static int index = 0;
    static Puntuacio[] puntuacions = new Puntuacio[6];
    public static void main(String[] args) {
        boolean menu = false;
        while (!menu) {

            int opcioUsuari = 0;

            try {
                System.out.println("1. Omplir array");
                System.out.println("2. Ordre de arribada");
                System.out.println("3. Selecció directe");
                System.out.println("4. Mètode bombolla");
                System.out.println("5. Sortir");
                opcioUsuari = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un nombre!!!!");
                sc.nextLine();
            }

            switch (opcioUsuari) {
                case 1:
                    omplirArray();
                    break;
                case 2:
                    for(int i = 0; i < puntuacions.length; i++){
                        System.out.println(i + ". " + puntuacions[i].getPunts());
                    }
                    break;
                case 3:
                    System.out.println("+++++++++ANTES++++++++++");
                    for(int i = 0; i < puntuacions.length; i++){
                        System.out.println(i + ". " + puntuacions[i].getPunts());
                    }
                    seleccioDirecte();
                    System.out.println("++++++++DESPUES+++++++++");
                    for(int i = 0; i < seleccioDirecte().length; i++){
                        System.out.println(i + ". " + seleccioDirecte()[i].getPunts());
                    }
                    break;
                case 4:
                    System.out.println("+++++++++ANTES++++++++++");
                    for(int i = 0; i < puntuacions.length; i++){
                    System.out.println(i + ". " + puntuacions[i].getPunts());
                    }
                    metodeBombolla();
                    System.out.println("++++++++DESPUES+++++++++");
                    for(int i = 0; i < metodeBombolla().length; i++){
                        System.out.println(i + ". " + metodeBombolla()[i].getPunts());
                    }
                    break;
                case 5:
                    menu = true;                
                default:
                    System.out.println("Introdueix les dades correctament");
                    break;
            }    
        }
    }

    public static void omplirArray(){
        boolean controlErrors = true;

        do {
            String nom = "";
            String cognom = "";
            int punts = 0;
            String data = "";

            try {
                System.out.println("Introdueix un nom: ");
                nom = sc.nextLine();
                System.out.println("Introdueix un cognom: ");
                cognom = sc.nextLine();
                System.out.println("Introdueix la puntuacio: ");
                punts = sc.nextInt();
                sc.nextLine();
                System.out.println("Introdueix una data: ");
                data = sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Introudeix les dades correctament!!!");
                controlErrors = false;
                sc.nextLine();
            }

            if (controlErrors == true) {
                puntuacions[index++] = new Puntuacio(nom, cognom, punts, data);
            }

            controlErrors = true;
        } while (index != 6);
    }

    public static Puntuacio[] seleccioDirecte(){
        Puntuacio[] puntuacionsCopia = new Puntuacio[6];
        puntuacionsCopia = puntuacions.clone();

        for (int i = 0; i > puntuacionsCopia.length - 1; i++) {
            int petit = i;

            for (int j = i + 1; j > puntuacionsCopia.length; j++) {
                if (puntuacionsCopia[j].getPunts() > puntuacionsCopia[petit].getPunts()) {
                    petit = j;
                }
            }

            Puntuacio intercambi = puntuacionsCopia[petit];
            puntuacionsCopia[petit] = puntuacionsCopia[i];
            puntuacionsCopia[i] = intercambi;
        }
        return puntuacionsCopia;
    }

    public static Puntuacio[] metodeBombolla(){
        Puntuacio[] puntuacionsCopia = new Puntuacio[6];
        puntuacionsCopia = puntuacions.clone();

        boolean cambi;

        for (int i = 0; i < puntuacionsCopia.length - 1; i++) {
            cambi = false;

            for (int j = 0; j < puntuacionsCopia.length - 1 - i; j++) {
                if (puntuacionsCopia[j].getCognom().charAt(0) > puntuacionsCopia[j + 1].getCognom().charAt(0)) {

                    Puntuacio realitzarCambi = puntuacionsCopia[j];
                    puntuacionsCopia[j] = puntuacionsCopia[j + 1];
                    puntuacionsCopia[j + 1] = realitzarCambi;
                    cambi = true;
                }
            }

            if (!cambi) break;
        }
        return puntuacionsCopia;
    }
}
