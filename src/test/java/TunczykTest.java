import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TunczykTest {

    @Test
    public void testSpadkuEnergiiPoKroku() {
        // Tworzymy tuńczyka zgodnie z Twoim konstruktorem (id: 1, pozycja X: 10, pozycja Y: 10)
        Tunczyk tunczyk = new Tunczyk(1, 10, 10);

        // Pobieramy energię przed krokiem
        double poczatkowaEnergia = tunczyk.getEnergia();

        // Wykonujemy właściwą metodę z Twojego kodu
        tunczyk.krok();

        // Sprawdzamy, czy po kroku energia spadła (powinna być mniejsza o 1.5)
        assertTrue(tunczyk.getEnergia() < poczatkowaEnergia, "Energia tuńczyka powinna spaść po wykonaniu kroku");
    }

    @Test
    public void testSmierciZWycienczenia() {
        Tunczyk tunczyk = new Tunczyk(2, 50, 50);

        // Zmuszamy rybę do wykonywania kroków (każdy zabiera 1.5 energii),
        // tak długo, aż jej energia spadnie do zera lub poniżej.
        // Dajemy limit 1000 kroków jako zabezpieczenie, by test nie zawiesił się w nieskończoność.
        int limit = 1000;
        int liczniki = 0;

        while (tunczyk.getEnergia() > 0 && liczniki < limit) {
            tunczyk.krok();
            liczniki++;
        }

        // Kiedy pętla się skończy, ryba powinna nie mieć już sił,
        // a jej metoda krok() powinna zaktualizować status na martwy.
        assertEquals("PADL", tunczyk.getStatus(), "Ryba po wyczerpaniu całej energii powinna zmienić status na PADL");
    }
}