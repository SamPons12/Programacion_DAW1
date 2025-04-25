package Ex10z6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Main {
  public static void main(String[] args) {
    String fitxerOrigen = "C:\\Users\\Sam\\Documents\\ESTRUCTURA_BASICA_HTML.txt";
    String fitxerDesti = "C:\\Users\\Sam\\Documents\\ESTRUCTURA_BASICA_HTML_comprimit.gz";

    try {
      FileInputStream fitxerInput = new FileInputStream(fitxerOrigen);
      FileOutputStream fitxerOutput = new FileOutputStream(fitxerDesti);
      GZIPOutputStream gzipOutput = new GZIPOutputStream(fitxerOutput);

      byte[] buffer = new byte[4096];
      int bytesLlegits;

      while ((bytesLlegits = fitxerInput.read(buffer)) != -1) {
        gzipOutput.write(buffer, 0, bytesLlegits);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
