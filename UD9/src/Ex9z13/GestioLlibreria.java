package Ex9z13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestioLlibreria {
  static ArrayList<Llibre> llibres = new ArrayList<>();
  static HashSet<String> clients = new HashSet<>();
  static HashMap<String, Integer> inventari = new HashMap<>();
  static boolean menu;
  static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    // Afegir llibres
    llibres.add(new Llibre("El quijote", "Bandai", "Miguel de Cervantes"));
    llibres.add(new Llibre("Guardian entre el centeno", "Planeta", "J. D. Salinger"));
    llibres.add(new Llibre("Wu Wei", "Obelisco", "Henri Borel"));
    llibres.add(new Llibre("El extranjero", "Random House", "Albert Camus"));
    // Afegir usuaris
    clients.add("Sam");
    clients.add("Joan");
    // Afegir inventari
    inventari.put("El quijote", 10);
    inventari.put("Guardian entre el centeno",7);
    inventari.put("Wu Wei", 0);
    inventari.put("El extranjero", 2);

    while (!menu) {
      try {
        menu();
      } catch (InputMismatchException e) {
        System.out.println("INTRODUEIX UN NOMBRE!!!!!");
        sc.nextLine();
      }
    }
  }

  public static void menu() throws InputMismatchException{
    System.out.println("1. Registrar");
    System.out.println("2. Realitzar compra");
    System.out.println("3. Inventari");
    System.out.println("4. Sortir");
    int opcioUsuari = sc.nextInt();;
    sc.nextLine();

    switch (opcioUsuari) {
      case 1:
        registrar();
        break;
      case 2:
        relitzarCompra();
        break;
      case 3:
        inventari();
        break;
      case 4:      
        menu = true;
        break;
      default:
        System.out.println("INTRODUEIX UN NOMBRE ENTRE 1-4!!!!");
        break;
    }
  }

  public static void test(){
    llibres.get(2).getNom()
  }

  public static void registrar(){
    System.out.println("Introduiex el nom del usuari: ");
    String nomUsuari = sc.nextLine();

    if (clients.contains(nomUsuari)) {
      System.out.println("JA EXISTEIX AQUEST USUARI!!!!");
      return;
    }else{
      clients.add(nomUsuari);
      System.out.println("REGISTRADO CORRECTAMENTE");
    }
  }

  public static void relitzarCompra(){
    System.out.println("Usuari: ");
    String nomUsuari = sc.nextLine();

    if (iniciarSessio(nomUsuari) == true) {
      System.out.println("Titol del llibre que vols comprar: ");
      String titolLliibre = sc.nextLine();

      if (inventari.containsKey(titolLliibre)) {
        int stock = inventari.get(titolLliibre) -1;
        inventari.replace(titolLliibre, stock);
      }else{
        System.out.println("NOM INCORRECTE O NO HI HA STOCK!!!!!!");
      }

    }else{
      System.out.println("¿Registrar? (Si/No)");
      String opcioRegistrar = sc.nextLine();
      opcioRegistrar.toLowerCase();

      switch (opcioRegistrar) {
        case "si":
          registrar();
          break;
        case "no":
          return;
        default:
          System.out.println("VALOR INCORRECTE, INTRODUEIX SI O NO!!!!!!!");
          break;
      }
    }
  }

  public static boolean iniciarSessio(String nomUsuari){
    if (clients.contains(nomUsuari)) {
      return true;
    }else{
      return false;
    }
  }

  public static void inventari(){
    System.out.println(inventari);
  }
}
