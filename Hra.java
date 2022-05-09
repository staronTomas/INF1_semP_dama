/**
 * Write a description of class Hra here.
 * 
 * @author (Tomáš Staroň) 
 * @version (a version number or a date)
 */
import java.util.Scanner;  // slúži na input používateľa

public class Hra {
    private static Sachovnica hra;
    private static Hrac hracBiely;
    private static Hrac hracCierny;
    private static Hrac ktoJeNaTahu;
    private static Hrac vitaz = null;    
    
    
    // prázdny konštruktor
    public Hra() {
        
    }
    
    
    
    // zapne hru, riadi priebeh
    public void hraj() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Vitaj v hre Dáma!");
        System.out.print("Zadaj meno bieleho hráča: ");
        String menoBielehoHraca = input.next();
        System.out.println("");
        System.out.print("Zadaj meno bieleho hráča: ");
        String menoCiernehoHraca = input.next();
        System.out.println("");
        System.out.print("Hra Začala!");
        hracBiely = new Hrac(menoBielehoHraca, "biely");
        hracCierny = new Hrac(menoCiernehoHraca, "cierny");
        ktoJeNaTahu = hracBiely;
        hra = new Sachovnica();
        hra = Sachovnica.getSachovnica2();
        hra.zobrazSachovnicu();
        
        
        while (hra.moznostPohybu(ktoJeNaTahu.getFarba()) ) {
            for (int i = 0; i < 30; i++) {
                System.out.print("-");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Počet bodov:  BIELY - " + hracBiely.getPocetBodov() + " .... ČIERNY - " + hracCierny.getPocetBodov());
            System.out.println("Na ťahu je: " + ktoJeNaTahu.getFarba() + " - " + ktoJeNaTahu.getMeno());                      
            System.out.print("Zadaj číslo riadku figúrky, ktorou sa chceš pohnúť: ");
            int riadok = -1 + input.nextInt();
            System.out.println();
            System.out.print("Zadaj číslo stĺpca figúrky, ktorou sa chceš pohnúť: ");
            int stlpec = -1 + input.nextInt();
            System.out.println();
            // kontroluje či sa na šachovnici nachádza takéto políčko
            if ((riadok < 0) || (stlpec < 0) || (riadok >= hra.getPocetPolicok()) || (stlpec >= hra.getPocetPolicok())) {
                System.out.println("Takéto políčko sa na ploche nenachádza!!!");
            } else {
                // kontroluje či sa na políčku nachádza figúrka a či je farby hráča, ktorý je na ťahu
                if (hra.getPolickoFigurka(riadok, stlpec) == null || (ktoJeNaTahu.getFarba() != hra.getFarbaFigurky(riadok, stlpec))) {
                    System.out.println("Na tomto políčku sa nenachádza tvoja figurka");
                } else {
                    System.out.print("Zadaj číslo riadku na ktoré sa chceš pohnúť: ");
                    int newRiadok = -1 + input.nextInt();
                    System.out.println();
                    System.out.print("Zadaj číslo stĺpca na ktoré sa chceš pohnúť: ");
                    int newStlpec = -1 + input.nextInt();
                    System.out.println();
                    // kontroluje či sa na šachovnici nachádza takéto políčko
                    if ((riadok < 0) || (stlpec < 0) || (riadok >= hra.getPocetPolicok()) || (stlpec >= hra.getPocetPolicok())) {
                        System.out.println("Takéto políčko sa na ploche nenachádza");
                    } else {
                        if (kontrolaTahu(ktoJeNaTahu.getFarba(), riadok, stlpec, newRiadok, newStlpec)) {
                            String[] vysledokTahu = hra.pohniFigurkou(riadok, stlpec, newRiadok, newStlpec);
                            if (vysledokTahu[0].equals("tah_vyhod_cierneho")) {
                                hracCierny.minusFigurka();
                            }
                            if (vysledokTahu[0].equals("tah_vyhod_bieleho")) {
                                hracBiely.minusFigurka();
                            }
                            
                            // pridá bod hráčovi ak je figurka na konci pola
                            if (vysledokTahu[1].equals("biely_bod")) {
                                hracBiely.pridajBod();
                            }
                            if (vysledokTahu[1].equals("cierny_bod")) {
                                hracCierny.pridajBod();
                            }
                                                                                    
                            //podmienka vymení hráča ktorý je na tahu
                            if (ktoJeNaTahu.getFarba().equals("biely")) {
                                ktoJeNaTahu = hracCierny;
                            } else {
                                ktoJeNaTahu = hracBiely;
                            }
                            
                        } else {
                            System.out.println("Neplatný ťah! Prosím skús iný!");   
                        }
                    }
                }
            }
        }
        
        
        // pokiaľ už nieje možný pohyb tak sa skontroluje kto vyhral
        for (int i = 0; i < 10; i++) {
            System.out.println("*****************************************************************");
        }
        System.out.println("Hra sa skončila!");
        
        if (hracBiely.getPocetBodov() > hracCierny.getPocetBodov() || hracBiely.getPocetBodov() < hracCierny.getPocetBodov()) {
            if (hracBiely.getPocetBodov() > hracCierny.getPocetBodov()) {
                vitaz = hracBiely;
            }
            
            if (hracBiely.getPocetBodov() < hracCierny.getPocetBodov()) {
                vitaz = hracCierny;
            }
            System.out.println("Víťazom sa stáva: " + vitaz.getMeno() + "(" + vitaz.getFarba() + ")" );
            System.out.println("Gratulujeme k víťazstvu.");
        } else {
            System.out.println("Víťazom sa nestáva nikto, hra skončila REMÍZOU !");
            System.out.println("Gratulujeme obidvom. :)");
        }
        System.out.println("Počet bodov biely: " + hracBiely.getPocetBodov());
        System.out.println("Počet bodov cierny: " + hracCierny.getPocetBodov());
    }
    
    
    // kontroluje  či je ťah možný a podľa pravidiel
    private static boolean kontrolaTahu(String ktoJeNaTahu1, int riadok1, int stlpec1, int newRiadok1, int newStlpec1) {
        //skontroluj farbu hráča a na základe toho spusť algoritmus
        int medzicislo = 0; //medzicislo pre posun stlpca podľa farby hráča
        int medzicislo2 = 0;
        if (ktoJeNaTahu1.equals("biely")) {
            medzicislo--;
            
            if ((riadok1 - newRiadok1) == 2) {
                //pokiaľ by to mal byt tah aj S vyhodenim
                medzicislo--;
                if ((stlpec1 - newStlpec1) == 2) {
                    medzicislo2 = -1;
                } else if ((stlpec1 - newStlpec1) == -2) {
                    medzicislo2 = 1;
                } else {
                    System.out.println("Zvolený ťah neexistuje! Zlý stlpec!");
                    return false;
                }
                
                if (hra.getPolickoFigurka((riadok1 - 1), (stlpec1 + medzicislo2)) == null) {
                    System.out.println("Tah nie je možný, pretože na medzipolíčku sa nenachádza figurka na vyhodenie");
                    return false;
                } else {
                    if (hra.getPolickoFigurka((riadok1 - 1), (stlpec1 + medzicislo2)).getFarbaFigurky() == ktoJeNaTahu.getFarba()) {
                        System.out.println("Nemôžeš vyhodiť svoju Figurku! Prosím opakuj ťah!");
                        return false;
                    } else {
                        if (hra.getPolickoFigurka(newRiadok1, newStlpec1) == null) {
                            System.out.println("TAH BOL VYKONANY");
                            return true;
                        }
                    }   
                }
            } else if ((riadok1 - newRiadok1) == 1) {
                //pokiaľ by to mal byt tah aj BEZ vyhodenia
                if ((newStlpec1 - stlpec1) == 1 || (newStlpec1 - stlpec1) == -1) {
                    if (hra.getPolickoFigurka(newRiadok1, newStlpec1) == null) {
                        System.out.println("TAH JE MOZNY");
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                System.out.println("Zvolený ťah neexistuje! Zlý riadok! ");
                return false;
            }
          
        } 
        // IF pre Čierneho  else pre Čierneho   else pre Čierneho 
        if (ktoJeNaTahu1.equals("cierny")) {
            medzicislo++;
            if ((riadok1 - newRiadok1) == -2) {
                //pokiaľ by to mal byt tah aj S vyhodenim
                medzicislo--;
                if ((stlpec1 - newStlpec1) == 2) {
                    medzicislo2 = -1;
                } else if ((stlpec1 - newStlpec1) == -2) {
                    medzicislo2 = 1;
                } else {
                    System.out.println("Zvolený ťah neexistuje! Zlý stlpec!");
                    return false;
                }
                
                if (hra.getPolickoFigurka((riadok1 + 1), (stlpec1 + medzicislo2)) == null) {
                    System.out.println("Tah nie je možný, pretože na medzipolíčku sa nenachádza figurka na vyhodenie");
                    return false;
                } else {
                    if (hra.getPolickoFigurka((riadok1 + 1), (stlpec1 + medzicislo2)).getFarbaFigurky() == ktoJeNaTahu.getFarba()) {
                        System.out.println("Nemôžeš vyhodiť svoju Figurku! Prosím opakuj ťah!");
                        return false;
                    } else {
                        if (hra.getPolickoFigurka(newRiadok1, newStlpec1) == null) {
                            System.out.println("TAH JE MOZNY");
                            
                            return true;
                        }
                    }   
                }

            } else if ((riadok1 - newRiadok1) == -1) {
                //pokiaľ by to mal byt tah aj BEZ vyhodenia
                if ((newStlpec1 - stlpec1) == 1 || (newStlpec1 - stlpec1) == -1) {
                    if (hra.getPolickoFigurka(newRiadok1, newStlpec1) == null) {
                        System.out.println("TAH JE MOZNY");
                        return true;
                    } else {
                        System.out.println("Tah nieje možny, pretože na políčku je figurka");
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                System.out.println("Zvolený ťah neexistuje! Zlý riadok");
                return false;
            }
        }
        return false;
    }
}
