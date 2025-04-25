package Ex6z10;

public class Main {
    public static void main(String[] args) {
        FiguraGeometrica[] figures = new FiguraGeometrica[4];
        figures[0] = new Rombe(0, 0, 2, 4, 3);
        figures[1] = new Cercle(0, 0, 3);
        figures[2] = new Rectangle(0, 0, 2, 3);
        figures[3] = new Quadrat(0, 0, 2);

        for (FiguraGeometrica figura : figures) {
            System.out.println("Area: " + figura.calculArea());
            System.out.println("Perimetre: " + figura.calculPerimetre());
            System.out.println();
        }
    }
}
