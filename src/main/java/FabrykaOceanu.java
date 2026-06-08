import java.util.Random;

public class FabrykaOceanu {

    public static void stworzZycie(int iloscTunczykow, int procentPlanktonu, int xPrad, int yPrad) {
        Symulacja symulacja = Symulacja.getInstance();
        Plansza plansza = symulacja.getPlansza();
        Random losowacz = new Random();

        // najpierw czyszczenie
        symulacja.wyczyscSymulacje();

        // ustawienie prądu morskiego
        symulacja.setPradMorski(new Wektor2D(xPrad, yPrad));
        System.out.println("🏭 FABRYKA: Wprowadzono wektor prądu morskiego: (" + xPrad + ", " + yPrad + ")");

        // plankton na plansze
        int idPlanktonu = 1;
        // parametry odżywcze jedzenia (możesz je dowolnie zmieniać!)
        int zasobnoscJedzenia = 30; // ile energii daje zjedzenie
        int czasOdnawiania = 10;    // po ilu turach od zjedzenia odrasta

        for (int x = 0; x < plansza.getSzerokosc(); x++) {
            for (int y = 0; y < plansza.getWysokosc(); y++) {
                Komorka komorka = plansza.getKomorka(x, y);

                // czyszenie starej komórki przed nową symulacją
                komorka.setPlankton(null);

                // losowanie liczbę od 0 do 99.
                // jeśli wynik jest mniejszy niż wartość suwaka (np. 20%), sianie planktonu!
                if (losowacz.nextInt(100) < procentPlanktonu) {
                    Plankton nowyPlankton = new Plankton(idPlanktonu++, x, y, zasobnoscJedzenia, czasOdnawiania);
                    komorka.setPlankton(nowyPlankton);
                }
            }
        }
        System.out.println("🌱 FABRYKA: Zasiano plankton (zagęszczenie docelowe ok. " + procentPlanktonu + "%).");

        // produkowanie tuńczyków
        for (int i = 0; i < iloscTunczykow; i++) {
            int startoweX = 0;
            int startoweY = losowacz.nextInt(100);

            Tunczyk nowyTunczyk = new Tunczyk(i + 1, startoweX, startoweY);
            symulacja.dodajTunczyka(nowyTunczyk);
        }
        System.out.println("🐟 FABRYKA: Pomyślnie wyprodukowano i wpuszczono " + iloscTunczykow + " tuńczyków!");
    }
}