import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlanktonTest {

    @Test
    public void testSmierciZWycienczenia() {
        // Odpalamy środowisko testowe: bardzo silny prąd w lewo (-10), żeby ryba nie dopłynęła do mety
        FabrykaOceanu.stworzZycie(1, 0, -10, 0);

        // Tworzymy tuńczyka na samym początku mapy (x: 0, y: 0)
        Tunczyk tunczyk = new Tunczyk(2, 0, 0);

        int limit = 1000;
        int liczniki = 0;

        // Ryba walczy z prądem w miejscu, aż padnie
        while (tunczyk.getEnergia() > 0 && liczniki < limit) {
            tunczyk.krok();
            liczniki++;
        }

        // Teraz na pewno umrze z wyczerpania, zanim dotrze do celu!
        assertEquals("PADL", tunczyk.getStatus(), "Ryba po wyczerpaniu całej energii powinna zmienić status na PADL");
    }

    @Test
    public void testOdradzaniaSiePlanktonu() {
        // Tworzymy plankton, który odradza się w dokładnie 3 kroki (ostatni parametr)
        Plankton plankton = new Plankton(2, 15, 15, 20, 3);

        // Najpierw go zjadamy, żeby stał się niedostępny
        plankton.pobierzZasoby();
        assertFalse(plankton.isCzyDostepny()); // Upewniamy się, że zniknął

        // Symulujemy upływ czasu (wywołujemy krok tyle razy, ile wynosi czasOdnowy)
        plankton.krok(); // krok 1
        plankton.krok(); // krok 2

        // Po dwóch krokach wciąż nie powinien być dostępny
        assertFalse(plankton.isCzyDostepny(), "Plankton nie powinien odrodzić się przed czasem");

        plankton.krok(); // krok 3 - po tym kroku (krokiOdZjedzenia >= czasOdnowy) powinien się odrodzić!

        // Sprawdzamy, czy maszyna stanów zadziałała i plankton wrócił do życia
        assertTrue(plankton.isCzyDostepny(), "Plankton powinien się odrodzić po odczekaniu pełnego czasu odnowy");
    }
}