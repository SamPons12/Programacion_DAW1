package Ex6z9;
public abstract class Treballador{

    private String nom;
    private double salari;

    public Treballador(String nom, double salari){
        this.nom = nom;
        this.salari = salari;
    }

    public void cobrar(){
        System.out.println("El treballador " + nom + " cobra " + salari + " euros");
    }

    public double getSalari(){
        return salari;
    }

    public String getNom(){
        return nom;
    }

    public void treballar(){
        System.out.println("Aquest treballador realitza tasques generals.");
    }

    @Override
    public String toString(){
        return "Treballador " + nom + " " + salari ;
    }
}