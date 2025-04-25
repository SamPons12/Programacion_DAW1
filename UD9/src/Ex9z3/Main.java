package Ex9z3;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ArrayList<Integer> numeros = new ArrayList<Integer>();
    numeros.add(10);
    numeros.add(20);
    numeros.add(30);
    numeros.add(40);
    numeros.add(50);

    System.out.println("++++++++3. Imprimir la llista original.++++++++++");
    for(int i = 0; i < numeros.size();i++){
      System.out.println(numeros.get(i));
    }

    numeros.add(60);

    System.out.println("++++++++5. Imprimir la llista després d'afegir el número 60.++++++++++");
    for(int i = 0; i < numeros.size();i++){
      System.out.println(numeros.get(i));
    }

    for(int i = 0; i < numeros.size();i++){
      if (numeros.get(i) == 30) {
        numeros.remove(i);
      }
    }

    System.out.println("++++++++7. Imprimir la llista després d'eliminar el número 30.++++++++++");
    for(int i = 0; i < numeros.size();i++){
      System.out.println(numeros.get(i));
    }

    System.out.println("++++++++8. Accedir al primer element de l'ArrayList i mostrar-lo per pantalla.++++++++++");
    System.out.println(numeros.getFirst());

    numeros.set(1, 25);

    System.out.println("++++++++10. Imprimir la llista després de modificar el segon element.++++++++++");
    for(int i = 0; i < numeros.size();i++){
      System.out.println(numeros.get(i));
    }
    System.out.println("+++++++++11. Mostrar per pantalla la quantitat d'elements que té l'ArrayList.++++++++++");
    System.out.println(numeros.size());

    for(int i = 0; i < numeros.size(); i++){
      System.out.println(i + numeros.get(i));
      if (numeros.get(i) == 40) {
        System.out.println("Si esta present el numero 40");
      }
    }

    numeros.clear();

    if (numeros.isEmpty()) {
      System.out.println("L'ArrayList està buit.");
  } else {
      System.out.println("L'ArrayList NO està buit.");
  }
  }
}
