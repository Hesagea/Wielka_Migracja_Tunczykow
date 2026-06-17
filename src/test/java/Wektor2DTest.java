import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Wektor2DTest {

    @Test
    public void testTworzeniaWektora() {
        // Przygotowanie danych
        Wektor2D wektor = new Wektor2D(5, -3);

        // Sprawdzenie, czy wartości przypisały się poprawnie
        assertEquals(5, wektor.getDx(), "Wartość X powinna wynosić 5");
        assertEquals(-3, wektor.getDy(), "Wartość Y powinna wynosić -3");
    }
}