public class Plansza {

    private static Plansza instance;

    private int szerokosc;
    private int wysokosc;
    private Komorka[][] tablicaKomorek;



    private Plansza() {
        this.szerokosc = 100;
        this.wysokosc = 100;
        this.tablicaKomorek= new Komorka[szerokosc][wysokosc];
        this.createPlansza();
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
        for(int i = 0; i < szerokosc; i++){
            for(int j = 0; j < wysokosc; j++){
                tablicaKomorek[i][j] = new Komorka(i, j);

                // ZMIANA: Tarlisko zajmuje teraz 4 ostatnie kolumny (indeksy 96, 97, 98, 99)
                if (i >= szerokosc - 4) {
                    tablicaKomorek[i][j].setCzyTarlisko(true);
                }
            }
        }
    }

    // Metoda pozwalająca pobrać konkretną komórkę, upewniając się, że nie wyjdziemy poza mapę
    public Komorka getKomorka(int x, int y) {
        // Proste zabezpieczenie, żeby ryba nie wypłynęła poza ekran
        int bezpieczneX = Math.max(0, Math.min(x, szerokosc - 1));
        int bezpieczneY = Math.max(0, Math.min(y, wysokosc - 1));

        return tablicaKomorek[bezpieczneX][bezpieczneY];
    }
    //aby inne klasy znały rozmiar oceanu
    public int getSzerokosc() {
        return this.szerokosc;
    }

    public int getWysokosc() {
        return this.wysokosc;
    }

}
