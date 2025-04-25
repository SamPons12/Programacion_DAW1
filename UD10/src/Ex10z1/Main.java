package Ex10z1;

import java.io.File;

public class Main {
  public static void main(String[] args) {
    String camiDirectori = "C:\\Users\\Sam\\Documents"; 
    
    File directori = new File(camiDirectori);
        
    if (directori.exists() && directori.isDirectory()) {
      String[] contingut = directori.list();
      for (String string : contingut) {
        System.out.println(string);
      }
    }          
  }
}
