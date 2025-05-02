package ExamenAnyPasat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);
  static ArrayList<LinieaDeFactura> linies = new ArrayList<>();
  static ArrayList<Factura> facturas = new ArrayList<>();
  public static void main(String[] args) {
    leerCompra();
    boolean menu = true;

    while (menu) {
      System.out.println("1. Procesar Compra");
      System.out.println("2. Visualitzar compra");
      System.out.println("3. Sortir");
      int opcioUsuari = sc.nextInt();

      switch (opcioUsuari) {
        case 1:
          procesarCompra();
          break;
        case 2:
          visualitzarCompra();
          break;
        case 3:
          guardarCompra();
          menu = false;
          break;
        default:
          System.out.println("INTRODUEIX UN NOMBRE VALID");
          break;
      }
    }
  }

  public static void procesarCompra(){
    BufferedReader br = null;
    
    //Read the first purchase
    try {
      br = new BufferedReader(new FileReader("C:\\Users\\Sam\\Downloads\\compra1.csv"));
      //Read the first line of the csv because is the information of the columns
      br.readLine();

      String liniea;
      double preuTotal = 0;

      while ((liniea = br.readLine()) != null) {
        //Save the variable "liniea" into an array string split the position with ";"
        String[] fila = liniea.split(";");

        linies.add(new LinieaDeFactura(fila[0], Double.parseDouble(fila[1]), Double.parseDouble(fila[2])));
        preuTotal += Double.parseDouble(fila[2]);
      }

      facturas.add(new Factura(new SimpleDateFormat("dd-MM-yyyy").format(new Date()), new DecimalFormat("#.00").format(preuTotal), linies));
      linies.clear();

    } catch (Exception e) {
      e.printStackTrace();
    }finally{
      try{
        if (br != null) br.close();
      }catch(IOException e){
        e.printStackTrace();
      }
    }

    //Read the second purchase
    try {
      br = new BufferedReader(new FileReader("C:\\Users\\Sam\\Downloads\\compra2.csv"));

      //Read the first line of the csv because is the information of the columns
      br.readLine();

      String liniea;
      double preuTotal = 0;

      while ((liniea = br.readLine()) != null) {
        //Save the variable "liniea" into an array string split the position with ";"
        String[] fila = liniea.split(";");

        linies.add(new LinieaDeFactura(fila[0], Double.parseDouble(fila[1]), Double.parseDouble(fila[2])));
        preuTotal += Double.parseDouble(fila[2]);
      }

      facturas.add(new Factura(new SimpleDateFormat("dd-MM-yyyy").format(new Date()), new DecimalFormat("#.00").format(preuTotal), linies));
      linies.clear();
      System.out.println("****COMPRA PROCESADA CORRECTAMENT****");

    } catch (Exception e) {
      System.out.println("******ERROR DURANT EL PROCESAMENT");
      e.printStackTrace();
    }finally{
      try {
        if (br != null) br.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
  }

  public static void visualitzarCompra(){
    if (facturas.isEmpty()) {
      System.out.println("NO HI HA FACTURAS REGISTRADES");
    }else{
      for(int i = 0; i < facturas.size(); i++){
        System.out.println("+++++++++FACTURA+++++++++");
        System.out.println(facturas.get(i));
      }
    }
    
  }

  @SuppressWarnings("unchecked")
  public static void leerCompra(){

    ObjectInputStream input = null;
    File f = new File("C:\\Users\\Sam\\Documents\\facturas.dat");

    if (!f.exists() || f.length() == 0) {

      try {
        f.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }else {
      try {

        input = new ObjectInputStream(new FileInputStream(f));
        facturas = (ArrayList<Factura>)input.readObject();
      
      } catch (Exception e) {
        e.printStackTrace();
      }finally{
        try {
          if (input != null) input.close();;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    
  }

  public static void guardarCompra(){
    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Sam\\Documents\\facturas.dat"))){
      if (facturas.isEmpty()) {
        System.out.println("NO HI HA FACTURAS A GUARDAR");
      }else{
        output.writeObject(facturas);
        output.close();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
