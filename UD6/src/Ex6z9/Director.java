package Ex6z9;
public class Director extends Treballador {
    
    public Director(String nom, double salari) {
        super(nom, salari);
    }

    @Override
    public void treballar() {
        System.out.println("Supervisa els equips i pren decisions estratègiques.");
    }
    
}
