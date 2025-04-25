package Ex6z9;
public class Enginyer extends Treballador {

    public Enginyer(String nom, double salari) {
        super(nom, salari);
    }

    @Override
    public void treballar() {
        System.out.println("Resol problemes tècnis i deissenya solucions.");
    }
    
}
