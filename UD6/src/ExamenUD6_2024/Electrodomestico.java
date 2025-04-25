package ExamenUD6_2024;

public abstract class Electrodomestico {
    protected String marca;
    protected String modelo;
    protected int consumo;

    public Electrodomestico(String marca, String modelo, int consumo){
        this.marca = marca;
        this.modelo = modelo;
        this.consumo = consumo;
    }
}
