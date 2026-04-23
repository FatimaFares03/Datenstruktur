import java.time.Duration;
class Kollision {
    String pruefung1;
    String pruefung2;
    int anzahl;
    String abstand = "";
    boolean kollidiert = false;
    public void berechneZeitabstand(Pruefung p1, Pruefung p2){
        if (p1.getBegin() != null && p2.getBegin() != null) {
            // Überprüfen welche Prüfung kommt am Anfang
            Pruefung erste = p1.getBegin().isBefore(p2.getBegin()) ? p1 : p2;
            Pruefung zweite = p1.getBegin().isBefore(p2.getBegin()) ? p2 : p1;
            //hier findet die Berechnung statt
            Duration dauer = Duration.between(erste.getEnd(), zweite.getBegin());
            long stunden = dauer.toHours();
            long minuten = dauer.toMinutesPart();
            this.abstand = String.format("%dh %dm", stunden, minuten);
            if (!erste.getEnd().isBefore(zweite.getBegin())) {
                kollidiert = true;
            }
        }
    }
}