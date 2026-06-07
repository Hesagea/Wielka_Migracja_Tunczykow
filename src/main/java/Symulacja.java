import java.util.ArrayList;
import java.util.List;

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

        for (Tunczyk t : listaTunczykow) {
            t.krok();
        }
    }

    public Plansza getPlansza() {
        return Plansza.getInstance();
    }

    // Metoda do resetowania symulacji
    public void wyczyscSymulacje() {
        this.listaTunczykow.clear();
    }

    // Pozwala interfejsowi graficznemu pobrać listę ryb, żeby je narysować
    public java.util.List<Tunczyk> getListaTunczykow() {
        return this.listaTunczykow;
    }

    //pozwala Fabryce ustawić nowy prąd
    public void setPradMorski(Wektor2D prad) {
        this.pradMorski = prad;
    }

    // by ryby mogły prąd odczytać
    public Wektor2D getPradMorski() {
        return this.pradMorski;
    }

}
