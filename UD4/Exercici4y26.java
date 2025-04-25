import java.util.Scanner;

public class Exercici4y26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Character[] lletres = new Character[26];
        Character[] numvegades = new Character[26];
        int index = 0;

        System.out.println("Introdueix un text: ");
        String text = sc.nextLine();

        text = text.toLowerCase();
        text.replace(" ", "");

        for(int i = 0; i < text.length(); i++) {
            for(int j = 0; j < index; j++) {
                if (text.charAt(i) == lletres[j]){
                    numvegades[j]++;
                }else{
                    lletres[index] = text.charAt(i);
                    index++;
                }
            }



        }

    }
}
