import java.util.Arrays;
import java.util.Scanner;

public class Ordenacio {
    static Scanner sc = new Scanner(System.in);
    static boolean menu = false;
    static int[] ordenar = new int[6];

    public static void main(String[] args) {
        
        for(int i = 0; i < ordenar.length; i++){
            System.out.println("Introdueix un nombre a la posicio " + i);
            int opcio = sc.nextInt();
            sc.nextLine();
            ordenar[i] = opcio;
        }

        while (!menu) {
            System.out.println("1. Inserció directa");
            System.out.println("2. Selecció directa");
            System.out.println("3. Bombolla");
            System.out.println("4. Sortir");
            int opcioUsuari = sc.nextInt();
            sc.nextLine();

            switch (opcioUsuari) {
                case 1:
                    System.out.println("Antes: " + new String (Arrays.toString(ordenar)));
                    insercioDirecta();
                    System.out.println("Despues: " + insercioDirecta());
                    break;
                case 2:
                    System.out.println("Antes: " + new String (Arrays.toString(ordenar)));
                    seleccioDirecta();                    
                    System.out.println("Despues: " + seleccioDirecta());
                    break;
                case 3:
                    System.out.println("Antes: " + new String (Arrays.toString(ordenar)));
                    bombolla();
                    System.out.println("Despues: " + bombolla());
                    break;
                case 4:
                    menu = true;    
                default:
                    break;
            }
        }
    }

    public static String insercioDirecta(){

        for (int i = 1; i < ordenar.length; i++) {
            int actual = ordenar[i];
            int j = i - 1;

            while (j >= 0 && ordenar[j] > actual) {
                ordenar[j + 1] = ordenar[j];
                j = j - 1;
            }
            ordenar[j + 1] = actual;
        }
        return new String(Arrays.toString(ordenar));
    }

    public static String seleccioDirecta(){

        for (int i = 0; i < ordenar.length - 1; i++) {
            int petit = i;

            for (int j = i + 1; j < ordenar.length; j++) {
                if (ordenar[j] < ordenar[petit]) {
                    petit = j;
                }
            }

            int intercambi = ordenar[petit];
            ordenar[petit] = ordenar[i];
            ordenar[i] = intercambi;
        }
        return Arrays.toString(ordenar);
    }

    public static String bombolla(){
        boolean cambi;

        for (int i = 0; i < ordenar.length - 1; i++) {
            cambi = false;

            for (int j = 0; j < ordenar.length - 1 - i; j++) {
                if (ordenar[j] > ordenar[j + 1]) {

                    int realitzarCambi = ordenar[j];
                    ordenar[j] = ordenar[j + 1];
                    ordenar[j + 1] = realitzarCambi;
                    cambi = true;
                }
            }

            if (!cambi) break;
        }
        return Arrays.toString(ordenar);
    }

}
