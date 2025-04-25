import java.util.Scanner;

public class Examen {

    static Scanner sc = new Scanner(System.in);
    static int index = 0;
    static float[] notas = new float[10];
    static boolean menu = true;

    public static void main(String[] args) {
        while(menu){
            menu();
        }
    }

    public static void menu(){
        System.out.println("Menú:");
        System.out.println("1. Añadir notas");
        System.out.println("2. Mostrar todas las notas");
        System.out.println("3. Calcular estadísticas");
        System.out.println("4. Salir");
        System.out.println("Selecciona una opción: ");
        int opcioUser = sc.nextInt();
        sc.nextLine();

        switch(opcioUser){
            case 1:
                anadirNota();
                break;
            case 2:
                 mostrarNotas();
                 break;
            case 3:
                 calcularEstadisticas();
                 break;
             case 4:
                 salir();
                 break;
                 default:
                     System.out.println("Introdueix un nombre valid");
        }
    }

    public static void anadirNota(){
        float nota = 0;
        while (index < 10){
            do {
                System.out.println("Introduce la nota (entre 0 y 10):");
                nota = sc.nextFloat();

            }while(nota < 0 || nota > 10 );
            notas[index] = nota;
            index++;
        }
    }

    public static void mostrarNotas(){
        System.out.println("Notas introducidas: ");
        for (int i = 0; i < notas.length; i++) {
            if(notas[0] == 0 ){
                System.out.println("Lista vacia");
                break;
            }else{
                System.out.println(notas[i]);
            }
        }
    }

    public static void calcularEstadisticas(){
        float notaMedia = 0;
        float max = notas[0];
        float min = notas[0];
        if(notas[0] == 0){
            System.out.println("No se puede calcular debido a que la lista esta vacia");
        }else{
            for (int i = 0; i < notas.length; i++) {
                notaMedia = notaMedia + notas[i];
                if (max < notas[i]){
                    max = notas[i];
                }
                if (min > notas[i]){
                    min = notas[i];
                }
            }
            notaMedia = notaMedia / index;
            System.out.println("Estadisticas: ");
            System.out.println("Nota media: " + notaMedia);
            System.out.println("Nota máxima: " + max);
            System.out.println("Nota minima: " + min);
        }
    }

    public static void salir(){
        System.out.println("¿Estás seguro que quieres salir? (sí/no): ");
        String opcioFinal = sc.nextLine();
        if(opcioFinal.equals("sí")){
            System.out.println("Fin del programa");
            menu = false;
        }else {
            menu = true;
        }

    }
}
