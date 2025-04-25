package Ex10z5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    String fitxerOrigen = "C:\\Users\\Sam\\Documents\\ESTRUCTURA_BASICA_HTML.txt";

     StringBuilder contingutEnMemoria = new StringBuilder();

        try{
            BufferedReader lector = new BufferedReader(new FileReader(fitxerOrigen));
            String linia;

            while ((linia = lector.readLine()) != null) {
                contingutEnMemoria.append(linia).append("\n");
            }

            String[] liniesArray = contingutEnMemoria.toString().split("\n");

            System.out.println("Contingut del fitxer (des de la memòria):\n");
            for (String liniaText : liniesArray) {
                System.out.println(liniaText);
            }

        } catch (IOException e) {
          e.printStackTrace();
        }
  }
}
