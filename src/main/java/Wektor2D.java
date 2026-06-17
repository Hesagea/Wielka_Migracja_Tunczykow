/**
 * Klasa pomocnicza reprezentująca wektor w dwuwymiarowym układzie współrzędnych (X, Y).
 * Używana do określania pozycji, wektora ruchu oraz siły prądów morskich.
 */
public class Wektor2D {
    // sumulacja pradu dla tunczykow
    private double dx;
    private double dy;
    public Wektor2D(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
        // konstruktor tworzacy sie tylko raz na początku
    }
    public double getDx(){return dx;}
    public double getDy(){return dy;}

// pomocnicza metoda dodawania prądów
    public void dodaj(Wektor2D inny){
        this.dx +=inny.dx;
        this.dy +=inny.dy;
    }
}
