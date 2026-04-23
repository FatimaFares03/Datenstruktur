import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader  {

    List<Pruefung> lesePruefungen(String datei) {
        List<Pruefung> liste = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(datei))) {
            String zeile = br.readLine(); // Header überspringen
            String[] header = zeile.split(";");

            while ((zeile=br.readLine()) != null) {
                String[] teile = zeile.split(";");

                Pruefung p = new Pruefung();
                p.key = teile[5] + ".--." + teile[4] + ".--." + teile[2] + ".--." + teile[1];
                p.zeit = teile[10] + "-" + teile[11];

                // Datum finden
                for (int i = 12; i < teile.length && i < header.length; i++) {
                    if (!teile[i].isEmpty()) {
                        p.datum = header[i];
                        break;
                    }
                }

                liste.add(p);
            }
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }

        return liste;
    }

    List<Anmeldung> leseAnmeldungen(String datei) {
        List<Anmeldung> liste = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(datei))) {
            br.readLine(); // Header überspringen

            String zeile;
            while ((zeile = br.readLine()) != null) {
                String[] teile = zeile.split(";");

                Anmeldung a = new Anmeldung();
                a.student = teile[0];
                a.pruefungKey = teile[6] + ".--." + teile[5] + ".--." + teile[3] + ".--." + teile[2];

                liste.add(a);
            }
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }

        return liste;
    }
}
