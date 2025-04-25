
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Partida implements Serializable {
    private String nom;
    private ArrayList<Character> combinacioOculta = new ArrayList<>();
    private ArrayList<Tirada> tirades = new ArrayList<>();
    private int puntuacio;
    private boolean estat;
 
    public Partida (){
        generarCombinacio();
    }

    public void generarCombinacio(){
        Random randomNumbers = new Random();
        ArrayList<Character> posiblesLletres = new ArrayList<>(Arrays.asList('R', 'G', 'B', 'M', 'Y', 'C'));

        for(int i = 0; i < 4; i++){
            combinacioOculta.add(posiblesLletres.get(randomNumbers.nextInt(posiblesLletres.size())));
        }
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return nom;
    }

    public int[] comprovar(Tirada t){
        int[] resultat = new int[2];
        boolean[] comprovar = new boolean[4];
        int correctes = 0;
        int incorrectes = 0;

        for(int i = 0; i < combinacioOculta.size(); i++){
            if (t.getCombinacio().get(i) == combinacioOculta.get(i)) {
                correctes++;
                comprovar[i] = true;
            }
            resultat[0] = correctes;
        }

        for(int i = 0; i < combinacioOculta.size(); i++){
            if (!comprovar[i]) {
                for(int j = 0; j < combinacioOculta.size(); j++){   
                    if (!comprovar[j] && combinacioOculta.get(j) == t.getCombinacio().get(i)) {
                        incorrectes++;
                }
            } 
            }
            resultat[1] = incorrectes;
        }
        return resultat;
    }

    public void setPuntuacio(int puntuacio){
        this.puntuacio = puntuacio;
    }

    public int getPuntuacio(){
        return puntuacio;
    }

    public String getCombinacioOculta(){
        return combinacioOculta.toString();
    }

    public void setTirada(Tirada t){
        tirades.add(t);
    }
   
    public void setEstat(boolean r){
        estat = r;
    }

    public boolean getGuanyar(){
        return estat;
    }

    public String mostrarDatos(){
        return "*********PARTIDA*********" + "\n" + 
        "Jugador: " + nom + "\n" +
        "Puntuacio: " + puntuacio;
    }

    @Override
    public String toString(){
        String s1 =  "*********PARTIDA*********" + "\n" + 
                "Jugador: " + nom + "\n" +
                "Estat: " + estat + "\n" + 
                "Puntuacio: " + puntuacio + "\n";
        String s2 = "";
        for(int i = 0; i < tirades.size(); i++){
            s2 += "************TIRADA " + i + "***********" + "\n" + 
            "Combinació: " + tirades.get(i).getCombinacio() + "\n" + 
            "Puntuacio: " + tirades.get(i).getPuntuacio() + "\n";

        }
        return s1 + s2;
    }
}
