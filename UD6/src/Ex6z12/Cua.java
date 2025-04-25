package Ex6z12;

public class Cua {
    private Persona[] cua = new Persona[10];
    private int nPersones;
    private String name;

    public Cua(String name) {
        this.name = name;
    }

    public void afegirPersona(Persona persona){
        if (nPersones < 10){
            cua[nPersones++] = persona;
        }else{
            System.out.println("La cua està plena");
        }
    }

    public void eliminarPersona(String nomPersona){
        for(int i = 0; i < nPersones; i++){
            if(cua[i].getNom().equals(nomPersona)){
                cua[i] = null;
                for(int j = i; j < nPersones - 1; j++){
                    cua[j] = cua[j+1];
                }
                nPersones--;
                break;
            }
        }
    }

    @Override
    public String toString(){
        String s = "Cua " + name + ":\n";
        for(int i = 0; i < nPersones; i++){
            s += cua[i].getNom() + "\n";
        }
        return s;
    }
}