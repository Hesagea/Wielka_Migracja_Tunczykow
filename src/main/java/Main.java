public class Main {
    public static void main(String[] args) {
        System.out.println("--- URUCHAMIAMY WIELKĄ MIGRACJĘ TUŃCZYKÓW ---");

        Symulacja symulacja = Symulacja.getInstance();

        Plansza plansza = Plansza.getInstance();

        System.out.println("Ocean gotowy! Wymiary: 100x100");

        //tworzenie ryb

        //pierwszy krok pętli czasu
        symulacja.krokSymulacji();
    }
}