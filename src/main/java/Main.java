import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- START WIELKIEJ MIGRACJI ---");

        // inicjalizacja Singletonów
        Symulacja symulacja = Symulacja.getInstance();
        Plansza plansza = Plansza.getInstance(); // To automatycznie stworzy siatkę

        System.out.println("Ocean gotowy! Wymiary: 100x100");

        // Tworzymy kępki planktonu i rozrzucamy je na trasie ryb
        // Parametry: id, x, y, zasobnosc (energia), czasOdnowy (w krokach)
        Plankton p1 = new Plankton(101, 5, 10, 30, 5);
        Plankton p2 = new Plankton(102, 10, 50, 30, 5);

        // Wkładamy plankton do odpowiednich komórek na planszy
        plansza.getKomorka(5, 10).setPlankton(p1);
        plansza.getKomorka(10, 50).setPlankton(p2);

        // tworzymy dwie ryby
        Tunczyk t1 = new Tunczyk(1, 0, 10);
        Tunczyk t2 = new Tunczyk(2, 0, 50);

        // wrzucamy do symulacji
        symulacja.dodajTunczyka(t1);
        symulacja.dodajTunczyka(t2);

        Application.launch(AplikacjaGUI.class, args);
    }
}