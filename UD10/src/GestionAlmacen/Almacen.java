package GestionAlmacen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class Almacen {
  public static void main(String[] args) {
    HashSet<Producte> almacen = new HashSet<>();
    
    try {
      BufferedReader br = new BufferedReader(new FileReader(""));

      String liniea;
      String[] dades;
      while ((liniea = br.readLine()) != null) {
        dades = liniea.split(",");

        if (dades.length != 5) {
          continue;
        }

        boolean buit = false;

        for (String dada : dades) {
          if (dada.trim().isEmpty()) {
            buit = true;
            break;
          }
        }

        if (buit) {
          continue;
        }

        try {
          almacen.add(new Producte(Integer.parseInt(dades[0]), dades[1], dades[2], Integer.parseInt(dades[3]), Double.parseDouble(dades[4])));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    for (Producte producte : almacen) {
      System.out.println(producte);
    }
  }
}
