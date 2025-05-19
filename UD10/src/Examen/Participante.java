package Examen;

import java.util.Objects;

public class Participante {
  private String nombre;
  private int edad;
  private String prueba;

  public Participante(String nombre, int edad, String prueba){
    this.nombre = nombre;
    this.edad = edad;
    this.prueba = prueba;
  }

  public String getPrueba(){
    return prueba;
  }

  public int getEdad(){
    return edad;
  }

  public String getNom(){
    return nombre;
  }

  @Override
  public boolean equals(Object o){
    if (o == this) return true;
    if (!(o instanceof Participante)) return false;
    Participante p = (Participante)o;

    return p.nombre.equals(this.nombre)
            && p.prueba.equals(this.prueba);
  }
  
  @Override
  public int hashCode(){
    return Objects.hash(nombre, prueba);
  }
}
