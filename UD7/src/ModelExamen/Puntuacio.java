public class Puntuacio {
    private String nom;
    private String cognom;
    private int punts;
    private String data;

    public Puntuacio(String nom, String cognom, int punts, String data){
        this.nom = nom;
        this.cognom = cognom;
        this.punts = punts;
        this.data = data;
    }

    public int getPunts(){
        return punts;
    }

    public String getCognom(){
        return cognom;
    }

}
