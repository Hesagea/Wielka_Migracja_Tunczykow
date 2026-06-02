public class Plansza {

    private static Plansza instance;

    private int szerokosc;
    private int wysokosc;
    private Komorka[][] tablicaKomorek;



    private Plansza() {
        this.szerokosc = 100;
        this.wysokosc = 100;
        this.tablicaKomorek= new Komorka[szerokosc][wysokosc];
    }

    //funkcja sprawdza czy plansza już istnieje
    public static Plansza getInstance() {
        if (instance == null) {
            instance = new Plansza();
        }
        return instance;
    }

    //pomocy...
    //tu byla francuska hania

    //polska Hania też tu była <3

    public void createPlansza(){

    }

}
