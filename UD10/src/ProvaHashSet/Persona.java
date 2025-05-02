package ProvaHashSet;

import java.util.Objects;

public class Persona {
  private String nom;
  private int edat;
  private String dni;

  public Persona(String nom, int edat, String dni){
    this.nom = nom;
    this.edat = edat;
    this.dni = dni;
  }

  @Override
  public boolean equals(Object o){
    if (o == this) {return true;}
    if (!(o instanceof Persona)) {return false;}

    return this.dni.equals(((Persona)o).dni) && this.nom.equals(((Persona)o).nom) && this.edat == (((Persona)o).edat);
  }

  @Override
  public int hashCode(){
    return Objects.hash(dni, nom, edat);
  }
}
