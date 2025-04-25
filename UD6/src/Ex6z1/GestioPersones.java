public class GestioPersones {
    public static void main(String[] args) {
        Persona p1 = new Persona();

        p1.setDni("41749829E");
        p1.setNom("Miquel");
        p1.setEdat((short)12);

        p1.imprimir();
    }
}
