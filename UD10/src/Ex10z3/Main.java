package Ex10z3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
  public static void main(String[] args) {
    File origen = new File("C:\\\\Users\\\\Sam\\\\Documents\\\\ESTRUCTURA_BASICA_HTML.txt");
    File desti = new File("C:\\Users\\Sam\\Documents\\ESTRUCTURA_BASICA_HTML_copia.txt");
 
    InputStream in = null;
    OutputStream out = null;
    
    try {
      in = new FileInputStream(origen);
      out = new FileOutputStream(desti);
    } catch (IOException e) {
      e.printStackTrace();
    }
    

    byte[] dades = new byte[1024];
    int llegits = 0;
    int totalBytesLlegits = 0;

    try {
      while (-1 != (llegits = in.read(dades))) {
        out.write(dades, 0 ,llegits);
        totalBytesLlegits += llegits;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    System.out.println("Mida del fitxer original: " + origen.length());
    System.out.println("Mida del fitxer còpia: " + desti.length());
    System.out.println("Total de bytes copiats: " + totalBytesLlegits);
    
  }
}
