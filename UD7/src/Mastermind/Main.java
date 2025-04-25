
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String combinacio = "RBGMYC";
    static ArrayList<Partida> partides = new ArrayList<>();
    
    static File f = new File("C:\\Users\\Sam\\Documents\\partida.dat");
    static FileInputStream fis = null;
    static ObjectInputStream input = null;
    static FileOutputStream fos = null;
    static ObjectOutputStream output = null;
    public static void main(String[] args) {
        try {
            recuperarPartida();
        } catch (ClassNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if (input != null) input.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boolean menu = false;

        while (!menu) {
            int opcioUsuari = 0;
            try {
                System.out.println("1. Jugar");
                System.out.println("2. Mostrar partida");
                System.out.println("3. Sortir");
                opcioUsuari = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un numero enter");
                sc.nextLine();
            }
           
            switch (opcioUsuari) {
                case 1:
                    partida();
                    break;
                case 2:
                    mostrarPartides();
                    break;
                case 3:
                    try {
                        guardarPartida();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally{
                        try {
                            if (output != null) output.close();
                            if (fos != null) fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    menu = true;
                    break;
                default:
                    break;
            }
        }
    }

    private static void partida(){
        Partida p1 = new Partida();
        int intents = 0;
        
        System.out.println("Introdueix el teu nom: ");
        p1.setNom(sc.nextLine());
        limpiarPantalla();

        while (p1.getGuanyar() == false && intents < 16 ) {
            Tirada tirada = new Tirada();
            String combinacioUsuari;
            boolean control = false;
            
            do {
                System.out.println("Introdueix cobinacio de cuatre colors(R, B, G, M, Y, C): ");
                combinacioUsuari = sc.nextLine().toUpperCase();

                if (combinacioUsuari.length() != 4) {
                    System.out.println("Entrada no vàlida. Ha de contenir exactament 4 lletres.");
                    continue; 
                }
    
                control = true;
                for (int i = 0; i < combinacioUsuari.length(); i++) {
                    if (combinacio.indexOf(combinacioUsuari.charAt(i)) == -1) {
                        control = false;
                        break;
                    }
                }
    
                if (control) {
                    break;
                } else {
                    System.out.println("Entrada no vàlida. Només pots utilitzar R, B, G, M, Y, C.");
                }    

            } while (!control);
            
            tirada.setCombinacio(combinacioUsuari);
            tirada.setResposta(p1.comprovar(tirada));
            tirada.setPuntuacio(tirada.getResposta()[0]);
            p1.setPuntuacio(tirada.getPuntuacio() + p1.getPuntuacio());
            p1.setTirada(tirada);
            p1.setEstat(tirada.getResposta()[0] == 4 ? true : false);
            
            System.out.println(p1.mostrarDatos());
            System.out.println(tirada.toString());
            intents++;
        }

        if (p1.getGuanyar() == true) {
            limpiarPantalla();
            System.out.println("-------------HAS GUANYAT-----------------");
        }else{
            limpiarPantalla();
            System.out.println("-------------HAS PERDUT-----------------");
            System.out.println("COMBINACION SECRECTA: " + p1.getCombinacioOculta());
        }  
        partides.add(p1);
    }

    private static void mostrarPartides(){
        limpiarPantalla();
        if (partides.isEmpty()) {
            System.out.println("No hay partidas registradas");
        }
        for(int i = 0; i < partides.size(); i++){
            System.out.println("--------DATOS-------");
            System.out.println(partides.get(i).toString()); 
        }
    }
    
    public static void limpiarPantalla(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    @SuppressWarnings("unchecked")
    public static void recuperarPartida() throws IOException, ClassNotFoundException{
        if (f.exists() && f.length() > 0) {
            fis = new FileInputStream(f);
            input = new ObjectInputStream(fis);
            partides = (ArrayList<Partida>)input.readObject();
        }else{
            f.createNewFile();
        }
    }

    public static void guardarPartida() throws IOException, ClassNotFoundException{
        if (f.exists()) {
            fos = new FileOutputStream(f);
            output = new ObjectOutputStream(fos);
            output.writeObject(partides);
        }
    }
}
