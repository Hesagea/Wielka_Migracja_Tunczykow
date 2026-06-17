import java.util.ArrayList;
/**
 * Reprezentuje pojedyncze pole na planszy symulacji.
 * Przechowuje informacje o tym, jaki obiekt (ryba, plankton) aktualnie się na nim znajduje.
 */
public class Komorka {
    private boolean czyTarlisko, czyTunczyk;
    private int x, y;
    private Wektor2D pradMorski;
    //zakładamy, ze może byc tylko jeden tunczyk na jednej komorce

    private Plankton plankton; // obiekt
    public Komorka(int x, int y){
        this.czyTarlisko=false;
        this.czyTunczyk =false;
        this.x=x;
        this.y=y;
        this.pradMorski = new Wektor2D(0.0, 0.0);
    }

    // możliwość zmiany cazy Tarlisko to samo  niżej z planktonem
    public void setCzyTarlisko(boolean tarlisko){
        this.czyTarlisko = tarlisko;
    }

    public void setCzyTunczyk(boolean tunczyk){
        this.czyTunczyk = tunczyk;
    }

    public void setPradMorski(Wektor2D nowyPrad) {
        this.pradMorski = nowyPrad;
    }

    public boolean getCzyTarlisko() {
        return this.czyTarlisko;
    }

    //Tuńczyk będzie sprawdzał, jak mocno znosi go woda
    public Wektor2D getPradMorski() {
        return this.pradMorski;
    }

    public int getX() { return x; }
    public int getY() { return y; }


    public Plankton getPlankton(){
        return this.plankton;
    }

    public void setPlankton(Plankton plankton){
        this.plankton = plankton;
    }
}
