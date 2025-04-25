import java.util.Scanner;

public class GestioZoo {
    static Scanner sc = new Scanner(System.in);

    static Persona[] persona = new Persona[100];
    static int index = 0;
    public static void main(String[] args) {

        int opcion;
        do {
            System.out.println("------Programa Gestion Zoo------");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Crear Cuidador");
            System.out.println("3. Ver Datos");
            System.out.println("4. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    persona[index++] = crearClient();
                    break;
                case 2:
                    persona[index++] = crearCuidador();
                    break;
                case 3:
                    verDatos();
                    break;
                case 4:
                    salir();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while(opcion != 4);

    }

    public static Persona crearClient(){

        System.out.println("Ingrese el nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el DNI del cliente: ");
        String dni = sc.nextLine();

        System.out.println("Ingrese la edad del cliente: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese si el cliente tiene ticket: ");
        String ticket = sc.nextLine();

        return new Clients(nombre, dni, edad, ticket);
    }

    public static Persona crearCuidador(){

        System.out.println("Ingrese el nombre del cuidador: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el DNI del cuidador: ");
        String dni = sc.nextLine();

        System.out.println("Ingrese la edad del cuidador: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el animal que cuida el cuidador: ");
        String animal = sc.nextLine();

        return new Cuidador(nombre, dni, edad, animal);

    }

    public static void verDatos(){
        for (int i = 0; i < index; i++) {
            if (persona[i] instanceof Clients) {
                System.out.println("*******Datos Cliente*******");
                persona[i].mostrarDades();
            }else if (persona[i] instanceof Cuidador) {
                System.out.println("*******Datos Cuidador*******");
                persona[i].mostrarDades();
            }
        }
    }

    public static void salir(){
        System.out.println("Fin del programa");
        sc.close();
    }
}
