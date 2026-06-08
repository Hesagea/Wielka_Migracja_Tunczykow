import java.util.List;

public class StrategiaStadna implements StrategiaRuchu {

    // Strategia
    @Override
    public Wektor2D wyliczWektorRuchu() {
        List<Tunczyk> wszystkieRyby = Symulacja.getInstance().getListaTunczykow();

        // Bazowo ryba ma silne parcie prosto do tarliska w prawo (oś X)
        double bazaDx = 6.0;
        double bazaDy = 0.0;

        // Siły algorytmu Boids
        double separacjaX = 0.0;
        double separacjaY = 0.0;
        double spojnoscY = 0.0;
        int liczbaSasiadow = 0;

        // Podwójna pętla (Nested Loop) — klasyczne podejście backendowe do analizy odległości
        for (Tunczyk ja : wszystkieRyby) {
            // Szukamy aktywnej ryby, która aktualnie przetwarza swój ruch
            if (ja.getStatus().equals("W_DRODZE")) {

                for (Tunczyk inna : wszystkieRyby) {
                    // Ignorujemy samą siebie oraz ryby, które już padły lub dopłynęły do celu
                    if (ja.getId() != inna.getId() && inna.getStatus().equals("W_DRODZE")) {

                        // Obliczamy realny dystans w pionie między 'inna' a 'ja'
                        double dystansY = inna.getY() - ja.getY();
                        double absolutnyDystans = Math.abs(dystansY);

                        // Promień percepcji sensorycznej ławicy (zasięg wzroku = 15 pól)
                        if (absolutnyDystans > 0 && absolutnyDystans < 15) {

                            // 1. ROZDZIELANIE (Separation): Jeśli są za blisko, odpychamy je w osiach, by na siebie nie wpadały
                            if (absolutnyDystans < 2) {
                                separacjaY -= dystansY * 0.5;
                                separacjaX -= 1.0; // Przyhamowanie, by nie uderzyć w ogon ryby z przodu
                            }

                            // 2. SPÓJNOŚĆ (Cohesion): Zbieramy pozycje pionowe sąsiadów, by wyrównać tor ławicy
                            spojnoscY += inna.getY();
                            liczbaSasiadow++;
                        }
                    }
                }

                // 3. WYRÓWNANIE (Alignment): Jeśli wykryto sąsiadów, korygujemy tor lotu do środka grupy ("w kupie")
                if (liczbaSasiadow > 0) {
                    double srednieY = spojnoscY / liczbaSasiadow;
                    bazaDy = (srednieY - ja.getY()) * 0.1;
                }

                // Przerywamy główną pętlę po wyliczeniu wektora dla aktualnie przetwarzanego agenta
                break;
            }
        }

        // Zwracamy kompletny, wypadkowy wektor ruchu dla silnika symulacji
        return new Wektor2D(bazaDx + separacjaX, bazaDy + separacjaY);
    }
}