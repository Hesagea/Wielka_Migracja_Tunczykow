# Wielka Migracja Tuńczyków - Symulacja Agentowa

**Autorzy:** Hanna Ciesielska, Hanna Słobodzian

## 📖 O projekcie
Projekt to interaktywna symulacja ekosystemu morskiego oparta na architekturze wieloagentowej. Głównym celem aplikacji jest zbadanie, jak zjawiska środowiskowe, takie jak dostępność pożywienia (planktonu) oraz siła i kierunek prądów morskich, wpływają na przeżywalność stada tuńczyków w drodze na tarlisko.

Aplikacja została napisana w języku **Java** z wykorzystaniem biblioteki **JavaFX** do stworzenia interaktywnego, skalowalnego interfejsu graficznego. Projekt wykorzystuje wzorce projektowe takie jak *Singleton* (zarządzanie symulacją i mapą) oraz *Strategia* (algorytmy ruchu ryb).

### Główne funkcjonalności:
* **Model Agentowy:** Aktywne tuńczyki (posiadające energię i cel) oraz pasywny plankton (odnawialne źródło pożywienia).
* **System Ekonomii Energii:** Każdy ruch kosztuje rybę określoną ilość energii. Zjedzenie planktonu regeneruje siły, a brak pożywienia kończy się śmiercią agenta.
* **Wpływ Środowiska:** Globalny wektor prądu morskiego (X, Y) znosi stado z obranego kursu.
* **Statystyki na Żywo:** Animowany wykres kołowy (PieChart) na bieżąco analizujący wskaźnik przeżywalności stada.

---

## Quick Start

### Wymagania:
* Java Development Kit (JDK) w wersji 17 lub nowszej.
* Maven (narzędzie do budowania projektu).

### Instrukcja uruchomienia:
1. **Pobierz repozytorium:**
   ```bash
   git clone [https://github.com/Hesagea/Wielka_Migracja_Tunczykow](https://github.com/Hesagea/Wielka_Migracja_Tunczykow)
   cd Wielka_Migracja_Tunczykow
2. **Uruchom projekt przez Maven**
   Otwórz terminal w folderze projektu i wpisz polecenie:
   ```bash
   mvn clean javafx:run
  Alternatywnie: Otwórz projekt w środowisku IntelliJ IDEA, upewnij się, że zależności Mavena są załadowane, i uruchom plik Main.java.

---

## Sample Run

### 1. Spektakularna wygrana
W tym scenariuszu stado natrafia na idealne warunki do migracji.
* **Początkowa liczba tuńczyków:** 50
* **Zagęszczenie planktonu (%):** 35
* **Wektor prądu morskiego (X, Y):** -1, -2
* **Wynik:** 100% stada szybko i sprawnie dociera na tarlisko. Na mecie tuńczyki przybierają złoty kolor zwycięstwa, a wykres kołowy wskazuje pełen sukces.

### 2. Wielkie Uśmiercanie
Scenariusz uświadamiający o potędze natury i bezlitosnym kryzysie zasobów.
* **Początkowa liczba tuńczyków:** 150
* **Zagęszczenie planktonu (%):** 5
* **Wektor prądu morskiego (X, Y):** -3, -2
* **Wynik:** Ogromne stado błyskawicznie wyjada znikome resztki pożywienia. Silny prąd wsteczny i boczny spycha ryby w róg oceanu. Przeżywalność drastycznie spada, na ekranie tworzy się cmentarzysko martwych agentów.

### 3. Rzeczywistość
Scenariusz odzwierciedlający naturalne, zrównoważone warunki oceaniczne, w których przetrwają tylko najsilniejsi.
* **Początkowa liczba tuńczyków:** 60
* **Zagęszczenie planktonu (%):** 5
* **Wektor prądu morskiego (X, Y):** -2, -2
* **Wynik:** Lekki prąd znosi ryby, a ograniczona ilość pożywienia sprawia, że stado naturalnie dzieli się na mniejsze grupy. Widzimy pełne spektrum ekosystemu: najszybsze i najszczęśliwsze osobniki docierają na tarlisko, podczas gdy słabsze ryby na tyłach stada padają z wyczerpania.
