
import java.util.*;

public class KollisionsFinder {

    List<Kollision> findeKollisionen(List<Pruefung> pruefungen, List<Anmeldung> anmeldungen) {
        // Studenten gruppieren
        Map<String, List<String>> studentPruefungen = new HashMap<>();

        for (Anmeldung a : anmeldungen) {
            if (!studentPruefungen.containsKey(a.student)) {
                studentPruefungen.put(a.student, new ArrayList<>());
            }
            studentPruefungen.get(a.student).add(a.pruefungKey);
        }
        // Kollisionen zählen
        Map<String, Map<String, Integer>> zaehler = new HashMap<>();

        for (List<String> pruefungenVonStudent : studentPruefungen.values()) {
            for (int i = 0; i < pruefungenVonStudent.size(); i++) {
                for (int j = i + 1; j < pruefungenVonStudent.size(); j++) {
                    String p1 = pruefungenVonStudent.get(i);
                    String p2 = pruefungenVonStudent.get(j);
                    if (!zaehler.containsKey(p1)) zaehler.put(p1, new HashMap<>());
                    if (!zaehler.containsKey(p2)) zaehler.put(p2, new HashMap<>());
                    zaehler.get(p1).put(p2, zaehler.get(p1).getOrDefault(p2, 0) + 1);
                    zaehler.get(p2).put(p1, zaehler.get(p2).getOrDefault(p1, 0) + 1);
                }
            }
        }
        // umwandeln in eine Liste
        List<Kollision> ergebnis = new ArrayList<>();
        for (String p1 : zaehler.keySet()) {
            for (String p2 : zaehler.get(p1).keySet()) {
                Kollision k = new Kollision();
                k.pruefung1 = p1;
                k.pruefung2 = p2;
                k.anzahl = zaehler.get(p1).get(p2);
                ergebnis.add(k);
            }
        }
        return ergebnis;
    }
}

