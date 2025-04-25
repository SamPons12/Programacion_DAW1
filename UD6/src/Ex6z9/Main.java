package Ex6z9;
public class Main {
    
    public static void main(String[] args) {
      Treballador[] treballadors = {
        new Enginyer("Pere", 2000),
        new Administrador("Anna", 1500),
        new Comercial("Marta", 1800)
      };

        for (Treballador treballador : treballadors) {
            System.out.println(treballador);
            treballador.treballar();
            treballador.cobrar();
            System.out.println("********************************");
        }


    }
    
}
