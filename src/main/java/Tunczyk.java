public class Tunczyk extends Agent {

    private StrategiaRuchu mojaStrategia;

    // 1. Konstruktor
    public Tunczyk(int id, int x, int y) {
        // "super" przekazuje dane (id, x, y) w górę, do konstruktora Agenta
        super(id, x, y);

        this.mojaStrategia = new RuchLosowy();
    }

    @Override
    public void krok() {
        Wektor2D wektor = mojaStrategia.wyliczWektorRuchu();

        int potencjalneX = this.x + (int) wektor.getDx();
        int potencjalneY = this.y + (int) wektor.getDy();

        Komorka bezpiecznaKomorka = Symulacja.getInstance().getPlansza().getKomorka(potencjalneX, potencjalneY);

        // Przypisujemy rybie ostateczne, bezpieczne współrzędne pobrane z Torusa
        this.x = bezpiecznaKomorka.getX();
        this.y = bezpiecznaKomorka.getY();
        // zeby było cos widac
        System.out.println("Tuńczyk nr " + id + " przepłynął na nowe współrzędne: (" + this.x + ", " + this.y + ")");
    }
}