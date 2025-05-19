package Ex10z8;

public class Profesor extends Persona{

    private static final int PERCENTAGE = 15;
    private String asignatura;

    public Profesor(String nombre, String dni, int edat, String assignatura){
        super(nombre, dni, edat);
        this.asignatura = assignatura;
    }

    public void mostrarDades(){
        super.mostrarDades();
        System.out.println("Assignatura: " + asignatura);
    }

    public int suma (int a, int b){
        return a + b + PERCENTAGE;
    }


}
