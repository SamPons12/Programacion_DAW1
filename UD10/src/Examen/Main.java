package Examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  static Scanner sc = new Scanner(System.in);
  static HashSet<Participante> participantes = new HashSet<>();
  static TreeMap<String, ArrayList<Participante>> resultados = new TreeMap<>();
  public static void main(String[] args) {
    boolean menu = true;
    
    while(menu){
      System.out.println("1. Processar dades");
      System.out.println("2. Sortir");
      int opcio = sc.nextInt();
      sc.nextLine();

      switch (opcio) {
        case 1:
          procesarDatos();
          break;
        case 2:
          menu = false;
      
        default:
          break;
      }
  
    }
  }

  public static void procesarDatos(){
    BufferedReader br = null;

    try {
      br = new BufferedReader(new FileReader("C:\\Users\\Sam\\Downloads\\participantes.txt"));
      String[] dades;
      String linia;

      while ((linia = br.readLine()) != null) {
        dades = linia.split(";");
        
        if (dades.length != 3) {
          continue;
        }

        boolean buit = false;
        for (String string : dades) {
          if (string.trim().isEmpty()) {
            buit = true;
            break;
          }
        }

        if (buit) {
          continue;
        }

        try {
      
          participantes.add(new Participante(dades[0], Integer.parseInt(dades[1]), dades[2]));
          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    
    } catch (Exception e) {

    }finally{
      try {
        if (br != null) br.close();

      } catch (Exception e) {
        e.printStackTrace();
      }
      for (Participante part : participantes) {
        
        if (!resultados.containsKey(part.getPrueba())) {
          resultados.put(part.getPrueba(), new ArrayList<Participante>());
          resultados.get(part.getPrueba()).add(part);
        }else{
          resultados.get(part.getPrueba()).add(part);
        }
      }

      for (String string : resultados.keySet()) {
      System.out.println(string + ": " + resultados.get(string).size() + " participantes.");

       
      }
  }
}
}
