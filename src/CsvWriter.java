import java.io.PrintWriter;
import java.util.*;

class CsvWriter {

    void schreibeKollisionen(List<Kollision> kollisionen, String datei) {
        // Nach Prüfung1 gruppieren
        Map<String, List<Kollision>> gruppen = new TreeMap<>();
        Set<String> allePruefungen = new TreeSet<>();

        for (Kollision k : kollisionen) {
            allePruefungen.add(k.pruefung1);
            allePruefungen.add(k.pruefung2);

            if (!gruppen.containsKey(k.pruefung1)) {
                gruppen.put(k.pruefung1, new ArrayList<>());
            }
            gruppen.get(k.pruefung1).add(k);
        }

        try (PrintWriter pw = new PrintWriter(datei)) {
            pw.println("Fach 1;Lfd. Nr.;Fach 1;Fach 2;Datum / Uhrzeit;Kollisionen;Abstand");

            for (String pruefung : allePruefungen) {
                // Titelzeile
                pw.println(pruefung + ";;;;Kein Datum gef;;");

                // Kollisionen
                if (gruppen.containsKey(pruefung)) {
                    int nr = 1;
                    for (Kollision k : gruppen.get(pruefung)) {
                        pw.println(";" + nr + ";" + k.pruefung1 + ";" + k.pruefung2 +
                                ";Kein Datum gef;" + k.anzahl + ";");
                        nr++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}

