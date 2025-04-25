package estadisiticas;

import java.io.FileReader;
import java.util.HashMap;

public class Estadistica {
  public static void main(String[] args) {
    HashMap<Character, Integer> estCastella = new HashMap<>();
    HashMap<Character, Integer> estAngles = new HashMap<>();

    FileReader inCastella = null;
    FileReader inAngles = null;

    int asciLlegit;
    char lletra;

    try{
       inCastella = new FileReader("C:\\Users\\Sam\\Downloads\\textExempleCastella.txt");
       inAngles = new FileReader("C:\\Users\\Sam\\Downloads\\textExempleAngles.txt");

       while ((asciLlegit = inCastella.read()) != -1) {
        lletra = (char)asciLlegit;
        
        if (estCastella.containsKey(lletra)){
          estCastella.put(lletra, estCastella.get(lletra) + 1);
        }else{
          estCastella.put(lletra, 1);
        }
       }

       while ((asciLlegit = inAngles.read()) != -1) {
        lletra = (char)asciLlegit;
        
        if (estAngles.containsKey(lletra)){
          estAngles.put(lletra, estAngles.get(lletra) + 1);
        }else{
          estAngles.put(lletra, 1);
        }
       } 

       inCastella.close();
       inAngles.close();

       estAngles.forEach((k,v) -> {System.out.print(k + ":" + v);} );
       estCastella.forEach((k,v) -> {System.out.print(k + ":" + v);} );
    }catch(Exception e){
  
    }
   
  } 
}
