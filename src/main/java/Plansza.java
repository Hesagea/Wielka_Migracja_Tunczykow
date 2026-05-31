public class Plansza {

    private static Plansza instance;

    private int szerokosc;
    private int wysokosc;

    private Plansza() {
        this.szerokosc = 100;
        this.wysokosc = 100;
    }

    //funkcja sprawdza czy plansza już istnieje
    public static Plansza getInstance() {
        if (instance == null) {
            instance = new Plansza();
        }
        return instance;
    }
}
