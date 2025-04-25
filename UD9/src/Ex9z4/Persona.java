package Ex9z4;

public class Persona {
  private String nom;
  private String dni;
  private int edat;

  public Persona(String nom, String dni, int edat) {
      this.nom = nom;
      this.dni = dni;
      this.edat = edat;
  }

  public String getNom() {
      return nom;
  }

  public String getDni() {
      return dni;
  }

  public int getEdat() {
      return edat;
  }

  public void mostrarInfo() {
      System.out.println("Nom: " + nom + ", DNI: " + dni + ", Edat: " + edat);
  }
}
