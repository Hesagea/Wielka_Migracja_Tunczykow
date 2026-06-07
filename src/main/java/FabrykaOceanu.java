import java.util.Random;

public class FabrykaOceanu {

    // Używamy metody statycznej, żeby działała jak globalna maszyna produkcyjna
    public static void stworzZycie(int iloscTunczykow, int procentPlanktonu) {
        Symulacja symulacja = Symulacja.getInstance();
        Random losowacz = new Random();

        // najpierw czyszczenie oceanu
        symulacja.wyczyscSymulacje();

        for (int i = 0; i < iloscTunczykow; i++) {
            int losoweX = losowacz.nextInt(100);
            int losoweY = losowacz.nextInt(100);

            Tunczyk nowyTunczyk = new Tunczyk(i + 1, losoweX, losoweY);
            symulacja.dodajTunczyka(nowyTunczyk);
        }

        System.out.println("FABRYKA: Pomyślnie wyprodukowano i wrzucono do oceanu " + iloscTunczykow + " tuńczyków!");

        // kod Hani C
        System.out.println("FABRYKA: Zlecenie na " + procentPlanktonu + "% planktonu czeka na implementację przez inżynierkę ekosystemu.");
    }
}