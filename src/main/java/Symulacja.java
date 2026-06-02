import java.util.ArrayList;
import java.util.List;

public class Symulacja {

    private static Symulacja instance;
    private List<Tunczyk> listaTunczykow;

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

        // Pętla przechodząca przez wszystkie ryby w worku
        for (Tunczyk t : listaTunczykow) {
            // ruszanie ryb
        }
    }

}
