package Ex6z13;

public class InstrumentsPercusio extends InstrumentsMusicals implements Sons {

    public InstrumentsPercusio(String nom) {
        super(nom);
    }

    @Override
    public void soProduit() {
        System.out.println("El so produït per la percussió és un so de cop.");
    }   
}

