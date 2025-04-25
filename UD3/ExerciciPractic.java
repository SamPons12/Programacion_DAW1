import java.util.Scanner;
public class ExerciciPractic {
    public static void main(String[] args) {
        String[][] array =   new String[3][3];
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduïu la informació per al producte 1:" );
        System.out.println("Nom del producte: ");
        array[0][0] = sc.nextLine();
        System.out.println("Quantitat en estoc: ");
        array[0][1] = sc.nextLine();
        System.out.println("Preu per la unitat: ");
        array[0][2] = sc.nextLine();
        System.out.println("Introduïu la informació per al producte 2:" );
        System.out.println("Nom del producte: ");
        array[1][0] = sc.nextLine();
        System.out.println("Quantitat en estoc: ");
        array[1][1] = sc.nextLine();
        System.out.println("Preu per la unitat: ");
        array[1][2] = sc.nextLine();
        System.out.println("Introduïu la informació per al producte 3:" );
        System.out.println("Nom del producte: ");
        array[2][0] = sc.nextLine();
        System.out.println("Quantitat en estoc: ");
        array[2][1] = sc.nextLine();
        System.out.println("Preu per la unitat: ");
        array[2][2] = sc.nextLine();
        int quantitat = Integer.parseInt(array[0][1]);
        int quantitat2 =Integer.parseInt(array[1][1]);
        int quantitat3 =Integer.parseInt(array[2][1]);
        double preu = Double.parseDouble(array[0][2]);
        double preu2 = Double.parseDouble(array[1][2]);
        double preu3 = Double.parseDouble(array[2][2]);
        double Total = quantitat * preu + quantitat2 * preu2 + quantitat3 * preu3;
        System.out.println("Inventari de la botiga:");
        System.out.println("Nom\t\t Quantitat\t\t Preu per unitat" + " " + "Total");
        System.out.println(array[0][0] + "\t\t" + " " + quantitat +"\t\t\t\t"+ "  " + preu + "\t\t\t" + quantitat * preu);
        System.out.println(array[1][0] + "\t" + " " + quantitat2 +"\t\t\t"+ "  " + preu2 + "\t\t\t" + quantitat2 * preu2);
        System.out.println(array[2][0] + " " + " " + quantitat3 +"\t\t\t"+ "  " + preu3 + "\t\t\t" + quantitat3 * preu3 + "\n");
        System.out.println("El valor total de l'inventari és:" + Total);
    }
}
