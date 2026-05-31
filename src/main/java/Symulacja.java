public class Symulacja {

    private static Symulacja instance;

    private int simulationSeed;
    private double globalSpeed;

    private Symulacja() {
        this.simulationSeed = 42;
        this.globalSpeed = 1.0;
    }

    public static Symulacja getInstance() {
        if (instance == null) {
            instance = new Symulacja();
        }
        return instance;
    }


}
