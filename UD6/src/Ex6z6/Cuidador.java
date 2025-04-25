public class Cuidador extends Persona{

    private String animal;

    public Cuidador(String nombre, String dni, int edad, String animal){
        super(nombre, dni, edad);
        this.animal = animal;
    }

    public void mostrarDades(){
        super.mostrarDades();
        System.out.println("Animal que cuida: " + animal);
    }
}
