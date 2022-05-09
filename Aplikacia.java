/**
 * Write a description of class Aplikacia here.
 * 
 * @author (Tomáš Staroň) 
 * @version (a version number or a date)
 */
import java.util.Scanner;  // slúži na input používateľa
public class Aplikacia {
    private static Hra theGame;
    
    
    // hlavná metóda, využíva sa na spustenie hry
    public static void main (String[] args) {
        int volba = 999;
        Scanner input = new Scanner(System.in);
        System.out.println("*****   MENU   *****");
        System.out.println("*****   Pre štart hry zvoľ číslo 1   *****");
        System.out.println("*****   Pre ukončenie hry zvoľ číslo 0   *****");
        volba = input.nextInt();
        while (volba != 0) {
            if (volba == 1) {
                theGame = new Hra();
                theGame.hraj();
            }
            System.out.println("Ak chceš začať novú hru zadaj '1'.... Ak chceš hru ukončiť zadaj číslo '0'.");
            volba = input.nextInt();
        }
        System.out.println("HRA BOLA UKONČENÁ!");
    }
}
