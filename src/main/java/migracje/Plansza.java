package migracje;

public class Plansza {

    private static Plansza instance;
    //zmienna P to nasza stworzona plansza

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
