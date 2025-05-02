package GestionAlmacen;

import java.util.Objects;

public class Producte {
  private int id;
  private String nom;
  private String categoria;
  private int cantidad;
  private double preu;

  public Producte(int id, String nom, String categoria, int cantidad, double preu){
    this.id = id;
    this.cantidad = cantidad;
    this.categoria = categoria;
    this.nom = nom;
    this.preu = preu;
  }

  @Override
  public boolean equals(Object o){
    if (o == this) return true;
    if (!(o instanceof Producte)) return false;
    Producte p = (Producte)o;
    return p.id == this.id 
            && p.nom.equals(this.nom) 
            && p.categoria.equals(this.categoria) 
            && p.cantidad == this.cantidad 
            && p.preu == this.preu;
  }

  @Override
  public int hashCode(){
    return Objects.hash(id, nom, cantidad, categoria, preu);
  }

  @Override
  public String toString(){
    return "ID: " + id + "Nom: " + nom + "Categoria" + categoria + "Cantidad: " + cantidad + "Preu" + preu;
  }
}
