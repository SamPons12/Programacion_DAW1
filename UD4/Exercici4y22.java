import java.util.Scanner;

public class Exercici4y22 {

    private static int[] llistaEnters = new int[10];

    public static void main(String[] args) {
        llegirLlista();
        ordenarLlista();
        mostrarLlista();
        MeitatInf();
    }

    public static void llegirLlista() {
        System.out.println("Escriu 10 valors enters i prem retorn.");
        Scanner lector = new Scanner(System.in);
        int index = 0;
        while (index < llistaEnters.length) {
            if (lector.hasNextInt()) {
                llistaEnters[index] = lector.nextInt();
                index++;
            } else {
                lector.next();
            }
        }
        lector.nextLine();
    }

    public static void ordenarLlista() {
        for (int i = 0; i < llistaEnters.length - 1; i++) {
            for(int j = i + 1; j < llistaEnters.length; j++) {
                if (llistaEnters[i] > llistaEnters[j]) {
                    int canvi = llistaEnters[i];
                    llistaEnters[i] = llistaEnters[j];
                    llistaEnters[j] = canvi;
                }
            }
        }
    }

    public static void mostrarLlista() {

        System.out.print("L'array ordenat és: [ ");
        for (int i = 0; i < llistaEnters.length;i++) {
            System.out.print(llistaEnters[i] + " ");
        }
        System.out.println("]");
    }

    public static void MeitatInf(){
        int canvi = llistaEnters[9]/2;
        int nvegades = 0;
        for (int i = 0; i < llistaEnters.length -2; i++){
            if(llistaEnters[i] < canvi){
               nvegades++;
            }
        }
        System.out.println(nvegades);
    }
}
