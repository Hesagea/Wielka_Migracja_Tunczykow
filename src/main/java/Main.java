import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- START WIELKIEJ MIGRACJI ---");

        // inicjalizacja Singletonów
        Symulacja symulacja = Symulacja.getInstance();
        Plansza plansza = Plansza.getInstance(); // To automatycznie stworzy siatkę

        System.out.println("Ocean gotowy! Wymiary: 100x100");

        Application.launch(AplikacjaGUI.class, args);
    }
}