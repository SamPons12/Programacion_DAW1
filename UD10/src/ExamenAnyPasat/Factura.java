package ExamenAnyPasat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
  private String data;
  private String total;
  private ArrayList<LinieaDeFactura> linies = new ArrayList<>();

  public Factura(String data, String total, ArrayList<LinieaDeFactura> linies){
    this.data = data;
    this.total = total;
    this.linies.addAll(linies);
  }

  @Override
  public String toString(){
    String format = "Data: " + data + "\n" + "Total: " + total + "€" + "\n";

    for (int i = 0; i < linies.size(); i++){
      
      format += linies.get(i);
    }
    return format;
  }

}
