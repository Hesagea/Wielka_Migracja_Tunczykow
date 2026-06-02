public class Plankton extends Agent {
    private int zasobnosc;
    private int czasOdnowy;
    private int krokiOdZjedzenia;
    private boolean czyDostepny;


    public Plankton(int id, int x, int y, int zasobnosc, int czasOdnowy){
        // wywołanie klasy bazowej (agent)
        super(id, x, y);
        this.zasobnosc = zasobnosc;
        this.czasOdnowy = czasOdnowy;
        this.krokiOdZjedzenia = 0;
        this.czyDostepny = true;

    }
    @Override
    public void krok(){
        if(czyDostepny){
            return;
        }
        krokiOdZjedzenia++;

        if (krokiOdZjedzenia>=czasOdnowy){
            this.czyDostepny = true;
            this.krokiOdZjedzenia = 0;
            System.out.println("Plankton ID " + this.id + " odrodził się na pozycji [" + this.x + "," + this.y + "].");
        }
    }
    public int pobierzZasoby(){
        this.czyDostepny = false;
        this.krokiOdZjedzenia = 0;
        return this.zasobnosc;
    }
    public boolean isCzyDostepny(){
        return czyDostepny;
    }

    public int getZasobnosc(){
        return zasobnosc;
    }
}
