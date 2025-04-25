package Ex6z10;

public class Rombe extends FiguraGeometrica {

    private float costat;
    private float diagonalMajor;
    private float diagonalMenor;

    public Rombe(float posicioX, float posicioY, float costat, float diagonalMajor, float diagonalMenor) {
        super(posicioX, posicioY);
        this.costat = costat;
        this.diagonalMajor = diagonalMajor;
        this.diagonalMenor = diagonalMenor;
    }

    @Override
    public float calculArea() {
        return (diagonalMajor * diagonalMenor) / 2;
    }

    @Override
    public float calculPerimetre() {
        return (4 * costat);
    }

}
