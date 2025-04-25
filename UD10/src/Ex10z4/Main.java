package Ex10z4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    String fitxerOrigen = "C:\\Users\\Sam\\Documents\\ESTRUCTURA_BASICA_HTML.txt";
    String fitxerDesti = "C:\\Users\\Sam\\Documents\\ESTRUCTURA_BASICA_HTML_copia.txt";

    try {
      BufferedReader lector = new BufferedReader(new FileReader(fitxerOrigen));
      BufferedWriter escriptor = new BufferedWriter(new FileWriter(fitxerDesti));

      String linia;
      int liniesCopiades = 0;

      while ((linia = lector.readLine()) != null) {
        escriptor.write(linia);
        escriptor.newLine();
        liniesCopiades++;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
