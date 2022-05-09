/**
 * Write a description of class HraciaPlocha here.
 * 
 * @author (Tomáš Staroň) 
 * @version (a version number or a date)
 */ 
public class Hrac {
    
    private String meno;
    private String farba;
    private int pocetFigurok;
    private int pocetBodov;
    
    
    //konštruktor
    public Hrac(String paMeno, String paFarba) {
        this.pocetFigurok = 8;
        this.pocetBodov = 0;
        this.meno = paMeno;
        this.farba = paFarba;
        
    }
    
    public String getMeno() {
        return this.meno;
    }
    
    public String getFarba() {
        return this.farba;
    }
    
    public int getPocetFigurok() {
        return this.pocetFigurok;
    }
    
    public int getPocetBodov() {
        return this.pocetBodov;
    }
    
    public void pridajBod() {
        this.pocetBodov++;
    }
    public void minusFigurka() {
        this.pocetFigurok--;
    }
}
