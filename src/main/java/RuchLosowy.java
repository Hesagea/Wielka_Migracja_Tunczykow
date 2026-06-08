import java.util.Random;

public class RuchLosowy implements StrategiaRuchu {

    private Random random = new Random();

    @Override
    public Wektor2D wyliczWektorRuchu() {
        double dx = random.nextInt(4) + 1;

        double dy = random.nextInt(8) - 2;

        return new Wektor2D(dx, dy);
    }
}