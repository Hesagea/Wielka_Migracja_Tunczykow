public class Plansza {

    private static Plansza instance;

    // na razie przykładowe zmienne
    private int szerokosc;
    private int wysokosc;

    private Plansza() {
        this.szerokosc = 100;
        this.wysokosc = 100;
    }

    public static Plansza getInstance() {
        if (instance == null) {
            instance = new Plansza();
        }
        return instance;
    }
}
