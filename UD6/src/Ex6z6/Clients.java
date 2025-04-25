public class Clients extends Persona {

    private String ticket;

    public Clients(String nombre, String dni, int edad, String ticket){
        super(nombre, dni, edad);
        this.ticket = ticket;
    }

    public void mostrarDades(){
        super.mostrarDades();
        System.out.println("Ticket: " + ticket);
    }
}
