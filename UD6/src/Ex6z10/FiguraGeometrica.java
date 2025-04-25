package Ex6z10;

public abstract class FiguraGeometrica {
    protected float posicioX;
    protected float posicioY;

    public FiguraGeometrica(float posicioX, float posicioY) {
        this.posicioX = posicioX;
        this.posicioY = posicioY;
    }

    public abstract float calculArea();
    public abstract float calculPerimetre();
}



