import java.util.List;

public class StrategiaStadna implements StrategiaRuchu {

    // Strategia
    @Override
    public Wektor2D wyliczWektorRuchu() {
        List<Tunczyk> wszystkieRyby = Symulacja.getInstance().getListaTunczykow();

        double bazaDx = 1.0;
        double bazaDy = 0.0;
        double separacjaX = 0.0, separacjaY = 0.0;

        for (Tunczyk inna : wszystkieRyby) {
            // Logika Boids: Wyliczanie odległości i unikanie nakładania się ryb
            // Tuńczyk pobierze ten wektor i zmieni pozycję
        }

        return new Wektor2D(bazaDx + separacjaX, bazaDy + separacjaY);
    }
}