package Ex9z7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
  public static void main(String[] args) {
        LinkedList<String> colors = new LinkedList<>(Arrays.asList("Vermell", "Blau", "Verd", "Groc"));

        // 1. Afegir al final
        colors.add("Negre");

        // 2. Recorre tots els elements
        System.out.println("Llista de colors: " + colors);

        // 3. Recorre des d'una posició especificada
        ListIterator<String> iterator = colors.listIterator(2);
        while (iterator.hasNext()) System.out.print(iterator.next() + " ");
        System.out.println();

        // 4. Iterar en ordre invers
        System.out.print("Ordre invers: ");
        Iterator<String> reverseIterator = colors.descendingIterator();
        while (reverseIterator.hasNext()) System.out.print(reverseIterator.next() + " ");
        System.out.println();

        // 5. Inserir en una posició específica
        colors.add(2, "Taronja");

        // 6. Inserir al principi i al final
        colors.addFirst("Blanc");
        colors.addLast("Gris");

        // 7. Inserir al capdavant
        colors.offerFirst("Morat");

        // 8. Inserir al final
        colors.offerLast("Rosa");

        // 9. Inserir elements en posicions específiques
        colors.addAll(3, Arrays.asList("Turquesa", "Marró"));

        // 10. Obtenir la primera i última ocurrència
        System.out.println("Primer: " + colors.getFirst() + ", Últim: " + colors.getLast());

        // 11. Mostrar elements i posicions
        for (int i = 0; i < colors.size(); i++) System.out.println(i + ": " + colors.get(i));

        // 12. Eliminar un element especificat
        colors.remove("Verd");

        // 13. Eliminar primer i últim element
        colors.removeFirst();
        colors.removeLast();

        // 14. Eliminar tots els elements
        LinkedList<String> copyColors = new LinkedList<>(colors); // Copia per referència
        colors.clear();

        // 15. Canviar dos elements
        Collections.swap(copyColors, 1, 3);

        // 16. Barrejar els elements
        Collections.shuffle(copyColors);

        // 17. Unir dues LinkedLists
        LinkedList<String> moreColors = new LinkedList<>(Arrays.asList("Daurat", "Platejat"));
        copyColors.addAll(moreColors);

        // 18. Clonar la LinkedList
        LinkedList<String> clonedList = (LinkedList<String>) copyColors.clone();

        // 19. Eliminar i retornar el primer element
        System.out.println("Primer: " + clonedList.pollFirst());

        // 20. Recuperar sense eliminar el primer element
        System.out.println("Primer: " + clonedList.peekFirst());

        // 21. Recuperar sense eliminar l'últim element
        System.out.println("Últim  : " + clonedList.peekLast());

        // 22. Comprovar si existeix un element
        System.out.println(clonedList.contains("Blau"));

        // 23. Convertir en ArrayList
        ArrayList<String> arrayList = new ArrayList<>(clonedList);

        // 24. Comparar dues LinkedLists
        System.out.println(clonedList.equals(copyColors));

        // 25. Comprovar si està buida
        System.out.println(clonedList.isEmpty());

        // 26. Substituir un element
        clonedList.set(2, "Celeste");

        // Resultat final
        System.out.println("Final: " + clonedList);
    }
}
