package Ex6z10;

public class Rectangle extends FiguraGeometrica {

    private float amplada;
    private float alcada;

    public Rectangle (float posicioX, float posicioY, float amplada, float alcada) {
        super(posicioX, posicioY);
        this.amplada = amplada;
        this.alcada = alcada;
    }

    @Override
    public float calculArea() {
        return (amplada * alcada);
    }

    @Override
    public float calculPerimetre() {
        return (2 * amplada + 2 * alcada);
    }

}
