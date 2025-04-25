import java.util.Scanner;

public class Banc {
    static Scanner sc = new Scanner(System.in);

    static boolean menu = true;
    static CompteBancari[] comptes = new CompteBancari[100];
    static int index = 0;
    public static void main(String[] args) {
        while(menu){
            menu();
        }
    }

    public static void menu(){
        System.out.println("--Bienvenido al programa de gestion bancario--");
        System.out .println("1. Crear nueva cuenta bancaria" + "\n"+
                            "2. Ingresar dinero en una cuenta bancaria" + "\n" +
                            "3. Retirar dinero en una cuenta bancaria" + "\n" +
                            "4. Mostrar saldo de una cuenta bancaria" + "\n" +
                            "5. Salir del programa");
        int opcioClient = sc.nextInt();

        switch (opcioClient){
            case 1:
                crearCompte();
                break;
            case 2:
                ingresarDoblers();
                break;
            case 3:
                retirarDoblers();
                break;
            case 4:
                mostrarSaldo();
                break;
            case 5:
                salirPrograma();
                break;
                default:
                    System.out.println("Introduce un valor valido");

        }
    }

    public static void crearCompte(){
        System.out.println("Ingrese el nombre del cuenta bancaria: ");
        String nom = sc.next();
        sc.nextLine();

        System.out.println("Ingrese el DNI de la cuenta bancaria: ");
        String dni = sc.next();
        sc.nextLine();

        System.out.println("Ingrese el saldo de inico de la cuenta bancaria: ");
        float saldo = sc.nextFloat();
        sc.nextLine();

        comptes[index] = new CompteBancari(dni, nom, saldo);
        index++;
    }

    public static void ingresarDoblers(){
        System.out.println("Ingrese el DNI del propietario de la cuenta bancaria: ");
        String dni = sc.next();

        System.out.println("Cantidad de dinero a ingresar: ");
        float cantidad = sc.nextFloat();
        sc.nextLine();

        for(int i = 0; i < index; i++){
            if(comptes[i].getDniTitular().equals(dni)){
                comptes[i].ingresar(cantidad);
                System.out.println("Se ha ingresado el dinero correctamente");
            }else{
                System.out.println("El DNI es incorrecto");
            }
        }
    }

    public static void retirarDoblers(){
        System.out.println("Ingrese el DNI del propietario de la cuenta bancaria: ");
        String dni = sc.next();

        System.out.println("Cantidad de dinero a retirar: ");
        float cantidad = sc.nextFloat();
        sc.nextLine();

        for(int i = 0; i < index; i++){
            if(comptes[i].getDniTitular().equals(dni)){
                comptes[i].retirar(cantidad);
                System.out.println("Se ha retirado el dinero correctamente");
            }else {
                System.out.println("El DNI es incorrecto");
            }
        }
    }

    public static void mostrarSaldo(){
        System.out.println("Ingrese el DNI del propietario de la cuenta bancaria: ");
        String dni = sc.next();

        for(int i = 0; i < index; i++) {
            if (comptes[i].getDniTitular().equals(dni)) {
                System.out.println(comptes[i].getSaldo());
            }
        }
    }

    public static void salirPrograma(){
        menu = false;

    }
}
