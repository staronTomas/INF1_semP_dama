/**
 * Write a description of class Sachovnica here.
 * 
 * @author (Tomáš Staroň) 
 * 
 * @version (a version number or a date)
 */


public class Sachovnica {
    
    
    // mnou vytvorene atributy
    private Figurka[][] polickoFigurka;
    private Kruh[][] polickoKruh;
    private int pocetBielych;
    private int pocetCiernych;
    
    
    // atributy vytvoreny na hodinách INF
    private Obdlznik[][] polickaSachovnice;
    private static final int POCET_POLICOK = 8;
    private static final String FARBA_1 = "green";
    private static final String FARBA_2 = "blue";
    
    private static Sachovnica instancia;
    
    
    //konštruktor
    public Sachovnica() {
        
        // čast konštruktora pre vytvorenie vizualnej podoby šachovnice (vytvorený na hodinách INF)
        String farbaA;
        String farbaB;
        this.polickaSachovnice = new Obdlznik[this.POCET_POLICOK][this.POCET_POLICOK];
        for (int i = 0; i < this.polickaSachovnice.length; i++) {
            if ((i % 2) == 0) {
                farbaA = this.FARBA_1;
                farbaB = this.FARBA_2;
            } else {
                farbaA = this.FARBA_2;
                farbaB = this.FARBA_1;
            }
            for (int j = 0; j < this.polickaSachovnice[i].length; j++) {
                this.polickaSachovnice[i][j] = new Obdlznik();
                this.polickaSachovnice[i][j].zmenPolohu((j * 50), (i * 50));
                this.polickaSachovnice[i][j].zmenStrany(50, 50);
                if ((j % 2) == 0) {
                    this.polickaSachovnice[i][j].zmenFarbu(farbaA);  
                } else {
                    this.polickaSachovnice[i][j].zmenFarbu(farbaB);
                }
            }
        }

        // moja časť konštruktora, vloží na plátno figurky
        
        
        this.polickoKruh = new Kruh[this.POCET_POLICOK][this.POCET_POLICOK];
        for (int i = 0; i < this.polickoKruh.length; i++) {
            for (int j = 0; j < this.polickoKruh[i].length; j++) {
                this.polickoKruh[i][j] = new Kruh();
            }
        }
        
        // vytvorenie figuriek pre cierneho
        for (int i = 1; i < 8; i += 2) {
            this.polickoKruh[0][i].zmenPriemer(25);
            this.polickoKruh[0][i].posunVodorovne(12.5 + (50 * i));
            this.polickoKruh[0][i].posunZvisle(12.5);
            this.polickoKruh[0][i].zmenFarbu("black");
        }
        for (int i = 0; i < 7; i += 2) {
            this.polickoKruh[1][i].zmenPriemer(25);
            this.polickoKruh[1][i].posunVodorovne(12.5 + (50 * i));
            this.polickoKruh[1][i].posunZvisle(62.5);
            this.polickoKruh[1][i].zmenFarbu("black");
        }
        
        // vytvorenie figuriek pre bieleho
        for (int i = 1; i < 8; i += 2) {
            this.polickoKruh[6][i].zmenPriemer(25);
            this.polickoKruh[6][i].posunVodorovne(12.5 + (50 * i));
            this.polickoKruh[6][i].posunZvisle(312.5);
            this.polickoKruh[6][i].zmenFarbu("white");
        }
        for (int i = 0; i < 7; i += 2) {
            this.polickoKruh[7][i].zmenPriemer(25);
            this.polickoKruh[7][i].posunVodorovne(12.5 + (50 * i));
            this.polickoKruh[7][i].posunZvisle(362.5);
            this.polickoKruh[7][i].zmenFarbu("white");
        }

        
        this.polickoFigurka = new Figurka[8][8];
        
        //vytvorenie figurok pre čierneho hráča
        for (int i = 1; i < this.polickoFigurka.length; i += 2) {
            this.polickoFigurka[0][i] = new Figurka("cierny");
            this.pocetCiernych++;
        }
        for (int i = 0; i < this.polickoFigurka.length; i += 2) {
            this.polickoFigurka[1][i] = new Figurka("cierny");
            this.pocetCiernych++;
        }
        
        //vytvorenie figurok pre bieleho hráča
        for (int i = 1; i < this.polickoFigurka.length; i += 2) {
            this.polickoFigurka[6][i] = new Figurka( "biely");
            this.pocetBielych++;
        }
        for (int i = 0; i < this.polickoFigurka.length; i += 2) {
            this.polickoFigurka[7][i] = new Figurka("biely");
            this.pocetBielych++;
        }
    }
    
    
    public Figurka getPolickoFigurka(int riadok, int stlpec) {
        return this.polickoFigurka[riadok][stlpec];
    }
    
    public String getFarbaFigurky(int riadok, int stlpec) {
        return this.polickoFigurka[riadok][stlpec].getFarbaFigurky();
    }
    
    public int getPocetPolicok() {
        return this.POCET_POLICOK;
    }
    
    //funkcia vytvorená na hodine INF
    public static Sachovnica getSachovnica() {
        if (Sachovnica.instancia == null) {
            Sachovnica.instancia = new Sachovnica();
        }
        return Sachovnica.instancia;
    }
    
    public static Sachovnica getSachovnica2() {
        Sachovnica.instancia = new Sachovnica();
        return Sachovnica.instancia;
    }
    
    
    
    
    //funkcia vytvorená na hodine INF, zobrazuje šachovnicu pomocou iných tried 
    public void zobrazSachovnicu() {
        
        for (int i = 0; i < this.polickaSachovnice.length; i++) {
            for (int j = 0; j < this.polickaSachovnice[i].length; j++) {
                this.polickaSachovnice[i][j].zobraz();
                this.polickoKruh[i][j].zobraz();
            }
        }
    }     
    
    // met´doa zaručí, že sa figúrka pohne a prekreslí sa aj na plátne
    public String[] pohniFigurkou(int riadok, int stlpec, int newRiadok, int newStlpec) {
        String farba;
        String farbaDruheho;
        int medzicislo = 0;
        int medzicislo2 = 0;
        String[] vysledok = new String[2]; // ukladám sem udaje o tom či sa vyhadzuje figurka, a či prišla figurka na koneic hracej plochy
        vysledok[0] = "tah";
        vysledok[1] = "ziaden_bod";
        // pre bieleho
        if (this.polickoFigurka[riadok][stlpec].getFarbaFigurky().equals("biely")) {
            farba = "white";
            farbaDruheho = "black";
            medzicislo--;
            int xVyhodenej = 0;
            int yVyhodenej = 0;
            if ((riadok - newRiadok) == 2) {
                //pokiaľ by to mal byt tah aj S vyhodenim
                medzicislo--;
                if ((stlpec - newStlpec) == 2) {
                    medzicislo2 = -1;
                }
                if ((stlpec - newStlpec) == -2) {
                    medzicislo2 = 1;
                }
                xVyhodenej = riadok - 1;
                yVyhodenej = stlpec + medzicislo2;
                
                // vyhodenie figurky
                this.polickoKruh[xVyhodenej][yVyhodenej].skry();
                this.polickoFigurka[xVyhodenej][yVyhodenej] = null;
                
                vysledok[0] = "tah_vyhod_cierneho";
            }
            
            // Kontroluje či už je figurka na konci plochy
            if (newRiadok == 0) {
                vysledok [1] = "biely_bod";
            }
            if (newRiadok == 1) {
                if (newStlpec == 0) {
                    if (this.polickoFigurka[0][1] != null) {
                        if (this.polickoFigurka[0][1].getFarbaFigurky().equals("biely")) {
                            vysledok[1] = "biely_bod";
                        }
                    }
                } else {
                    if (this.polickoFigurka[0][newStlpec - 1] != null && this.polickoFigurka[0][newStlpec + 1] != null) {
                        if (this.polickoFigurka[0][newStlpec - 1].getFarbaFigurky().equals("biely") && this.polickoFigurka[0][newStlpec + 1].getFarbaFigurky().equals("biely")) {
                            vysledok[1] = "biely_bod";
                        }
                    }
                    
                }
            }
            
        // pre čierneho
        } else {
            farba = "black";
            farbaDruheho = "white";
            medzicislo++;
            int xVyhodenej = 0;
            int yVyhodenej = 0;
            if ((riadok - newRiadok) == -2) {
                //pokiaľ by to mal byt tah aj S vyhodenim
                medzicislo--;
                if ((stlpec - newStlpec) == 2) {
                    medzicislo2 = -1;
                }
                if ((stlpec - newStlpec) == -2) {
                    medzicislo2 = 1;
                }
                xVyhodenej = riadok + 1;
                yVyhodenej = stlpec + medzicislo2;
                
                // vyhodenie figurky
                this.polickoKruh[xVyhodenej][yVyhodenej].skry();
                this.polickoFigurka[xVyhodenej][yVyhodenej] = null;
                vysledok[0] = "tah_vyhod_bieleho";
            } 
            
            
            // Kontroluje či už je figurka na konci plochy
            if (newRiadok == 7) {
                vysledok [1] = "cierny_bod";
            }
            if (newRiadok == 6) {
                if (newStlpec == 7) {
                    if (this.polickoFigurka[7][6] != null) {
                        if (this.polickoFigurka[7][6].getFarbaFigurky().equals("cierny")) {
                            vysledok[1] = "cierny_bod";
                        }
                        
                    }
                } else {
                    if (this.polickoFigurka[7][newStlpec - 1] != null && this.polickoFigurka[0][newStlpec + 1] != null) {
                        if (this.polickoFigurka[7][newStlpec - 1].getFarbaFigurky().equals("cierny") && this.polickoFigurka[0][newStlpec + 1].getFarbaFigurky().equals("cierny")) {
                            vysledok[1] = "cierny_bod";
                        }
                    }
                }
            }
        }
       
        this.polickoFigurka[newRiadok][newStlpec] = this.polickoFigurka[riadok][stlpec];
        this.polickoFigurka[riadok][stlpec] = null;
        this.polickoKruh[riadok][stlpec].skry();
        this.polickoKruh[riadok][stlpec] = new Kruh();
        this.polickoKruh[newRiadok][newStlpec] = new Kruh();
        this.polickoKruh[newRiadok][newStlpec].zmenPriemer(25);
        this.polickoKruh[newRiadok][newStlpec].posunVodorovne(12.5 + (50 * newStlpec));
        this.polickoKruh[newRiadok][newStlpec].posunZvisle(12.5 + 50 * newRiadok );
        this.polickoKruh[newRiadok][newStlpec].zmenFarbu(farba);
        this.polickoKruh[newRiadok][newStlpec].zobraz();
        
        return vysledok;
    }
    
    
    // kontroluje či hráč, ktorý je na ťahu sa stále môže pohnúť nejakou figúrkou, zaručí, že pokiaľ sa return rovná "false", tak sa hra skončí
    public boolean moznostPohybu(String farba) {
        
        // kontroluje či sa nachádza figurka na začiatku hracej plochy, ktorá sa môže pohnúť
        if (farba.equals("biely")) {
            for (int i = 1; i < 8; i = i + 2) {
                if (this.polickoFigurka[6][i] != null) {
                    if (this.polickoFigurka[6][i].getFarbaFigurky().equals(farba)) {
                        return true;
                    }
                }
            }
            for (int i = 0; i < 8; i = i + 2) {
                if (this.polickoFigurka[7][i] != null) {
                    if (this.polickoFigurka[7][i].getFarbaFigurky().equals(farba)) {
                        return true;
                    }
                }
            }
            for (int i = 1; i < 7 ; i++) {
                if (this.polickoFigurka[1][i] != null) {
                    if (this.polickoFigurka[0][i + 1] == null) {
                        return true;
                    }
                    if (i > 0 || i < 8) {
                        if (this.polickoFigurka[1][i - 1] == null) {
                            return true;
                        }
                    }
                }
            }
        } else {
            for (int i = 1; i < 8; i += 2) {
                if (this.polickoFigurka[0][i] != null) {
                    if (this.polickoFigurka[0][i].getFarbaFigurky().equals(farba)) {
                        return true;
                    }
                }
            }
            for (int i = 0; i < 7; i += 2) {
                if (this.polickoFigurka[1][i] != null) {
                    if (this.polickoFigurka[1][i].getFarbaFigurky().equals(farba)) {
                        return true;
                    }
                }
            }
            
            for (int i = 1; i < 8 ; i++) {
                if (this.polickoFigurka[6][i] != null) {
                    if (this.polickoFigurka[7][i - 1] == null) {
                        return true;
                    }
                    if (i > 0 || i < 7) {
                        if (this.polickoFigurka[7][i + 1] == null) {
                            return true;
                        }
                    }
                
                }
            }
        }
        
        // skontroluje či sa ešte nachádza nejaká figurka na hracej ploche, ktorá sa mže pohnúť
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.polickoFigurka[i][j] != null) {
                    if (this.polickoFigurka[i][j].getFarbaFigurky().equals(farba)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
