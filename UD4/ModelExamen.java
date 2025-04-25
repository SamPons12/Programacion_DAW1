import java.util.Scanner;

public class ModelExamen {
    static int errors = 0;
    static int opcioUser = 0;
    static String[] usuari = new String[200];
    static String[] contrasenya = new String[200];
    static boolean menu = true;
    static int indexContrasenya = 0;
    static Scanner sc = new Scanner(System.in);
    static int indexUsuari = 0;
    public static void main(String[] args) {
        while (menu) {
            menu();
        }

    }

    public static void menu() {

        System.out.println("1. Introduir nou usuari i contrasenya \n2. Entrar al sistema \n3. Sortir del programa ");
        opcioUser = sc.nextInt();
        sc.nextLine();
        switch (opcioUser) {
            case 1:
                UsuariContrsenya();
                break;
            case 2:
                    EntrarSistema();
                    break;
            case 3:
                        SortirPrograma();
                        break;
                        default:
                            System.out.println("Opcio Invalida");
        }



    }

    public static void UsuariContrsenya() {

        if(opcioUser == 1) {
            opcioUser = 0;
            System.out.println("Introdueix el nom de usuari: ");
            usuari[indexUsuari] = sc.next();
            indexUsuari++;

            System.out.println("Introdueix la contrasenya: ");
            contrasenya[indexContrasenya] = sc.next();
            indexContrasenya++;

        }
    }

    public static void EntrarSistema() {
        String loginUsuari;
        String loginContrasenya;
        boolean macthContrasenya = false;
        boolean matchUsuari = false;

        if(opcioUser == 2) {
            opcioUser = 0;

            System.out.println("Introdueix el nom de usuari: ");
            loginUsuari = sc.next();
            System.out.println("Introdueix la contrasenya: ");
            loginContrasenya = sc.next();

            for(int i = 0; i < indexUsuari; i++){
                if(usuari[i].equals(loginUsuari)) {
                    matchUsuari = true;

                    if (contrasenya[i].equals(loginContrasenya)) {
                        macthContrasenya = true;

                    }
                    break;
                }
                }

            if((matchUsuari == true && macthContrasenya == true && errors > 1)){
                System.out.println("Has entrat al sistema. Hi ha hagut " + errors +  " intents d'entrada incorrectes");
                matchUsuari = false;
                macthContrasenya = false;
                menu();
            }

            if (macthContrasenya == true && matchUsuari == true) {
                System.out.println("Has entrat al sistema. No hi ha hagut intents d'entrada incorrectes");
                matchUsuari = false;
                macthContrasenya = false;
                menu();
            }else if (macthContrasenya == true && matchUsuari == false) {
                errors++;
                System.out.println("No has entrat al sistema. Hi ha hagut " + errors +  " intents d'entrada incorrectes");
                macthContrasenya = false;
            } else if (matchUsuari == true && macthContrasenya == false) {
                matchUsuari = false;
                errors++;
                System.out.println("No has entrat al sistema. Hi ha hagut " + errors +  " intents d'entrada incorrectes");
            }else {
                errors++;
                System.out.println("No has entrat al sistema. Hi ha hagut " + errors +  " intents d'entrada incorrectes");
            }
        }
    }

        public static void SortirPrograma() {
            if(opcioUser == 3) {
                opcioUser = 0;
                for (int i = 0; i < indexUsuari; i++) {
                    System.out.println(usuari[i]);
                    System.out.println(contrasenya[i]);
                }
            }
            menu = false;
        }
    }
