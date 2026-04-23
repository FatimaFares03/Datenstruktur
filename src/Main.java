import java.util.List;

class Main {
    public static void main(String[] args) {
        // Fatima
        CsvReader reader = new CsvReader();
        List<Pruefung> pruefungen = reader.lesePruefungen("pruefungen.csv");
        List<Anmeldung> anmeldungen = reader.leseAnmeldungen("anmeldungen.csv");

        // Isabell
        KollisionsFinder finder = new KollisionsFinder();
        List<Kollision> kollisionen = finder.findeKollisionen(pruefungen, anmeldungen);

        // Magnus
        CsvWriter writer = new CsvWriter();
        writer.schreibeKollisionen(kollisionen, "kollisionen.csv");

        // GUI wurde erstellt
        new GUI();
    }
}