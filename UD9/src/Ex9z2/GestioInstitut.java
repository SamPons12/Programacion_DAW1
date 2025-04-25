package Ex9z2;

import java.util.Hashtable;
import java.util.Scanner;

public class GestioInstitut {
    static Scanner sc = new Scanner(System.in);
    static Hashtable<String, Persona> personas = new Hashtable<>();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("------Programa Gestión Instituto------");
            System.out.println("1. Crear Profesor");
            System.out.println("2. Crear Alumno");
            System.out.println("3. Ver Datos");
            System.out.println("4. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregarProfesor();
                    break;
                case 2:
                    agregarAlumno();
                    break;
                case 3:
                    verDatos();
                    break;
                case 4:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 4);
    }

    public static void agregarProfesor() {
        System.out.println("Ingrese el DNI del profesor: ");
        String dni = sc.nextLine();
        
        if (personas.containsKey(dni)) {
            System.out.println("Ja hi ha colcu amb aquest DNI!!!");
            return;
        }

        System.out.println("Ingrese el nombre del profesor: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese la edad del profesor: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el módulo del profesor: ");
        String modulo = sc.nextLine();

        personas.put(dni, new Profesor(nombre, dni, edad, modulo));
    }

    public static void agregarAlumno() {
        System.out.println("Ingrese el DNI del alumno: ");
        String dni = sc.nextLine();

        if (personas.containsKey(dni)) {
            System.out.println("Ja hi ha colcu amb aquest dni: ");
            return;
        }

        System.out.println("Ingrese el nombre del alumno: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese la edad del alumno: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el nivel del alumno: ");
        String nivel = sc.nextLine();

        personas.put(dni, new Alumno(nombre, dni, edad, nivel));
    }

    public static void verDatos() {
        if (personas.isEmpty()) {
            System.out.println("No hi ha informacio");
        }

        for (Persona p : personas.values()) {
            if (p instanceof Profesor) {
                System.out.println("******* Datos Profesor *******");
            } else if (p instanceof Alumno) {
                System.out.println("******* Datos Alumno *******");
            }
            p.mostrarDades();
        }
    }
}
