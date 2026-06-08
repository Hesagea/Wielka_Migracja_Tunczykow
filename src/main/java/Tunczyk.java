public class Tunczyk extends Agent {

    // orzystamy z pola klasy Agent!

    public Tunczyk(int id, int x, int y) {
        super(id, x, y);
        this.mojaStrategia = new RuchLosowy();
    }

    @Override
    public void krok() {
        // Zabezpieczenie: martwa ryba lub ta, która dopłynęła do celu, stoi w miejscu
        if (this.status.equals("U_CELU") || this.status.equals("PADL")) {
            return;
        }

        // 1. Pobranie chęci ruchu ze strategii
        Wektor2D wektor = mojaStrategia.wyliczWektorRuchu();

        // 2. Pobranie globalnego prądu morskiego
        Wektor2D prad = Symulacja.getInstance().getPradMorski();

        // 3. Spadek energii za wykonanie akcji
        this.energia -= 1.5;

        // 4. ŁĄCZENIE SIŁ: Sumujemy ruch autonomiczny z ruchem wody
        int potencjalneX = this.x + (int) wektor.getDx() + (int) prad.getDx();
        int potencjalneY = this.y + (int) wektor.getDy() + (int) prad.getDy();

        // 5. Pobranie bezpiecznej komórki z planszy
        Komorka bezpiecznaKomorka = Symulacja.getInstance().getPlansza().getKomorka(potencjalneX, potencjalneY);

        this.x = bezpiecznaKomorka.getX();
        this.y = bezpiecznaKomorka.getY();

        System.out.println("Tuńczyk nr " + id + " | Pozycja: (" + this.x + ", " + this.y + ") | Energia: " + this.energia);

        // 6. SYSTEM ODŻYWIANIA: Jeśli na polu jest gotowy plankton, ryba regeneruje siły
        if (bezpiecznaKomorka.getPlankton() != null && bezpiecznaKomorka.getPlankton().isCzyDostepny()) {
            int energiaZJedzenia = bezpiecznaKomorka.getPlankton().pobierzZasoby();
            this.energia += energiaZJedzenia;
            System.out.println("🐳 Tuńczyk nr " + id + " zjadł plankton! Energia wzrosła do: " + this.energia);
        }

        // 7. AKTUALIZACJA STATUSU AGENTA
        if (this.energia <= 0) {
            this.status = "PADL";
            System.out.println("❌ Tuńczyk nr " + id + " padł z głodu.");
        } else if (bezpiecznaKomorka.getCzyTarlisko()) {
            this.status = "U_CELU";
            System.out.println("🐟 SUKCES! Tuńczyk nr " + id + " dotarł do Tarliska!");
        }
    }
}