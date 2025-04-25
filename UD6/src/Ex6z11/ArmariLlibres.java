package Ex6z11;

import java.util.Scanner;

public class ArmariLlibres {
    Scanner sc = new Scanner(System.in);

    private Llibre[] llibres = new Llibre[10];
    private int index = 0;
    private String genere;
    
    public ArmariLlibres(String genere){
        this.genere = genere;
    }

    public void afegirLlibres(Llibre llibre){
        llibres[index++] = llibre;
    }

    public boolean eliminarLlibres(String titolEliminar){
        if (index == 0){
            return false;
        } else {
            for (int i = 0; i < index; i++){
                if (llibres[i].getTitolLlibre().equals(titolEliminar)){
                    llibres[i] = null;
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    public void mostrarLlibres(){
        for (int i = 0; i < index; i++){
            if(index != 0){
                System.out.println(llibres[i]);
            }else{
                System.out.println("No hi ha llibres en aquest armari");
            }
        }
    }
}


