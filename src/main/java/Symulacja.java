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

        // pętla przechodząca przez wszystkie ryby w worku
        for (Tunczyk t : listaTunczykow) {
            t.krok();
        }
    }

    public Plansza getPlansza() {
        return Plansza.getInstance();
    }

    public List<Tunczyk> getListaTunczykow() {
        return this.listaTunczykow;
    }

    public void wyczyscSymulacje() {
        this.listaTunczykow.clear();
    }

    public void setPradMorski(Wektor2D prad) {
        this.pradMorski = prad;
    }

    public Wektor2D getPradMorski() {
        return this.pradMorski;
    }
}
