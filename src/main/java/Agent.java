// klasa bazowa, abstrakcyjna
public abstract class Agent {
    protected int id;
    protected int x;
    protected int y;

    // Energia
    protected double energia = 50.0;

    public Agent(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
    // metoda abstrakcyjna
    public abstract void krok();
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

    // Energia
    public double getEnergia() { return this.energia; }
    public void setEnergia(double energia) { this.energia = energia; }

    // zmienna trzymająca aktualny strategie
    protected StrategiaRuchu mojaStrategia;

    //pozwala podmienić strategie w dowolnym momencie
    public void setStrategia(StrategiaRuchu nowaStrategia) { this.mojaStrategia = nowaStrategia; }
}