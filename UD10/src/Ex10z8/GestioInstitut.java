package Ex10z8;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GestioInstitut {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Alumno> alumnos = new ArrayList<>();
    static ArrayList<Profesor> profesores = new ArrayList<>();

    public static void main(String[] args) {
        File f  = null;
        FileInputStream fis = null;
        ObjectInputStream input = null;

        try {
            f = new File("C:\\Users\\Sam\\Documents\\persona.dat");
          
            if (f.exists() && f.length() > 0) {
                fis = new FileInputStream(f);
                input = new ObjectInputStream(fis);
                alumnos = (ArrayList<Alumno>) input.readObject();
                profesores = (ArrayList<Profesor>) input.readObject();

            }else{
                f.createNewFile();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(f.length() > 0){fis.close(); input.close();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

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
                    profesores.add((Profesor) crearProfesor());
                    break;
                case 2:
                    alumnos.add((Alumno) crearAlumno());
                    break;
                case 3:
                    verDatos();
                    break;
                case 4:
                    salir(f);
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
       for (Alumno alumno : alumnos) {
            System.out.println("-----------ALUMNO---------");
            alumno.mostrarDades();
       }

       for (Profesor profesor : profesores) {
            System.out.println("----------------PROFE------------");
            profesor.mostrarDades();
       }
    }

    public static void salir(File f){

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(alumnos);
            oos.writeObject(profesores);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fin del programa");
        sc.close();
    }
}
