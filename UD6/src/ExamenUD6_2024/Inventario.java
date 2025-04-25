package ExamenUD6_2024;

import java.util.Scanner;

public class Inventario {
    static Scanner sc = new Scanner(System.in);
    static Electrodomestico[] electrodomesticos = new Electrodomestico[10];
    static int index;
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Introducir nuevo dispositivo");
            System.out.println("2. Mostrar todas las neveras");
            System.out.println("3. Mostrar todas las lavadoras");
            System.out.println("4. Mostrar todos los electrodomesticos");
            System.out.println("5. Mostrar cuántos electrodomésticos hay de cada tipo y el número total de electrodomésticos");
            System.out.println("6. Mostrar el precio sumado de todas las neveras");
            System.out.println("7. Mostrar el precio sumado de todas las lavadoras");
            System.out.println("8. Mostrar el precio sumado de todos los electrodomésticos");
            System.out.println("9. Enviar un electrodoméstico a reparar");
            System.out.println("10. Salir");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    introducirDispositivo();
                    break;
                case 2:
                    mostrarNeveras();
                    break;
                case 3:
                    mostrarLavadoras();
                    break;
                case 4:
                    mostrarTodo();
                    break;
                case 5:
                    contarElectrodomesticos();
                    break;
                case 6:
                    precioNeveras();
                    break;
                case 7:
                    precioLavadora();
                    break;
                case 8:
                    precioTotal();
                    break;
                case 9:
                    enviarReparar();
                    break;
                case 10:
                    salir = true;
                    break;
                default:
                    System.out.println("Introduce un valor correcto.");
                    break;
            }
        }
    }

    static void introducirDispositivo(){
        System.out.println("1. Nevera");
        System.out.println("2. Lavadora");
        int opcion = sc.nextInt();
        sc.nextLine();

        System.out.println("Introduce la marca: ");
        String marca = sc.nextLine();
        System.out.println("Introducir modelo: ");
        String modelo = sc.nextLine();
        System.out.println("Introducir consumo: ");
        int consumo = sc.nextInt();
        sc.nextLine();
        System.out.println("Introducir altura: ");
        float altura = sc.nextFloat();
        sc.nextLine();
        System.out.println("Introducir precio: ");
        float precio = sc.nextFloat();
        sc.nextLine();

        switch (opcion) {
            case 2:
                electrodomesticos[index++] = new Lavadora(marca, modelo, consumo, altura, precio);
                break;
            case 1:
                electrodomesticos[index++] = new Nevera(marca, modelo, consumo, altura, precio);
                break;
            default:
                System.out.println("Introduce un valor correcto");
                break;
        }
    }

    static void mostrarNeveras(){
        if(index == 0){
            System.out.println("No hay ninguna nevera");
        }else{
            for(int i = 0; i < index; i++){
                if(electrodomesticos[i]instanceof Nevera){
                    System.out.println(electrodomesticos[i].toString());
                }
            }
        } 
    }

    static void mostrarLavadoras(){
        if (index == 0) {
            System.out.println("No hay ninguna lavadora");
        }else{
            for(int i = 0; i < index; i++){
                if(electrodomesticos[i]instanceof Lavadora){
                    System.out.println(electrodomesticos[i].toString());
                }
            }
        }  
    }

    static void mostrarTodo(){
        if (index == 0) {
            System.out.println("No hay ninguna lavadora");
        }else{
            for(int i = 0; i < index; i++){
                if(electrodomesticos[i]instanceof Lavadora){
                    System.out.println(electrodomesticos[i].toString());
                }else if (electrodomesticos[i]instanceof Nevera) {
                    System.out.println(electrodomesticos[i].toString());
                }
            }
        }  
    }

    static void contarElectrodomesticos(){
        int neveras = 0;
        int lavadoras = 0;

        if (index == 0) {
            System.out.println("No hay ningun electrodomestico");
        }else{
            for(int i = 0; i < index; i++){
                if(electrodomesticos[i]instanceof Nevera){
                    neveras++;
                }else if (electrodomesticos[i]instanceof Lavadora) {
                    lavadoras++;
                }
            }
        }
        int total = neveras+lavadoras;
        System.out.println("Nevera/s: " + neveras + " Lavadora/s: " + lavadoras + " Total: " + total);
    }

    static void precioNeveras(){
        float precioNevera = 0;
        if (index == 0) {
            System.out.println("No hay nada");
        }else{
            for(int i = 0; i < index; i++){
                if(electrodomesticos[i]instanceof Nevera){
                    precioNevera += ((Nevera)electrodomesticos[i]).getPrecio();
                }
            }
        }
        System.out.println("Precio de todas las neveras: " + precioNevera);
    }
    
    static void precioLavadora(){
        float precioLavadora = 0;
        if (index == 0) {
            System.out.println("No hay nada");
        }else{
            for(int i = 0; i < index; i++){
                if(electrodomesticos[i]instanceof Lavadora){
                    precioLavadora += ((Lavadora)electrodomesticos[i]).getPrecio();
                }
            }
        }
        System.out.println("Precio de todas las neveras: " + precioLavadora);
    }

    static void precioTotal(){
        float precioTotal = 0;
        if (index == 0) {
            System.out.println("No hay nada");
        }else{
            for(int i = 0; i < index; i++){
                if(electrodomesticos[i]instanceof Lavadora){
                    precioTotal += ((Lavadora)electrodomesticos[i]).getPrecio();
                }else if (electrodomesticos[i]instanceof Nevera) {
                    precioTotal += ((Nevera)electrodomesticos[i]).getPrecio();
                }
            }
        }
        System.out.println("Precio total: " + precioTotal);
    }

    static void enviarReparar(){
        if (index == 0) {
            System.out.println("No hay nada");
        }else{
            System.out.println("1. Nevera");
            System.out.println("2. Lavadora");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Introduce el modelo de la  nevera: ");
                    String modeloN = sc.nextLine();
                    System.out.println("Introduce el numero de horas de la reparacion: ");
                    int horasN = sc.nextInt();
                    sc.nextLine();

                    for(int i = 0; i < index; i++){
                        if(electrodomesticos[i]instanceof Nevera && ((Nevera)electrodomesticos[i]).getModelo().equals(modeloN)){
                            ((Nevera)electrodomesticos[i]).reparar(horasN);
                            break;
                        }else{
                            System.out.println("No se ha encontrado la nevera");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Introduce el modelo de la lavadora: ");
                    String modeloL = sc.nextLine();
                    System.out.println("Introduce el numero de horas de la reparacion: ");
                    int horasL = sc.nextInt();
                    sc.nextLine();

                    for(int i = 0; i < index; i++){
                        if(electrodomesticos[i]instanceof Lavadora && ((Lavadora)electrodomesticos[i]).getModelo().equals(modeloL)){
                            ((Lavadora)electrodomesticos[i]).reparar(horasL);
                            break;
                        }else{
                            System.out.println("No se ha encontrado la lavadora");
                        }
                    }
                    break;
                default:
                    System.out.println("Introduce un valor correcto");
                    break;
            }
        }
        
    }

}
