/**
 * Write a description of class HraciaPlocha here.
 * 
 * @author (Tomáš Staroň) 
 * @version (a version number or a date)
 */
public class Figurka {
    
    private String figFarba;
    
    //konštruktor
    public Figurka(String paFarba) {
        this.figFarba = paFarba;  
    }
    
    public String getFarbaFigurky() {
        return this.figFarba;
    }

}
