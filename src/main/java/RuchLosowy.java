import java.util.Random;

public class RuchLosowy implements StrategiaRuchu {

    private Random random = new Random();

    @Override
    public Wektor2D wyliczWektorRuchu() {
        // Losowy wektor dx od 5 do 10 (zawsze do przodu ku tarlisku)
        double dx = random.nextInt(6) + 5;
        // Losowy wektor dy od -5 do 5 (zygzak góra/dół)
        double dy = random.nextInt(11) - 5;

        return new Wektor2D(dx, dy);
    }
}