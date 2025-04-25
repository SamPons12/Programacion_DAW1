package Project6;

import java.util.Scanner;

public class GestionZoo {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Zonas mamiferos = new Zonas("mamiferos");
        Zonas aves = new Zonas("aves");
        Zonas peces = new Zonas("peces");
        Zonas reptiles = new Zonas("reptiles");
        boolean menu = false;
        
        while(menu == false){
            System.out.println("**********Sistema Gestión Zoo**********");
            System.out.println("1. Añadir animal");
            System.out.println("2. Eliminar animal");
            System.out.println("3. Visualizar animales por zonas");
            System.out.println("4. Salir");
            System.out.println("**********Sistema Gestión Zoo**********");
            int opcioUsuari = sc.nextInt();
            sc.nextLine();

            switch (opcioUsuari) {
                case 1:
                    System.out.println("Introduce nombre del animal:");
                    String nombre = sc.nextLine();
                    System.out.println("Introduce tipo de animal:");
                    String tipoAnimal = sc.nextLine();
                    System.out.println("Introduce la clase de animal(mamifero, ave, pez o reptil):");
                    String claseAnimal = sc.nextLine();
                    claseAnimal.toLowerCase();
                    System.out.println("Introduce la edad:");
                    int edat = sc.nextInt();
                    System.out.println("Introduce la altura del animal: ");
                    double altura = sc.nextDouble();


                    switch (claseAnimal) {
                        case "mamifero":
                            mamiferos.añadirAnimal(new Animal(edat, altura, tipoAnimal, nombre, claseAnimal));
                            break;
                        case "ave":
                            aves.añadirAnimal(new Animal(edat, altura, tipoAnimal, nombre, claseAnimal));
                            break;
                        case "pez":
                            peces.añadirAnimal(new Animal(edat, altura, tipoAnimal, nombre, claseAnimal));
                            break;
                        case "reptil":
                            reptiles.añadirAnimal(new Animal(edat, altura, tipoAnimal, nombre, claseAnimal));
                            break;
                        default:
                            System.out.println("!!!!!!!!!!!CLASE ANIMAL INCORRECTO!!!!!!!!!!!");
                            break;
                    }
                    
                    break;
                case 2:
                    int contador = 0;
                    System.out.println("Introduce el nombre del animal: ");
                    String nombreAnimal = sc.nextLine();

                    if(mamiferos.eliminarAnimal(nombreAnimal) == false){   
                        contador++;
                    }else {
                        System.out.println("Animal eliminado correctamente");
                    }
                    if(aves.eliminarAnimal(nombreAnimal) == false){
                        contador++;
                    }else {
                        System.out.println("Animal eliminado correctamente");
                    }
                    if(reptiles.eliminarAnimal(nombreAnimal) == false){
                        contador++;
                    }else {
                        System.out.println("Animal eliminado correctamente");
                    }
                    if (peces.eliminarAnimal(nombreAnimal) == false) {
                        contador++;
                    }else{
                        System.out.println("Animal eliminado correctamente");
                    }
                    if (contador == 4){
                        System.out.println("!!!!!!!!!!!!!NO SE HA ENCONTRADO NINGUN ANIMAL!!!!!!!!!!!!!");
                    }

                    break;
                case 3:
                    System.out.println("Introduce la zona que quieres ver(mamiferos, aves, peces, reptiles):");
                    String zona = sc.nextLine();

                    switch (zona) {
                        case "mamiferos":
                            mamiferos.enseñarAnimales();
                            break;
                        case "aves":
                            aves.enseñarAnimales();
                            break;
                        case "peces":
                            peces.enseñarAnimales();
                            break;
                        case "reptiles":
                            reptiles.enseñarAnimales();
                            break;
                        default:
                            System.out.println("!!!!!!!!!!!ZONA INCORRECTA!!!!!!!!!!!");
                            break;
                    }
                    break;
                case 4:
                    menu = true;
                    break;
                default:
                    System.out.println("!!!!!!!!!!!VALOR INCORRECTO!!!!!!!!!!!");
                    break;
        }
    }
}
}
