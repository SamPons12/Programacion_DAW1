package ExamenUD6_2024;

public class Lavadora extends Electrodomestico implements enReparacion {
    private float altura;
    private float precio;

    public Lavadora(String marca, String modelo, int consumo, float altura, float precio ){
        super(marca, modelo, consumo);
        this.altura = altura;
        this.precio = precio;
    }

    public float getPrecio(){
        return precio;
    }

    public String getModelo(){
        return modelo;
    }

    public void reparar(int horas){
        int precio_final = horas*precio_hora;
        System.out.println("Se ha enviado la nevera " + modelo + " a reparar durante " + horas + " percio total: " + precio_final);
    }

    @Override
    public String toString(){
        return ("**********LAVADORA**********" + "\n" + marca + " modelo " + modelo + " consumo " + consumo + " altura " + altura + " precio " + precio);
    }
}
