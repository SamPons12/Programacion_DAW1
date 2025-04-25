package Ex10z10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    URL url = null;
    BufferedReader in = null;
    BufferedWriter out = null;
    String liniea;

    try {

      url = new URL("http://www.iesjoanramis.org");
      in = new BufferedReader(new InputStreamReader(url.openStream()));
      out = new BufferedWriter(new FileWriter("C:\\Users\\Sam\\Documents\\ramis.html"));

      while ((liniea = in.readLine()) != null) {
        out.write(liniea);
        System.out.println(liniea);
      }

    } catch(Throwable t){
      t.printStackTrace();
    }finally{
      try {
        in.close();
        out.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    }
  }
}
