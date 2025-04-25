package Ex9z11;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);
  static HashMap<String, Integer> contactes = new HashMap<>();
  static boolean menu;
  public static void main(String[] args) {
   
    while (!menu) {
      try {
        menu();
      } catch (InputMismatchException e) {
        System.out.println("Inrtodueix un numero");
        sc.nextLine();
      }
    }
  }

  public static void menu() throws InputMismatchException{
    System.out.println("1. Afegir nou contace");
    System.out.println("2. Cercar contacte");
    System.out.println("3. Mostra tots els contactes");
    System.out.println("4. Sortir");
    int opcioUsuari = sc.nextInt();
    sc.nextLine();

    switch (opcioUsuari) {
      case 1:
        afegirContacte();
        break;
      case 2:
        cercarContacte();
        break;
      case 3:
        mostrarContacte(); 
        break; 
      case 4:
        menu = true;  
      default:
        break;
    }

  }

  public static void afegirContacte(){
    System.out.println("Introdueix un nom: ");
    String nom = sc.nextLine();

    System.out.println("Introudeix un numero de telefon: ");
    Integer telefon = sc.nextInt();
    sc.nextLine();

    int longitud = String.valueOf(telefon).length();
    if (longitud != 8) {
      System.out.println("Telefon incorrecte");
      return;
    }

    contactes.put(nom, telefon);
  }

  public static void cercarContacte(){
    System.out.println("Introdueix nom del contacte: ");
    String nom = sc.nextLine();

   if (contactes.containsKey(nom)) {
      System.out.println("Telefon " + contactes.get(nom)); 
   }else{
    System.out.println("No hi ha cap contacte amb aquest nom");
   }
  }

  public static void mostrarContacte(){
    if (contactes.isEmpty()) {
      System.out.println("No hi ha contactes registrats");
      return;
    }

    System.out.println(contactes);
  }
}
