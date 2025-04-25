package Ex6z9;
public class Administrador extends Treballador {
    
    public Administrador(String nom, double salari) {
        super(nom, salari);
    }

    @Override
    public void treballar() {
        System.out.println("Gestiona tasques administratives i documentació.");
    }
    
}
