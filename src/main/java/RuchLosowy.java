import java.util.Random;

public class RuchLosowy implements StrategiaRuchu {

    private Random random = new Random();

    // Tutaj znajduje się faktyczny kod tej strategii
    public Wektor2D wyliczWektorRuchu() {
        // Losujemy ruch od -1 do 1 na osi X oraz Y
        double dx = random.nextInt(3) - 1;
        double dy = random.nextInt(3) - 1;

        // Zwracamy gotowy wektor wyliczony przez strategię
        return new Wektor2D(dx, dy);
    }
}