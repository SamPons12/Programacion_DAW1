public class CompteBancari {

    private String dniTitular;
    private String nom;
    private float saldo;

    public CompteBancari(){

    }

    public CompteBancari(String dniTitular, String nom, float saldo){
        this.dniTitular = dniTitular;
        this.nom = nom;
        this.saldo = saldo;
    }

    public String getDniTitular() {
        return dniTitular;
    }

    public void ingresar(float cantidad) {
        saldo  = saldo + cantidad;

    }

    public void retirar(float cantidad) {
        saldo = saldo - cantidad;

        if (saldo < 0) {
            System.out.println("Saldo insuficiente");
        }
    }

    public float getSaldo() {
        return saldo;
    }
}
