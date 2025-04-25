package Ex10z9;

import java.io.*;

public class Main {
  public static void main(String[] args) {
    String nomFitxer = "C:\\Users\\Sam\\Documents\\exemple.txt";

    // ✅ Escribimos y cerramos correctamente el archivo
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer))) {
      writer.write("asdasdasdasdasdasdasdasdsada\n");
      writer.write("kssdfsjdfjdsfjsdfjsdfjdsjfsjdfjdsf");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // ✅ Ahora sí, el archivo ya está correctamente escrito y cerrado
    try (RandomAccessFile fitxer = new RandomAccessFile(nomFitxer, "r")) {
      int posicio = 0;
      long longitud = fitxer.length();
      System.out.println("Longitud del fitxer: " + longitud);

      if (posicio < longitud) {
        fitxer.seek(posicio); 
        char caracter = (char) fitxer.read();
        System.out.println("El caràcter a la posició " + posicio + " és: " + caracter);
      } else {
        System.out.println("La posició indicada supera la longitud del fitxer.");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
