public class Main {
    public static void main(String[] args) {
        System.out.println("--- START WIELKIEJ MIGRACJI ---");

        // inicjalizacja Singletonów
        Symulacja symulacja = Symulacja.getInstance();
        Plansza plansza = Plansza.getInstance(); // To automatycznie stworzy siatkę

        System.out.println("Ocean gotowy! Wymiary: 100x100");

        // tworzymy dwie ryby
        Tunczyk t1 = new Tunczyk(1, 0, 10);
        Tunczyk t2 = new Tunczyk(2, 0, 50);

        // wrzucamy do symulacji
        symulacja.dodajTunczyka(t1);
        symulacja.dodajTunczyka(t2);

        for (int i = 1; i <= 20; i++) {
            System.out.println("\n--- KROK SYMULACJI NR " + i + " ---");
            symulacja.krokSymulacji();
        }
    }
}