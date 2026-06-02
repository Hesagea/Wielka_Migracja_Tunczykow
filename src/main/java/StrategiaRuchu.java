public interface StrategiaRuchu {
    // Każda strategia musi umieć wyliczyć wektor (kierunek i siłę) ruchu
    Wektor2D wyliczWektorRuchu();
}