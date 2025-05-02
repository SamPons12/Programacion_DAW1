package ExamenAnyPasat;

import java.io.Serializable;

public class LinieaDeFactura implements Serializable{
  private String descripcio;
  private double quantitat;
  private double preuUnitari;

  public LinieaDeFactura(String descripcio, double quantitat, double preuUnitari){
    this.descripcio = descripcio;
    this.quantitat = quantitat;
    this.preuUnitari = preuUnitari;
  }

  public String toString(){
    return "--" + descripcio + "   " + quantitat + "   " + preuUnitari + "\n";
  }
}
