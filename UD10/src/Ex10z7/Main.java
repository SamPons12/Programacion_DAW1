package Ex10z7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String nomFitxer = "fitxer.txt";

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nomFitxer));
            String linia;
            int numeroLinia = 1;

            while ((linia = lector.readLine()) != null) {
                numeroLinia++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
