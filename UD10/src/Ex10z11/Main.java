package Ex10z11;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    Random rand = new Random();
    int[] numeros = new int[1000];

    for(int i = 0; i < numeros.length; i++){
      numeros[i] = rand.nextInt(1 * 1000);
    }

    try(
      BufferedWriter writerPar = new BufferedWriter(new FileWriter("C:\\Users\\Sam\\Documents\\exemple.txt")); 
      BufferedWriter writerNoPar = new BufferedWriter(new FileWriter("C:\\Users\\Sam\\Documents\\exemple1.txt"))
    ) 
    {
      for(int i = 0; i < numeros.length; i++){
        int numero = numeros[i];
        if (numero % 2 == 0) {
          writerPar.write(String.valueOf(numero));
          writerPar.newLine();
          System.out.println("Par");
        }else{
          writerNoPar.write(String.valueOf(numero));
          writerNoPar.newLine();
          System.out.println("impar");
        }
      } 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
