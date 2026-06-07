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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AplikacjaGUI extends Application {

    @Override
    public void start(Stage glowneOkno) {
        // 1. Główny Reżyser (BorderPane - dzieli ekran na strefy)
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

        // przerwa miedzy elementami
        Region przerwa = new Region();
        VBox.setVgrow(przerwa, Priority.ALWAYS);

        // Element 1: Wpisywanie ilości tuńczyków85bfe0
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

        // Element 3: Masywny przycisk START
        Button przyciskStart = new Button("URUCHOM SYMULACJĘ");
        przyciskStart.setMaxWidth(Double.MAX_VALUE); // Rozciąga przycisk na całą szerokość menu
        przyciskStart.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 20px;-fx-font-family: 'Verdana';");

        // wrzucamy wszystko do naszego pionowego menu
        menuBoczne.getChildren().addAll(kafelekTunczyki, kafelekPlankton, przerwa, przyciskStart);

        // ==========================================
        // STREFA ŚRODKOWA: MIEJSCE NA OCEAN
        // ==========================================
        VBox srodek = new VBox(10);
        srodek.setAlignment(Pos.CENTER);
        // zastępczy, niebieski kwadrat, który na razie robi za ocean
        Rectangle oceanZastepstwo = new Rectangle(500, 500, Color.LIGHTBLUE);

        srodek.getChildren().addAll(oceanZastepstwo);

        // ==========================================
        // SKŁADANIE CAŁOŚCI I AKCJE
        // ==========================================
        glownyUklad.setRight(menuBoczne);
        glownyUklad.setCenter(srodek);

        // co będzie po kliknięciu zielonego przycisku?
        przyciskStart.setOnAction(event -> {
            try {
                int iloscRyba = Integer.parseInt(poleTunczyki.getText());
                int ilePlanktonu = (int) suwakPlankton.getValue();
                System.out.println("Przycisk kliknięty! Wysyłam zlecenie do Fabryki...");
                FabrykaOceanu.stworzZycie(iloscRyba, ilePlanktonu);

            } catch (NumberFormatException e) {
                // jeśli ilość nie będzie liczbą
                System.out.println("BŁĄD: W pole ilości tuńczyków musisz wpisać liczbę całkowitą!");
            }
        });

        // pakujemy na scenę i wyświetlamy
        Scene scena = new Scene(glownyUklad, 900, 600);
        glowneOkno.setTitle("Wielka Migracja Tuńczyków - Panel Sterowania");
        glowneOkno.setScene(scena);
        glowneOkno.show();
    }
}