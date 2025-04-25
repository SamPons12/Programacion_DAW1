public class Persona {
    private String dni;
    private String nom;
    private short edat;

    public Persona() {

    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setNom(String nombre) {
        this.nom = nombre;
    }

    public String getNom() {
        return nom;
    }

    public void setEdat(short edat) {
        this.edat = edat;
    }

    public short getEdat() {
        return edat;
    }

    public void imprimir() {
        System.out.println("Visualització de dades de la persona " + nom  + "\n" + "DNI.................." + dni + "\n" + "Nom.................." + nom + "\n" + "Edat.............." + edat);
    }

}
