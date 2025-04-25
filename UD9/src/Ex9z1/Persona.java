package Ex9z1;

public class Persona {
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
