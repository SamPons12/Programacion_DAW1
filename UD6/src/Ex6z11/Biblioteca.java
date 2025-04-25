package Ex6z11;

import java.util.Scanner;

public class Biblioteca {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ArmariLlibres armari1 = new ArmariLlibres("novel·la");
        ArmariLlibres armari2 = new ArmariLlibres("poesia");
        ArmariLlibres armari3 = new ArmariLlibres("teatre");

        boolean menu = true;
        int opcioUsuari = 0;

        while(menu == true){
            System.out.println("1. Afegir llibre");
            System.out.println("2. Eliminar llibres");
            System.out.println("3. Mostrar llibres");
            System.out.println("4. Sortir");
            opcioUsuari = sc.nextInt();
            sc.nextLine();

            switch (opcioUsuari) {
                case 1:
                    System.out.println("A quin armari vols afegir el llibre? (novel·la, poesia, teatre)");
                    String genere = sc.nextLine();
                    genere.toLowerCase();
                    System.out.println("Introdueix el títol del llibre");
                    String titol = sc.nextLine();
                    System.out.println("Introdueix l'autor del llibre");
                    String autor = sc.nextLine();
                    System.out.println("Introdueix l'editorial del llibre");
                    String editorial = sc.nextLine();
                    Llibre llibre = new Llibre(titol, autor, editorial);
                        switch (genere) {
                            case "novel·la":
                                armari1.afegirLlibres(llibre);
                                break;
                            case "poesia":
                                armari2.afegirLlibres(llibre);
                                break;
                            case "teatre":
                                armari3.afegirLlibres(llibre);
                                break;
                            default:
                                System.out.println("No existeix aquest armari");
                                break;
                    }
                    
                    break;
                case 2:
                    int contador = 0;
                    System.out.println("Introdueix el titol del llibre que vols eliminar");
                    String titolEliminar = sc.nextLine();
                    if(armari1.eliminarLlibres(titolEliminar) == false){   
                        contador++;
                    }else {
                        System.out.println("Llibre eliminat correctament");
                    }
                    if(armari2.eliminarLlibres(titolEliminar) == false){
                        contador++;
                    }else {
                        System.out.println("Llibre eliminat correctament");
                    }
                    if(armari3.eliminarLlibres(titolEliminar) == false){
                        contador++;
                    }else {
                        System.out.println("Llibre eliminat correctament");
                    }
                    if (contador == 3){
                        System.out.println("No s'ha trobat cap llibre amb aquest títol");
                    }

                    break;
                case 3:
                    System.out.println("1. Mostrar tots els llibres");
                    System.out.println("2. Mostrar llibres per armari");
                    int opcioMostrar = sc.nextInt();
                    sc.nextLine();
                    switch (opcioMostrar) {
                        case 1:
                            System.out.println("----------Novel·la----------");
                            armari1.mostrarLlibres();
                            System.out.println("----------Poesia----------:");
                            armari2.mostrarLlibres();
                            System.out.println("----------Teatre----------");
                            armari3.mostrarLlibres();
                            break;
                        case 2:

                        System.out.println("Quin armari vols mostrar? (novel·la, poesia, teatre)");
                        String armari = sc.nextLine();
                        armari.toLowerCase();
                        switch (armari) {
                            case "novel·la":
                                armari1.mostrarLlibres();
                                break;
                            case "poesia":
                                armari2.mostrarLlibres();
                                break;
                            case "teatre":
                                armari3.mostrarLlibres();
                                break;
                            default:
                                System.out.println("No existeix aquest armari");
                                break;
                        }
                            break;
                        default:
                            System.out.println("Opció incorrecta");
                            break;
                        }   
                case 4:
                    menu = false;
                    break;
                default:
                    System.out.println("Opció incorrecta");
                    break;
            }
        }
    }
}




