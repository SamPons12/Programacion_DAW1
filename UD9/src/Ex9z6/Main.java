package Ex9z6;

import java.util.LinkedList;

public class Main {
      public static void main(String[] args) {
          // 1. Crear una LinkedList anomenada llistaCiutats
          LinkedList<String> llistaCiutats = new LinkedList<>();
  
          // 2. Afegir ciutats a la llista
          llistaCiutats.add("Barcelona");
          llistaCiutats.add("Madrid");
          llistaCiutats.add("València");
          llistaCiutats.add("Sevilla");
  
          // 3. Mostrar la llista original de ciutats
          System.out.println("Llista original de ciutats: " + llistaCiutats);
  
          // 4. Afegir Bilbao al principi
          llistaCiutats.addFirst("Bilbao");
  
          // 5. Afegir Màlaga al final
          llistaCiutats.addLast("Màlaga");
  
          // 6. Mostrar el primer i l'últim element de la llista
          System.out.println("Primer element: " + llistaCiutats.getFirst());
          System.out.println("Últim element: " + llistaCiutats.getLast());
  
          // 7. Eliminar la primera ciutat
          llistaCiutats.removeFirst();
  
          // 8. Eliminar l'última ciutat
          llistaCiutats.removeLast();
  
          // 9. Mostrar la llista després de les modificacions
          System.out.println("Llista després de les modificacions: " + llistaCiutats);
      }
  }
