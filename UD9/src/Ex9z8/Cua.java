package Ex9z8;

import java.util.LinkedList;

public class Cua {
    private LinkedList<Persona> cua = new LinkedList<>();
    private String name;

    public Cua(String name) {
        this.name = name;
    }

    public void afegirPersona(Persona persona){
        cua.add(persona);
    }

    public void eliminarPersona(String nomPersona){
        for(int i = 0; i < cua.size(); i++){
            if(cua.get(i).getNom().equals(nomPersona)){
                cua.remove(i);
                break;
            }
        }
    }

    @Override
    public String toString(){
        String s = "Cua " + name + ":\n";
        for(int i = 0; i < cua.size(); i++){
            s += cua.get(i).getNom() + "\n";
        }
        return s;
    }
}