package Ex9z10;

import java.util.Iterator;
import java.util.TreeSet;


public class Main {
  public static void main(String[] args) {
    TreeSet<String> tree = new TreeSet<>();

    //Afegir cadenes de text al TreeSet (noms, ciutats, animals, etc).
    tree.add("leon");
    tree.add("joan");
    tree.add("Madrid");
    tree.add("Italia");

    //Mostrar els elements del TreeSet emprant un iterador. En quin ordre es mostren?

    while (tree.iterator().hasNext()) {
      System.out.println(tree.iterator().next());
    }

    //Verificar si una cadena de text específica està present al TreeSet.
    if (tree.contains("Madrid")) {
      System.out.println("Esta present");
    }else{
      System.out.println("No esta present");
    }

    //Intentar afegir un element ja existent. Que ha passat? Que no s'ha repetit el valor "Italia"
    tree.add("Italia");
    System.out.println(tree);

    //Eliminar una cadena de text del TreeSet.
    tree.remove("leon");

    //Mostrar els elements del TreeSet després d'eliminar una cadena de text.
    System.out.println(tree);
  }
}
