import java.util.List;

public class AnalizatorSymulacji {

    // Pola przechowujące najnowsze obliczenia dla silnika graficznego
    private int ogolem;
    private long wDrodze;
    private long sukcesy;
    private long padniete;
    private double sredniaEnergia;
    private double zageszczeniePlanktonuProcent;
    private double procentPrzezywalnosci;

    // Analiza
    public void generujRaportKroku(int numerKroku) {
        List<Tunczyk> ryby = Symulacja.getInstance().getListaTunczykow();
        Plansza plansza = Plansza.getInstance();

        this.ogolem = ryby.size();
        this.wDrodze = ryby.stream().filter(r -> r.getStatus().equals("W_DRODZE")).count();
        this.sukcesy = ryby.stream().filter(r -> r.getStatus().equals("U_CELU")).count();
        this.padniete = ryby.stream().filter(r -> r.getStatus().equals("PADL")).count();

        this.sredniaEnergia = ryby.stream()
                .filter(r -> r.getStatus().equals("W_DRODZE"))
                .mapToDouble(Tunczyk::getEnergia)
                .average()
                .orElse(0.0);

        // OBLICZANIE ZAGĘSZCZENIA PLANKTONU NA PLANSZY
        int dostepnyPlankton = 0;
        int calkowityPlankton = 0;

        for (int x = 0; x < plansza.getSzerokosc(); x++) {
            for (int y = 0; y < plansza.getWysokosc(); y++) {
                Komorka k = plansza.getKomorka(x, y);
                if (k.getPlankton() != null) {
                    calkowityPlankton++;
                    if (k.getPlankton().isCzyDostepny()) {
                        dostepnyPlankton++;
                    }
                }
            }
        }

        this.zageszczeniePlanktonuProcent = calkowityPlankton > 0
                ? ((double) dostepnyPlankton / calkowityPlankton) * 100 : 0.0;

        this.procentPrzezywalnosci = this.ogolem > 0
                ? ((double) (this.wDrodze + this.sukcesy) / this.ogolem) * 100 : 0.0;
    }

    // wyswietlania


    public int getOgolem() { return ogolem; }
    public long getwDrodze() { return wDrodze; }
    public long getSukcesy() { return sukcesy; }
    public long getPadniete() { return padniete; }
    public double getSredniaEnergia() { return sredniaEnergia; }
    public double getZageszczeniePlanktonuProcent() { return zageszczeniePlanktonuProcent; }
    public double getProcentPrzezywalnosci() { return procentPrzezywalnosci; }
}
