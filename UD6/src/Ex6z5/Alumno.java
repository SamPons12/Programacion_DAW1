public class Alumno extends Persona {

    private String nivel;

    public Alumno(String nom, String dni, int edat, String nivell) {
        super(nom, dni, edat);
        this.nivel = nivell;
    }

    public void mostrarDades(){
        super.mostrarDades();
        System.out.println("Nivlle: " + nivel);
    }
}
