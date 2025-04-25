package Ex10z8;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private String dni;
    private int edad;

    public Persona(String nombre, String dni, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
    }

    public void mostrarDades(){
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Edad: " + edad);
    }

}
