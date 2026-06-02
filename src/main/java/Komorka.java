import java.util.ArrayList;
import java.util.List;

public class Komorka {
    private boolean czyTarlisko, czyPlankton;
    private int x, y;
    private List<Tunczyk> tunczykiNaKomorce;
    //zależy jak chcemy zapisywać ilosc tunczykow na komorce

    public Komorka(int x, int y){
        this.czyTarlisko=false;
        this.czyPlankton=false;
        this.x=x;
        this.y=y;
        this.tunczykiNaKomorce=new ArrayList<>();
    }

    // możliwość zmiany cazy Tarlisko to samo  niżej z planktonem
    public void setCzyTarlisko(boolean tarlisko){
        this.czyTarlisko = tarlisko;
    }

    public void setCzyPlankton(boolean plankton){
        this.czyPlankton = plankton;
    }

    // jakaś metoda na zmianę listy z tunczykami


}
