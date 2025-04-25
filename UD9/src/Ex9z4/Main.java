package Ex9z4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  ArrayList<Persona> persones = new ArrayList<>();

  persones.add(new Persona("Anna", "12345678A", 25));
  persones.add(new Persona("Marc", "87654321B", 30));
  persones.add(new Persona("Laia", "11223344C", 22));

  int opcio;
  do {
    System.out.println("\n--- MENÚ ---");
    System.out.println("1. Mostrar persones");
    System.out.println("2. Afegir persona");
    System.out.println("3. Eliminar persona per DNI");
    System.out.println("4. Sortir");
    opcio = scanner.nextInt();
    scanner.nextLine();

    switch (opcio) {
      case 1:
        System.out.println("Llista de persones:");
        for (Persona p : persones) {
          p.mostrarInfo();
        }
        break;

      case 2:
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Edat: ");
        int edat = scanner.nextInt();
        scanner.nextLine();
        persones.add(new Persona(nom, dni, edat));
        break;

      case 3:
        System.out.print("Introdueix el DNI de la persona a eliminar: ");
        String dniAEliminar = scanner.nextLine();
        for(int i = 0; 0 < persones.size(); i++){
            if (persones.get(i).getDni().equals(dniAEliminar)) {
                persones.remove(i);
                System.out.println("Pesona eliminada");
                break;
            }
        }
        break;

      case 4:
        scanner.close();
        break;
      default:
        System.out.println("Opció no vàlida, intenta-ho de nou.");
    }
        } while (opcio != 4);
    }
}
