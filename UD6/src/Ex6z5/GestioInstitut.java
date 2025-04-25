import java.util.Scanner;

public class GestioInstitut {
    static Scanner sc = new Scanner(System.in);

    static Persona[] persona = new Persona[100];
    static int index = 0;
    public static void main(String[] args) {

    int opcion;
        do {
            System.out.println("------Programa Gestion Instituto------");
            System.out.println("1. Crear Profesor");
            System.out.println("2. Crear Alumno");
            System.out.println("3. Ver Datos");
            System.out.println("4. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    persona[index++] = crearProfesor();
                    break;
                case 2:
                     persona[index++] = crearAlumno();
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

    public static Persona crearProfesor(){

        System.out.println("Ingrese el nombre del profesor: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el DNI del profesor: ");
        String dni = sc.nextLine();

        System.out.println("Ingrese la edad del profesor: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el modulo del profesor: ");
        String modulo = sc.nextLine();

        return new Profesor(nombre, dni, edad, modulo);
    }

    public static Persona crearAlumno(){

        System.out.println("Ingrese el nombre del alumno: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el DNI del alumno: ");
        String dni = sc.nextLine();

        System.out.println("Ingrese la edad del alumno: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el nivel del alumno: ");
        String nivel = sc.nextLine();

        return new Alumno(nombre, dni, edad, nivel);

    }

    public static void verDatos(){
        for (int i = 0; i < index; i++) {
            if (persona[i] instanceof Profesor) {
                System.out.println("*******Datos Profesor*******");
                persona[i].mostrarDades();
            }else if (persona[i] instanceof Alumno) {
                System.out.println("*******Datos Alumno*******");
                persona[i].mostrarDades();
            }
        }
    }

    public static void salir(){
        System.out.println("Fin del programa");
        sc.close();
    }
}
