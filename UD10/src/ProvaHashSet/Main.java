package ProvaHashSet;

import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    HashSet<Persona> persones = new HashSet<>();

  Persona p1 = new Persona("Joan", 20, "123456789F");
  Persona p2 = new Persona("Joan", 20, "123456789F");
  Persona p3 = new Persona("Joan", 20, "123456789F");

  persones.add(p1);
  persones.add(p2);
  persones.add(p3);

  System.out.println(persones.size());

  }
}
