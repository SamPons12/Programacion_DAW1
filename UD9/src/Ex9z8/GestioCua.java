package Ex9z8;

import java.util.Scanner;

public class GestioCua {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Cua cine = new Cua("Cine");
        Cua teatre = new Cua("Teatre");
        boolean menu = false;
        
        while(menu == false){
            int opcio = 0;
            System.out.println("1. Afegir persona a la cua del cine");
            System.out.println("2. Afegir persona a la cua del teatre");
            System.out.println("3. Eliminar persona de la cua del cine");
            System.out.println("4. Eliminar persona de la cua del teatre");
            System.out.println("5. Mostrar cua del cine");
            System.out.println("6. Mostrar cua del teatre");
            System.out.println("7. Sortir");
            opcio = sc.nextInt();
            sc.nextLine();
            switch(opcio){
                case 1:
                    System.out.println("Introdueix el nom de la persona que vols afegir a la cua del cine:");
                    String nomCine = sc.nextLine();
                    System.out.println("Introdueix l'edat de la persona que vols afegir a la cua del cine:");
                    int edatCine = sc.nextInt();
                    cine.afegirPersona(new Persona(nomCine, edatCine));
                    break;
                case 2:
                    System.out.println("Introdueix el nom de la persona que vols afegir a la cua del teatre:");
                    String nomTeatre = sc.nextLine();
                    System.out.println("Introdueix l'edat de la persona que vols afegir a la cua del teatre:");
                    int edatTeatre = sc.nextInt();
                    teatre.afegirPersona(new Persona(nomTeatre, edatTeatre));
                    break;
                case 3:
                    System.out.println("Introdueix el nom de la persona que vols eliminar de la cua del cine:");
                    String nomCineEliminar = sc.nextLine();
                    cine.eliminarPersona(nomCineEliminar);
                    break;
                case 4:
                    System.out.println("Introdueix el nom de la persona que vols eliminar de la cua del teatre:");
                    String nomTeatreEliminar = sc.nextLine();
                    teatre.eliminarPersona(nomTeatreEliminar);
                    break;
                case 5:
                    System.out.println(cine.toString());
                    break;
                case 6:
                    System.out.println(teatre.toString());
                    break;
                case 7:
                    menu = true;
                    break;
                default:
                    System.out.println("Opció no vàlida");
                    break;
            }
        }
    }

}
