package bullscows;

import java.awt.desktop.SystemEventListener;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        String stars = "";
        boolean st;
        int p = 1;
        int l;
        int numberOfSymb;
        System.out.println("Please, enter the secret code's length");
        Scanner scanner = new Scanner(System.in);
        try {
            l = scanner.nextInt();

        } catch(Exception e) {
            System.out.println("Error");
            return;
        }
        try {

            numberOfSymb = scanner.nextInt();
        } catch(Exception e) {
            System.out.println("Error");
            return;
        }

        char[] codeA = new char[l];
        System.out.println("Input the number of possible symbols in the code:");

        if(numberOfSymb < l || numberOfSymb > 36){
            System.out.println("Error, not enough symbols");
            return;
        }
        for(int i = 0; i < l; i++)
            stars += "*";
        if (l > 36 || l < 1) {
            System.out.println("Error of length");
        }
        else {
            String alphabet = alphabet(numberOfSymb);
            for (int k = 0; k < l; k++) {
                do {
                    codeA[k] = alphabet.charAt(random.nextInt(alphabet.length()));
                    st = true;
                    for (int a = k-1; a >= 0; a--) {
                        if (codeA[k] == codeA[a])
                            st = false;

                    }
                } while (st==false);
                }
            if(numberOfSymb < 11)
                System.out.printf("The secret code is prepared: " + stars + "(0-%d)", (numberOfSymb - 1));
            else
                System.out.printf("The secret code is prepared: " + stars + "(0-9, a-%s)", alphabet.charAt(alphabet.length()-1));
            System.out.println("Okay, let's start a game!");
            System.out.println(alphabet);
            do {
                System.out.println("Turn " + p +":");
                String answer = scanner.next();
                char codeC[] = answer.toCharArray();
                Game.start(codeC, codeA);
                p++;
            } while(Game.bulls !=l);
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }
    static String alphabet(int number) {
        String alphabet = "";
        for(int i = 0; i < number; i++) {
            if (i >= 10) {

                alphabet += (char)(i  + 'a' - 10);
            }
            else {
                alphabet += i;
            }
        }
        return alphabet;
    }

}
 class Game {
     public static int cows = 0;
     public static int bulls = 0;
    public static void start(char codeC[], char codeA[]) {
        cows=0;
        bulls=0;
        for (int i = 0; i < codeC.length; i++) {
            if (codeA[i] == codeC[i])
                bulls++;
            else {
                for (int j = 0; j < codeC.length; j++) {
                    if (codeC[j] == codeA[i])
                        cows++;

                }
            }
        }
        if (bulls == 0 && cows == 0)
            System.out.println("Grade: None.");
        else
            System.out.println("Grade: " + bulls + " bulls and " + cows + " cows.");

    }
 }
