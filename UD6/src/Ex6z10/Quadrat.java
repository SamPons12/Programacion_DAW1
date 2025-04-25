package Ex6z10;

public class Quadrat extends FiguraGeometrica {

    private float costat;

    public Quadrat(float posicioX, float posicioY, float costat) {
        super(posicioX, posicioY);
        this.costat = costat;
    }

    @Override
    public float calculArea() {
        return (costat * costat);
    }

    @Override
    public float calculPerimetre() {
        return (4 * costat);
    }

}
