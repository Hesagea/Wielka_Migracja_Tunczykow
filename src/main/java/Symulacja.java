import java.util.ArrayList;
import java.util.List;
/**
 * Główny silnik zarządzający pętlą czasową (wzorzec Singleton).
 * Odpowiada za cykliczne wykonywanie kroków i odświeżanie logiki wszystkich agentów na planszy.
 */
public class Symulacja {

    private static Symulacja instance;
    private List<Tunczyk> listaTunczykow;
    private Wektor2D pradMorski = new Wektor2D(0, 0);

    private Symulacja() {
        this.listaTunczykow = new ArrayList<>();
    }

    public static Symulacja getInstance() {
        if (instance == null) {
            instance = new Symulacja();
        }
        return instance;
    }

    public void dodajTunczyka(Tunczyk t) {
        listaTunczykow.add(t);
    }

    public void krokSymulacji() {
        // Krok dla wszystkich ryb
        for (Tunczyk t : listaTunczykow) {
            t.krok();
        }

        // 👑 Krok regeneracji dla planktonu rozsianego na planszy!
        Plansza plansza = getPlansza();
        for (int x = 0; x < plansza.getSzerokosc(); x++) {
            for (int y = 0; y < plansza.getWysokosc(); y++) {
                Komorka k = plansza.getKomorka(x, y);
                if (k.getPlankton() != null) {
                    k.getPlankton().krok(); // uruchamia licznik odnawiania krzaczka
                }
            }
        }
    }

    public Plansza getPlansza() { return Plansza.getInstance(); }
    public List<Tunczyk> getListaTunczykow() { return this.listaTunczykow; }
    public void wyczyscSymulacje() { this.listaTunczykow.clear(); }
    public void setPradMorski(Wektor2D prad) { this.pradMorski = prad; }
    public Wektor2D getPradMorski() { return this.pradMorski; }
}