
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Tirada implements Serializable {
    private ArrayList<Character> combinacio = new ArrayList<>();
    private int[] resposta = new int[2];
    private int puntuacio;

    public Tirada(){
    }

    public void setCombinacio(String c){
        for(int i = 0; i < 4; i++){
            combinacio.add(c.charAt(i));
        }
    }

    public ArrayList<Character> getCombinacio(){
        return combinacio;
    }

    public void setResposta(int[] com){
        resposta[0] = com[0];
        resposta[1] = com[1];
    }

    public int[] getResposta(){
        return resposta;
    }

    public void setPuntuacio(int r){
        puntuacio = r*2;
    }

    public int getPuntuacio(){
        return puntuacio;
    }

    @Override
    public String toString(){
        return "*******TIRADA********+"+ "\n"+
                "Resultat: " + Arrays.toString(resposta);
    }

     
}
