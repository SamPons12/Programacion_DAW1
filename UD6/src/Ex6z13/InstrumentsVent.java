package Ex6z13;

public class InstrumentsVent extends InstrumentsMusicals implements Sons {

    public InstrumentsVent(String nom) {
        super(nom);
    }

    @Override
    public void soProduit() {
        System.out.println("El so produït per els instruments de vent és un so de buf.");
    }
}
