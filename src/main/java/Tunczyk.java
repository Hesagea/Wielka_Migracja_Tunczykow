public class Tunczyk extends Agent {

    private StrategiaRuchu mojaStrategia;


    public Tunczyk(int id, int x, int y) {
        super(id, x, y);

        this.mojaStrategia = new RuchLosowy();
    }

    @Override
    public void krok() {
        // Zanim ryba się ruszy, sprawdza, czy stoi na mecie. Jeśli tak, przerywa działanie ("return;")
        if (Symulacja.getInstance().getPlansza().getKomorka(this.x, this.y).getCzyTarlisko()) {
            return;
        }

        Wektor2D wektor = mojaStrategia.wyliczWektorRuchu();

        int potencjalneX = this.x + (int) wektor.getDx();
        int potencjalneY = this.y + (int) wektor.getDy();

        Komorka bezpiecznaKomorka = Symulacja.getInstance().getPlansza().getKomorka(potencjalneX, potencjalneY);

        this.x = bezpiecznaKomorka.getX();
        this.y = bezpiecznaKomorka.getY();
        // zeby było cos widac
        System.out.println("Tuńczyk nr " + id + " przepłynął na nowe współrzędne: (" + this.x + ", " + this.y + ")");

        // --- OGŁOSZENIE SUKCESU ---
        if (bezpiecznaKomorka.getCzyTarlisko()) {
            System.out.println("🐟 SUKCES! Tuńczyk nr " + id + " dotarł do Tarliska!");
        }
    }


}