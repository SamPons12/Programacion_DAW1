package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LecturaCsv {
  public static void main(String[] args) {
    ArrayList<Persona> personas = new ArrayList<>();
    String liniea;
    String[] dades =  new String[3];

    try {
      FileReader fr = new FileReader("C:\\Users\\Sam\\Documents\\datos_personas.csv");
      BufferedReader input = new BufferedReader(fr);
    
      while ((liniea = input.readLine()) != null) {
        dades = liniea.split(";");
        String nom = dades[0];
        int edat = Integer.valueOf(dades[1]);
        String dni = dades[2];

        personas.add(new Persona(dni, edat, nom));
      }

      for (Persona p : personas) {
        System.out.println(p);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
