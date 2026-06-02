import java.util.ArrayList;

public class Komorka {
    private boolean czyTarlisko, czyTunczyk;
    private int x, y;
    //zakładamy, ze może byc tylko jeden tunczyk na jednej komorce

    public Komorka(int x, int y){
        this.czyTarlisko=false;
        this.czyTunczyk =false;
        this.x=x;
        this.y=y;
    }

    // możliwość zmiany cazy Tarlisko to samo  niżej z planktonem
    public void setCzyTarlisko(boolean tarlisko){
        this.czyTarlisko = tarlisko;
    }

    public void setCzyTunczyk(boolean tunczyk){
        this.czyTunczyk = tunczyk;
    }

}
