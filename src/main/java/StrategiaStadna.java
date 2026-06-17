import java.util.List;
/**
 * Implementacja interfejsu StrategiaRuchu oparta na algorytmie Boids.
 * Symuluje zaawansowane zachowania stadne: dążenie do środka grupy, dopasowanie prędkości i unikanie kolizji z sąsiadami.
 */
public class StrategiaStadna implements StrategiaRuchu {

    @Override
    public Wektor2D wyliczWektorRuchu() {
        List<Tunczyk> wszystkieRyby = Symulacja.getInstance().getListaTunczykow();

        double bazaDx = 6.0;
        double bazaDy = 0.0;

        double separacjaX = 0.0;
        double separacjaY = 0.0;
        double spojnoscY = 0.0;
        int liczbaSasiadow = 0;

        for (Tunczyk ja : wszystkieRyby) {
            if (ja.getStatus().equals("W_DRODZE")) {

                for (Tunczyk inna : wszystkieRyby) {
                    if (ja.getId() != inna.getId() && inna.getStatus().equals("W_DRODZE")) {

                        double dystansY = inna.getY() - ja.getY();
                        double absolutnyDystans = Math.abs(dystansY);

                        if (absolutnyDystans > 0 && absolutnyDystans < 15) {
                            if (absolutnyDystans < 2) {
                                separacjaY -= dystansY * 0.5;
                                separacjaX -= 1.0;
                            }
                            spojnoscY += inna.getY();
                            liczbaSasiadow++;
                        }
                    }
                }

                if (liczbaSasiadow > 0) {
                    double srednieY = spojnoscY / liczbaSasiadow;
                    bazaDy = (srednieY - ja.getY()) * 0.1;
                }
                break;
            }
        }

        return new Wektor2D(bazaDx + separacjaX, bazaDy + separacjaY);
    }
}