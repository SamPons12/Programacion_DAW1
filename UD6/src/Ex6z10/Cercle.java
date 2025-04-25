package Ex6z10;

public class Cercle extends FiguraGeometrica {

    private float radi;

    public Cercle(float posicioX, float posicioY, float radi) {
        super(posicioX, posicioY);
        this.radi = radi;
    }

    @Override
    public float calculArea() {
        return (float) 3.14 * radi * radi;
    }

    @Override
    public float calculPerimetre() {
        return (float) (2 * 3.14 * radi);
    }

}
