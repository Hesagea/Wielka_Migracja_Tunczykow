import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AplikacjaGUI extends Application {
    // lista przechowująca współrzędne (X i Y) wizualnych kresek prądu
    private java.util.List<double[]> wizualizacjePradu = new java.util.ArrayList<>();

    @Override
    public void start(Stage glowneOkno) {
        // BorderPane - dzieli ekran na strefy
        BorderPane glownyUklad = new BorderPane();

        // ==========================================
        // STREFA PRAWA: MENU STEROWANIA (VBox)
        // ==========================================
        VBox menuBoczne = new VBox(15); // Odstęp 15px między elementami
        menuBoczne.setPadding(new Insets(20)); // Wewnętrzne marginesy
        menuBoczne.setStyle("-fx-background-color: #7bc5ea;-fx-border-color: #63b6e6; -fx-border-width: 0 0 0 3;");
        menuBoczne.setPrefWidth(350); // Szerokość naszego menu

        String stylNapisow = "-fx-font-family: 'Verdana'; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2d5365labelami tuńczyków planktonu i by uruchom b y;";
        String stylKafelka = "-fx-background-color: c8e6f7; -fx-padding: 15; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #5da7cf; -fx-border-width: 1;";

        // Element 1: Wpisywanie ilości tuńczyków
        Label tytulTunczyki = new Label("Początkowa liczba tuńczyków:");
        TextField poleTunczyki = new TextField("50"); // Domyślnie wpisane 50   zrobić ograniczenie !!!!!!!1
        tytulTunczyki.setStyle(stylNapisow);
        VBox kafelekTunczyki = new VBox(10); // Odstęp wewnątrz kafelka
        kafelekTunczyki.setStyle(stylKafelka);

        kafelekTunczyki.getChildren().addAll(tytulTunczyki, poleTunczyki);

        // Element 2: Suwak do planktonu
        Label tytulPlankton = new Label("Zagęszczenie planktonu (%):");
        tytulPlankton.setStyle(stylNapisow);
        Slider suwakPlankton = new Slider(0, 100, 20); // Od 0 do 100, startuje na 20
        suwakPlankton.setShowTickLabels(true); // Pokazuje cyferki na suwaku
        suwakPlankton.setShowTickMarks(true);  // Pokazuje kreseczki
        VBox kafelekPlankton = new VBox(15);
        kafelekPlankton.setStyle(stylKafelka);

        kafelekPlankton.getChildren().addAll(tytulPlankton, suwakPlankton);

        // Element 3: Wpisywanie wektora prądu morskiego
        Label tytulPrad = new Label("Wektor prądu morskiego (x,y)");
        tytulPrad.setStyle(stylNapisow);
        Label etykietaX = new Label("X:");
        Label etykietaY = new Label("Y:");
        TextField polex = new TextField("-2");
        polex.setPrefWidth(50);
        TextField poley = new TextField("-3");
        poley.setPrefWidth(50);
        javafx.scene.layout.HBox liniaWektora = new javafx.scene.layout.HBox(10);
        liniaWektora.setAlignment(Pos.CENTER_LEFT); // Żeby wszystko było równo w pionie
        liniaWektora.getChildren().addAll(etykietaX, polex, etykietaY, poley);
        VBox kafelekPrad = new VBox(10);
        kafelekPrad.setStyle(stylKafelka);

        kafelekPrad.getChildren().addAll(tytulPrad,liniaWektora);

        // Element 3: Masywny przycisk START
        Button przyciskStart = new Button("URUCHOM SYMULACJĘ");
        przyciskStart.setMaxWidth(Double.MAX_VALUE); // Rozciąga przycisk na całą szerokość menu
        przyciskStart.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 20px;-fx-font-family: 'Verdana';");

        // przerwa miedzy elementami
        Region przerwa = new Region();
        VBox.setVgrow(przerwa, Priority.ALWAYS);

        // wrzucamy wszystko do naszego pionowego menu
        menuBoczne.getChildren().addAll(kafelekTunczyki, kafelekPlankton, kafelekPrad,przerwa, przyciskStart);

        // ==========================================
        // STREFA ŚRODKOWA: MIEJSCE NA OCEAN
        // ==========================================
        VBox srodek = new VBox(10);
        srodek.setAlignment(Pos.CENTER);

        javafx.scene.canvas.Canvas plotnoOceanu = new javafx.scene.canvas.Canvas(500, 500);
        javafx.scene.canvas.GraphicsContext pedzel = plotnoOceanu.getGraphicsContext2D();

        pomalujTloOceanu(pedzel);

        srodek.getChildren().add(plotnoOceanu);

        // ==========================================
        // SKŁADANIE CAŁOŚCI I AKCJE
        // ==========================================
        glownyUklad.setRight(menuBoczne);
        glownyUklad.setCenter(srodek);

        // Tworzymy nasz niewidzialny metronom.
        // Duration.millis(200) oznacza, że klatka odświeża się co 200 milisekund (czyli 5 razy na sekundę).
        Timeline zegarSymulacji = new Timeline(new KeyFrame(Duration.millis(200), zdarzenie -> {
            Symulacja.getInstance().krokSymulacji();
            pomalujTloOceanu(pedzel);
            narysujPrad(pedzel);
            narysujRyby(pedzel);

        }));
        zegarSymulacji.setCycleCount(Timeline.INDEFINITE);

        // co będzie po kliknięciu zielonego przycisku?
        przyciskStart.setOnAction(event -> {
            try {
                int iloscRyba = Integer.parseInt(poleTunczyki.getText());
                int ilePlanktonu = (int) suwakPlankton.getValue();
                int xPrad = Integer.parseInt(polex.getText());
                int yPrad = Integer.parseInt(poley.getText());

                zegarSymulacji.stop();
                FabrykaOceanu.stworzZycie(iloscRyba, ilePlanktonu, xPrad, yPrad );

                // generacja prądu
                wizualizacjePradu.clear();
                java.util.Random losowaczPradu = new java.util.Random();
                for (int i = 0; i < 30; i++) {
                    // losuje pozycje na płótnie od 0 do 500 pikseli
                    wizualizacjePradu.add(new double[]{losowaczPradu.nextDouble() * 500, losowaczPradu.nextDouble() * 500});
                }

                zegarSymulacji.play();

                System.out.println("Zegar uruchomiony! Ryby płyną!");

            } catch (NumberFormatException e) {
                System.out.println("BŁĄD: W pole ilości tuńczyków musisz wpisać liczbę całkowitą!");
            }
        });

        // pakujemy na scenę i wyświetlamy
        Scene scena = new Scene(glownyUklad, 850, 500);
        glowneOkno.setTitle("Wielka Migracja Tuńczyków");
        glowneOkno.setScene(scena);
        glowneOkno.show();
    }

    // Metoda malująca tło naszego oceanu
    private void pomalujTloOceanu(javafx.scene.canvas.GraphicsContext pedzel) {
        // 1. Malujemy całe płótno na głęboki, morski kolor
        pedzel.setFill(Color.web("#1CA3EC")); // Ładny odcień niebieskiego
        pedzel.fillRect(0, 0, 500, 500);

        // tarlisko/meta
        pedzel.setFill(Color.web("#2E8B57", 0.6)); // Morski zielony (SeaGreen), lekko przezroczysty (0.6)
        pedzel.fillRect(495, 0, 5, 500); // Rysujemy pionowy pas od góry do dołu

        // siatka
        // skoro płótno ma 500px, a plansza 100 kratek, to jedna kratka ma 5x5 pikseli.
        pedzel.setStroke(Color.web("#ffffff", 0.2)); // biały kolor, ale bardzo przezroczysty (20%)
        pedzel.setLineWidth(0.5);

        for (int i = 0; i <= 500; i += 5) {
            pedzel.strokeLine(i, 0, i, 500); // linie pionowe
            pedzel.strokeLine(0, i, 500, i); // linie poziome
        }
    }

    private void narysujRyby(javafx.scene.canvas.GraphicsContext pedzel) {
        Symulacja symulacja = Symulacja.getInstance();
        java.util.List<Tunczyk> ryby = symulacja.getListaTunczykow();

        // ładuje obrazek do pamięci
        javafx.scene.image.Image obrazekTunczyka = new javafx.scene.image.Image("file:tunczyk.png");


        for (Tunczyk t : ryby) {
            // Plansza ma 100 kratek, a płótno 500 pikseli (1 kratka = 5px)
            int pikselX = t.getX() * 5;
            int pikselY = t.getY() * 5;

            // 3. Rysujemy nasz obrazek na płótnie!
            // Parametry: (co rysujemy, pozycja X, pozycja Y, szerokość obrazka, wysokość obrazka)
            // Ustawiłem 15x15 pikseli, żeby ryby były trochę większe niż pojedyncza kratka i ładnie na siebie nachodziły, ale możesz te liczby dowolnie zmieniać.
            pedzel.drawImage(obrazekTunczyka, pikselX, pikselY, 16, 16);
        }
    }

    private void narysujPrad(javafx.scene.canvas.GraphicsContext pedzel) {
        Symulacja symulacja = Symulacja.getInstance();
        Wektor2D wektorPradu = symulacja.getPradMorski();

        if (wektorPradu.getDx() == 0 && wektorPradu.getDy() == 0) return;

        // ustawienie białego, lekko przezroczystego koloru i grubości kreski
        pedzel.setStroke(Color.web("#ffffff", 0.4));
        pedzel.setLineWidth(1.5);

        for (double[] kreska : wizualizacjePradu) {
            // 1. Przesuwamy punkt zgodnie z prądem (mnożymy przez 2 dla lepszego efektu prędkości)
            kreska[0] += wektorPradu.getDx() * 2;
            kreska[1] += wektorPradu.getDy() * 2;

            // 2. Zawijanie ekranu (jak wypłynie, wraca z drugiej strony)
            if (kreska[0] > 500) kreska[0] -= 500;
            else if (kreska[0] < 0) kreska[0] += 500;

            if (kreska[1] > 500) kreska[1] -= 500;
            else if (kreska[1] < 0) kreska[1] += 500;

            // 3. Rysujemy ślad prądu (linię od "ogona" do obecnej pozycji punktu)
            // Ogon wyliczamy cofając się lekko w stronę przeciwną do prądu
            double ogonX = kreska[0] - (wektorPradu.getDx() * 4);
            double ogonY = kreska[1] - (wektorPradu.getDy() * 4);

            pedzel.strokeLine(ogonX, ogonY, kreska[0], kreska[1]);
        }
    }
}