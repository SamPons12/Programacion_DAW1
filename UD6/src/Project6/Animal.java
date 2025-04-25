package Project6;

public class Animal extends SerVivo{
    private String tipoAnimal;
    //Nombre animal me refiero a un nombre ej.Jose, Joan...
    private String nombreAnimal;
    private String claseAnimal;


    public Animal(int edat, double estatura, String tipoAnimal, String nombreAnimal, String claseAnimal){
        super(edat, estatura);
        this.tipoAnimal = tipoAnimal;   
        this.nombreAnimal = nombreAnimal;
        this.claseAnimal = claseAnimal;
    }

    public String getNombreAnimal(){
        return nombreAnimal;
    }

    public String toString(){
        String s = tipoAnimal + " se llama " + nombreAnimal + " es de la familia de los " + claseAnimal;
        return s;
    }
}
