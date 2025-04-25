package Ex6z9;
public class Comercial extends Treballador {
    
    public Comercial(String nom, double salari) {
        super(nom, salari);
    }

    @Override
    public void treballar() {
        System.out.println("Atén clients i genera vendes.");
    }
    
}
