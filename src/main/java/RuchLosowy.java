import java.util.Random;

public class RuchLosowy implements StrategiaRuchu {

    private Random random = new Random();

    public Wektor2D wyliczWektorRuchu() {
        double dx = random.nextInt(6) + 5;
        double dy = random.nextInt(11) - 5;

        // Zwracamy gotowy wektor wyliczony przez strategię
        return new Wektor2D(dx, dy);
    }
}