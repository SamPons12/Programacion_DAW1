package Project6;

public class Zonas {
    private String nombreZona;
    private int index = 0;
    Animal[] zonaAnimals = new Animal[5];
    
    public Zonas(String nombreZona){
        this.nombreZona = nombreZona;
    }

    public void añadirAnimal(Animal animal){
        zonaAnimals[index++] = animal;
    }

    public boolean eliminarAnimal(String nombreAnimal){
        if (index == 0){
            return false;
        }else{
            for(int i = 0; i < index; i++){
                if(zonaAnimals[i].getNombreAnimal().equals(nombreAnimal)){
                    zonaAnimals[i] = null;
                    for(int j = i; j < index; j++){
                        zonaAnimals[j + 1 ] = zonaAnimals[j];
                    }
                    index--;
                    return true;
                }
                break;
            }
            return false;
        }
        
    }

    public void enseñarAnimales(){
        if (index == 0){
            System.out.println("!!!!!!!!!!!NO HAY ANIMALES!!!!!!!!!!!");
        }else{
            for(int i = 0; i < index; i++){
                System.out.println("------------"+ nombreZona + "------------");
                System.out.println(zonaAnimals[i].toString());
            }
        }
       
    }
}
