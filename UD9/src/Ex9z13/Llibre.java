package Ex9z13;

public class Llibre {
  private String nom;
  private String editorial;
  private String autor;

  public Llibre(String nom, String editorial, String autor){
    this.nom = nom;
    this.editorial = editorial;
    this.autor = autor;
  }

  public String getNom(){
    return nom;
  }
}
