import java.util.List;
public class StrategiaStadna {
    //polimorfizm
    public Wektor2D wyliczWektorRuchu(){
        //singleton
        List<Tunczyk> wszystkieRybcie = Symulacja.getInstance().getListaTunczykow();

        // domyślny wektor
        double bazaDx = 1.0;
        double bazaDy = 0.0;

        //siły boids

        double separacjaX = 0.0, separacjaY = 0.0;
        double spojnoscX = 0.0, spojnosc = 0.0;
        int liczbaSasiadow = 0;



    }


}
