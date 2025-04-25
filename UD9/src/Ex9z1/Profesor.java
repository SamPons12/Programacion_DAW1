package Ex9z1;

public class Profesor extends Persona{

    private String asignatura;

    public Profesor(String nombre, String dni, int edat, String assignatura){
        super(nombre, dni, edat);
        this.asignatura = assignatura;
    }

    public void mostrarDades(){
        super.mostrarDades();
        System.out.println("Assignatura: " + asignatura);
    }
}
