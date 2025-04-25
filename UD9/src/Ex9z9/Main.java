package Ex9z9;

import java.util.HashSet;
import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    HashSet<Integer> nombres = new HashSet<>();

    //Afegir diferents elements al HashSet (per exemple números: 10, 20, 30, 40, 50).
    nombres.add(10);
    nombres.add(20);
    nombres.add(30);
    nombres.add(40);
    nombres.add(50);

    //Mostrar els elements del HashSet utilitzant un iterador (Iterator). Fixeu-vos en l’ordre en que es mostren.
    Iterator<Integer> iterator = nombres.iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }

   //Verificar si el nombre 20 o el 35 estan presents al HashSet.
    if (nombres.contains(20)) {
      System.out.println("Si esta present");
    }else{
      System.out.println("No esta present");
    }

    if (nombres.contains(35)) {
      System.out.println("si esta present");
    }else{
      System.out.println("NO esta present");
    }

    //Eliminar el nombre 40 del HashSet.
    nombres.remove(40);

    //Mostrar els elements del HashSet després d'eliminar l’element.
    for (Integer integer : nombres) {
      System.out.println(integer);
    }

    //Intentar introduït un nombre que ja existeix, per exemple el 50.
    nombres.add(50);

    // Mostrar els elements del HashSet després d'introduir un element existent. Que ha passat? No se ha duplicat, son tots unics.
    for (Integer integer : nombres) {
      System.out.println(integer);
    }

    //Obtenir la mida del HashSet.
    System.out.println(nombres.size());

    //Netejar el HashSet.
    nombres.clear();

    //Verificar si el HashSet està buit.
    if (nombres.isEmpty()) {
      System.out.println("Esta buit");
    }else{
      System.out.println("No esta buit");
    }

  }
}
