package Ex6z11;

public class Llibre {
    private String titolLlibre;
    private String autorLlibre;
    private String editorialLlibre;

    public Llibre(){

    }

    public Llibre (String titolLlibre, String autorLlibre, String editorialLlibre){ 
        this.titolLlibre = titolLlibre;
        this.autorLlibre = autorLlibre;
        this.editorialLlibre = editorialLlibre;
    }

    public void setTitolLlibre(String titolLlibre){
        this.titolLlibre = titolLlibre;
    }

    public void setAutorLlibre(String autorLlibre){
        this.autorLlibre = autorLlibre;
    }

    public void setEditorialLlibre(String editorialLlibre){
        this.editorialLlibre = editorialLlibre;
    }

    public String getTitolLlibre(){
        return titolLlibre;
    }

    public String getAutorLlibre(){
        return autorLlibre;
    }

    public String getEditorialLlibre(){
        return editorialLlibre;
    }


    @Override
    public String toString(){
        return "Llibre: " + titolLlibre + " de " + autorLlibre + " de l'editorial " + editorialLlibre;
    }
}
