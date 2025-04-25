package Ex10z2;

import java.io.File;
import java.io.FilenameFilter;

public class Main {
  public static void main(String[] args) {
    String rutaDirectori = ("C:\\Users\\Sam\\Downloads");
    File directori = new File(rutaDirectori);

    if (directori.exists() && directori.isDirectory()) {
      String[] contingut = directori.list(new FilenameFilter() {

        @Override
        public boolean accept(File f, String name){
          if (name.endsWith(".jpg") || name.endsWith(".png")) {
            return true;  
          }else{
            return false;
          }

        }
      });
      
      for (String string : contingut) {
        System.out.println(string);
      }
    }
  }
}
