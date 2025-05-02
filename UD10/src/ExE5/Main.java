package ExE5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    HashMap<String, Double> sumaTemperatures  = new HashMap<>();
    HashMap<String, Integer> contadorMesos = new HashMap<>();

    try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Sam\\Downloads\\UD10-temperatures.txt"))){
      System.out.println(LocalDate.now());
      String liniea = br.readLine();
      String[] mesos = liniea.split(",");

      for (String string : mesos) {
        sumaTemperatures.put(string, 0.0);
        contadorMesos.put(string, 0);
      }

      while ((liniea = br.readLine()) != null) {
        String[] temperatures = liniea.split(",");

        for(int i = 0; i < temperatures.length; i++){
          double temperatura = Double.parseDouble(temperatures[i]);
          String mes = mesos[i];

          sumaTemperatures.put(mes, sumaTemperatures.get(mes) + temperatura);
          contadorMesos.put(mes, contadorMesos.get(mes) + 1);
        }
      }

      HashMap<String, Double> mitjanes = new HashMap<>();
        
      for(int i = 0; i < sumaTemperatures.size(); i++){
        mitjanes.put(mesos[i], sumaTemperatures.get(mesos[i]) / contadorMesos.get(mesos[i]));
      }

      mitjanes.forEach((k, v) -> {System.out.println(k + " Temperatura: " + v);});
        

    }catch(IOException e){

    }
  }
}
