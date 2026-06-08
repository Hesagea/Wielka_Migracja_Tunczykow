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

    // Lista przechowująca współrzędne (X i Y) wizualnych kresek prądu
    private java.util.List<double[]> wizualizacjePradu = new java.util.ArrayList<>();

    // Ładujemy obrazki tylko RAZ, bezpośrednio do pamięci
    private javafx.scene.image.Image obrazekTunczyka = new javafx.scene.image.Image("file:tunczyk.png");
    private javafx.scene.image.Image obrazekMartwego = new javafx.scene.image.Image("file:tunczyk_czarny.png");
    private javafx.scene.image.Image obrazekZlotego = new javafx.scene.image.Image("file:tunczyk_zloty.png");
    private javafx.scene.image.Image obrazekPlanktonu = new javafx.scene.image.Image("file:plankton.png");
    private javafx.scene.image.Image obrazekPlanktonuZjedzonego = new javafx.scene.image.Image("file:plankton_zjedzony.png");

    private AnalizatorSymulacji analizator = new AnalizatorSymulacji();

    @Override
    public void start(Stage glowneOkno) {
        BorderPane glownyUklad = new BorderPane();
        glownyUklad.setStyle("-fx-background-color: #2C3E50;");

        // ==========================================
        // STREFA PRAWA: MENU STEROWANIA (VBox)
        // ==========================================
        VBox menuBoczne = new VBox(15);
        menuBoczne.setPadding(new Insets(20));
        menuBoczne.setStyle("-fx-background-color: #7bc5ea;-fx-border-color: #63b6e6; -fx-border-width: 0 0 0 3;");
        menuBoczne.setPrefWidth(350);

        String stylNapisow = "-fx-font-family: 'Verdana'; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2d5365;";
        String stylKafelka = "-fx-background-color: c8e6f7; -fx-padding: 15; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #5da7cf; -fx-border-width: 1;";

        // Element 1: Tuńczyki
        Label tytulTunczyki = new Label("Początkowa liczba tuńczyków:");
        TextField poleTunczyki = new TextField("20");
        tytulTunczyki.setStyle(stylNapisow);
        VBox kafelekTunczyki = new VBox(10);
        kafelekTunczyki.setStyle(stylKafelka);
        kafelekTunczyki.getChildren().addAll(tytulTunczyki, poleTunczyki);

        // Element 2: Plankton
        Label tytulPlankton = new Label("Zagęszczenie planktonu (%):");
        tytulPlankton.setStyle(stylNapisow);
        Slider suwakPlankton = new Slider(0, 100, 12);
        suwakPlankton.setShowTickLabels(true);
        suwakPlankton.setShowTickMarks(true);
        VBox kafelekPlankton = new VBox(15);
        kafelekPlankton.setStyle(stylKafelka);
        kafelekPlankton.getChildren().addAll(tytulPlankton, suwakPlankton);

        // Element 3: Wektor
        Label tytulPrad = new Label("Wektor prądu morskiego:");
        tytulPrad.setStyle(stylNapisow);
        Label etykietaX = new Label("X:");
        Label etykietaY = new Label("Y:");

        TextField polex = new TextField("-2");
        polex.setPrefWidth(50);
        TextField poley = new TextField("-2");
        poley.setPrefWidth(50);

        javafx.scene.layout.HBox liniaWektora = new javafx.scene.layout.HBox(10);
        liniaWektora.setAlignment(Pos.CENTER_LEFT);
        liniaWektora.getChildren().addAll(etykietaX, polex, etykietaY, poley);

        VBox kafelekPrad = new VBox(10);
        kafelekPrad.setStyle(stylKafelka);
        kafelekPrad.getChildren().addAll(tytulPrad, liniaWektora);

        // Element 4: Statystyki
        // ==========================================
        // KAFELEK STATYSTYK Z WYKRESEM KOŁOWYM
        // ==========================================
        VBox kafelekStatystyki = new VBox(8);
        kafelekStatystyki.setStyle(stylKafelka);

        Label tytulStatystyki = new Label("STATYSTYKI NA ŻYWO:");
        tytulStatystyki.setStyle(stylNapisow);
        Label statPrzezycie = new Label("Przeżywalność: 0.0%");
        statPrzezycie.setStyle("-fx-font-weight: bold; -fx-text-fill: darkgreen; -fx-font-size: 14px;");

        // Tworzymy "kawałki pizzy" (dane dla wykresu)
        javafx.scene.chart.PieChart.Data daneWDrodze = new javafx.scene.chart.PieChart.Data("W drodze", 1); // 1 na start, by wykres miał kształt
        javafx.scene.chart.PieChart.Data daneUCelu = new javafx.scene.chart.PieChart.Data("U celu", 0);
        javafx.scene.chart.PieChart.Data danePadly = new javafx.scene.chart.PieChart.Data("Padły", 0);

        // Składamy wykres
        javafx.scene.chart.PieChart wykresKolowy = new javafx.scene.chart.PieChart(
                javafx.collections.FXCollections.observableArrayList(daneWDrodze, daneUCelu, danePadly)
        );
        wykresKolowy.setPrefHeight(200); // Ograniczamy wysokość, by ładnie leżał w menu
        wykresKolowy.setLegendVisible(false); // Wyłączamy legendę (nazwy będą bezpośrednio na kawałkach)
        wykresKolowy.setLabelsVisible(true);

        kafelekStatystyki.getChildren().addAll(tytulStatystyki, statPrzezycie, wykresKolowy);

        // Przycisk START
        Button przyciskStart = new Button("URUCHOM SYMULACJĘ");
        przyciskStart.setMaxWidth(Double.MAX_VALUE);
        przyciskStart.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 20px;-fx-font-family: 'Verdana';");

        Region przerwa = new Region();
        VBox.setVgrow(przerwa, Priority.ALWAYS);

        menuBoczne.getChildren().addAll(kafelekTunczyki, kafelekPlankton, kafelekPrad, kafelekStatystyki, przerwa, przyciskStart);

        // ==========================================
        // STREFA ŚRODKOWA: MIEJSCE NA OCEAN (600x600)
        // ==========================================
        VBox srodek = new VBox(10);
        srodek.setAlignment(Pos.CENTER);

        // Zmiana na 600x600 pikseli
        javafx.scene.canvas.Canvas plotnoOceanu = new javafx.scene.canvas.Canvas(600, 600);
        javafx.scene.canvas.GraphicsContext pedzel = plotnoOceanu.getGraphicsContext2D();

        pomalujTloOceanu(pedzel);
        srodek.getChildren().add(plotnoOceanu);

        glownyUklad.setRight(menuBoczne);
        glownyUklad.setCenter(srodek);

        // ==========================================
        // ZEGAR SYMULACJI (METRONOM) Z FUNKCJĄ AUTO-STOPU
        // ==========================================
        Timeline zegarSymulacji = new Timeline(); // Najpierw tworzymy pusty zegar

        KeyFrame klatka = new KeyFrame(Duration.millis(200), zdarzenie -> {
            Symulacja.getInstance().krokSymulacji();

            pomalujTloOceanu(pedzel);
            narysujPrad(pedzel);
            narysujPlankton(pedzel);
            narysujRyby(pedzel);

            analizator.generujRaportKroku(1); // Uruchamiamy obliczenia Hani

            // Zostawiamy tekstowy procent, bo to główny wskaźnik
            statPrzezycie.setText(String.format("Przeżywalność: %.1f%%", analizator.getProcentPrzezywalnosci()));

            // POMPUJEMY DANE DO WYKRESU! JavaFX sam zajmie się animacją kawałków
            daneWDrodze.setPieValue(analizator.getwDrodze());
            daneUCelu.setPieValue(analizator.getSukcesy());
            danePadly.setPieValue(analizator.getPadniete());

            // POMPUJEMY DANE DO WYKRESU I AKTUALIZUJEMY ETYKIETY!
            long wDrodze = analizator.getwDrodze();
            long uCelu = analizator.getSukcesy();
            long padly = analizator.getPadniete();

            // Zmiana wielkości kawałka
            daneWDrodze.setPieValue(wDrodze);
            // Zmiana napisu na kawałku (np. "W drodze: 42")
            daneWDrodze.setName("W drodze: " + wDrodze);

            daneUCelu.setPieValue(uCelu);
            daneUCelu.setName("U celu: " + uCelu);

            danePadly.setPieValue(padly);
            danePadly.setName("Padły: " + padly);

            // === NOWOŚĆ: SPRAWDZANIE WARUNKU KOŃCA ===
            boolean czyKtosJeszczePlynie = false;
            java.util.List<Tunczyk> ryby = Symulacja.getInstance().getListaTunczykow();

            for (Tunczyk t : ryby) {
                if (t.getStatus().equals("W_DRODZE")) {
                    czyKtosJeszczePlynie = true;
                    break; // Jeśli chociaż jedna ryba płynie, nie musimy sprawdzać reszty
                }
            }

            // Jeśli nikt już nie płynie (a lista ryb nie jest pusta, żeby nie zakończyć przed startem)
            if (!czyKtosJeszczePlynie && !ryby.isEmpty()) {
                zegarSymulacji.stop(); // Zatrzymujemy czas!
                System.out.println("🏁 KONIEC SYMULACJI! Wszystkie ryby są na mecie lub padły.");
                przyciskStart.setText("ZAKOŃCZONO (Powtórz)"); // Zmieniamy napis na przycisku
                przyciskStart.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 18px;-fx-font-family: 'Verdana';"); // Zmieniamy kolor na zielony sukces
            }
        });

        zegarSymulacji.getKeyFrames().add(klatka);
        zegarSymulacji.setCycleCount(Timeline.INDEFINITE);

        przyciskStart.setOnAction(event -> {
            try {
                przyciskStart.setText("URUCHOM SYMULACJĘ");
                przyciskStart.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 20px;-fx-font-family: 'Verdana';");

                int iloscRyba = Integer.parseInt(poleTunczyki.getText());
                int ilePlanktonu = (int) suwakPlankton.getValue();
                int xPrad = Integer.parseInt(polex.getText());
                int yPrad = Integer.parseInt(poley.getText());

                zegarSymulacji.stop();
                FabrykaOceanu.stworzZycie(iloscRyba, ilePlanktonu, xPrad, yPrad);

                wizualizacjePradu.clear();
                java.util.Random losowaczPradu = new java.util.Random();
                for (int i = 0; i < 30; i++) {
                    // Losowanie kresek prądu do granicy 600px
                    wizualizacjePradu.add(new double[]{losowaczPradu.nextDouble() * 600, losowaczPradu.nextDouble() * 600});
                }

                zegarSymulacji.play();
                System.out.println("Zegar uruchomiony! Ryby płyną!");

            } catch (NumberFormatException e) {
                System.out.println("BŁĄD: W pola musisz wpisać liczby całkowite!");
            }
        });

        // Zmniejszone całe okno, by nie wychodziło za ekran (1000x650)
        Scene scena = new Scene(glownyUklad, 1000, 650);
        glowneOkno.setTitle("Wielka Migracja Tuńczyków");
        glowneOkno.setScene(scena);
        glowneOkno.show();
    }

    private void pomalujTloOceanu(javafx.scene.canvas.GraphicsContext pedzel) {
        pedzel.setFill(Color.web("#1CA3EC"));
        pedzel.fillRect(0, 0, 600, 600);

        // ZMIANA: Tarlisko jest teraz szerokie na 24 piksele i idealnie pomieści rybę!
        pedzel.setFill(Color.web("#2E8B57", 0.6));
        pedzel.fillRect(576, 0, 24, 600);

        // Siatka co 6 pikseli
        pedzel.setStroke(Color.web("#ffffff", 0.2));
        pedzel.setLineWidth(0.5);

        for (int i = 0; i <= 600; i += 6) {
            pedzel.strokeLine(i, 0, i, 600);
            pedzel.strokeLine(0, i, 600, i);
        }
    }

    private void narysujRyby(javafx.scene.canvas.GraphicsContext pedzel) {
        Symulacja symulacja = Symulacja.getInstance();
        java.util.List<Tunczyk> ryby = symulacja.getListaTunczykow();

        for (Tunczyk t : ryby) {
            int pikselX = t.getX() * 6;
            int pikselY = t.getY() * 6;

            // Trzy stany do wyboru:
            if (t.getStatus().equals("PADL")) {
                pedzel.drawImage(obrazekMartwego, pikselX, pikselY, 24, 24);

            } else if (t.getStatus().equals("U_CELU")) {
                // Rysujemy dumnego, złotego tuńczyka na mecie!
                pedzel.drawImage(obrazekZlotego, pikselX, pikselY, 24, 24);

            } else {
                pedzel.drawImage(obrazekTunczyka, pikselX, pikselY, 24, 24);
            }
        }
    }

    private void narysujPlankton(javafx.scene.canvas.GraphicsContext pedzel) {
        Plansza plansza = Symulacja.getInstance().getPlansza();

        for (int x = 0; x < plansza.getSzerokosc(); x++) {
            for (int y = 0; y < plansza.getWysokosc(); y++) {
                Komorka k = plansza.getKomorka(x, y);

                // 1. Sprawdzamy, czy w tej komórce w ogóle rośnie plankton
                if (k.getPlankton() != null) {

                    // 2. Jeśli rośnie, sprawdzamy w jakim jest stanie!
                    if (k.getPlankton().isCzyDostepny()) {
                        // Soczysty, gotowy do zjedzenia
                        pedzel.drawImage(obrazekPlanktonu, x * 6, y * 6, 12, 12);
                    } else {
                        // Zjedzony, w trakcie odrastania
                        pedzel.drawImage(obrazekPlanktonuZjedzonego, x * 6, y * 6, 12, 12);
                    }
                }
            }
        }
    }

    private void narysujPrad(javafx.scene.canvas.GraphicsContext pedzel) {
        Symulacja symulacja = Symulacja.getInstance();
        Wektor2D wektorPradu = symulacja.getPradMorski();

        if (wektorPradu.getDx() == 0 && wektorPradu.getDy() == 0) return;

        pedzel.setStroke(Color.web("#ffffff", 0.4));
        pedzel.setLineWidth(1.5);

        for (double[] kreska : wizualizacjePradu) {
            kreska[0] += wektorPradu.getDx() * 2;
            kreska[1] += wektorPradu.getDy() * 2;

            // Zawijanie ekranu przy krawędzi 600px
            if (kreska[0] > 600) kreska[0] -= 600;
            else if (kreska[0] < 0) kreska[0] += 600;

            if (kreska[1] > 600) kreska[1] -= 600;
            else if (kreska[1] < 0) kreska[1] += 600;

            double ogonX = kreska[0] - (wektorPradu.getDx() * 4);
            double ogonY = kreska[1] - (wektorPradu.getDy() * 4);

            pedzel.strokeLine(ogonX, ogonY, kreska[0], kreska[1]);
        }
    }
}