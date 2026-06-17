/**
 * Abstrakcyjna klasa bazowa reprezentująca każdy obiekt w symulacji.
 * Definiuje wspólne cechy (np. pozycję) dla wszystkich uczestników ekosystemu.
 */
public abstract class Agent {
    protected int id;
    protected int x;
    protected int y;
    protected double energia;
    protected String status = "W_DRODZE";

    public Agent(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
        this.energia = 50.0;
    }
    // metoda abstrakcyjna
    public abstract void krok();

    public String getStatus() {
        return this.status;
    }

    public double getEnergia() {
        return this.energia;
    }

    public int getId(){
        return id;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){ this.y = y;}


    // zmienna trzymająca aktualny strategię
    protected StrategiaRuchu mojaStrategia;

    //pozwala podmienić strategię w dowolnym momencie
    public void setStrategia(StrategiaRuchu nowaStrategia) {
        this.mojaStrategia = nowaStrategia;
    }



}
