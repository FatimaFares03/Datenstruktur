import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private JTable tabelle;
    private DefaultTableModel model;
    private JTextField filterFeld;
    private List<Kollision> allKollisionen;

    public GUI() {
        setTitle("Klausurverwaltung HFT-Stuttgart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter:"));
        filterFeld = new JTextField(20);
        JButton filterButton = new JButton("Suchen");
        JButton resetButton = new JButton("Alle anzeigen");

        filterPanel.add(filterFeld);
        filterPanel.add(filterButton);
        filterPanel.add(resetButton);

        String[] spalte = {"Fach 1", "Fach 2", "Anzahl"};
        model = new DefaultTableModel(spalte, 0);
        tabelle = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelle);

        add(filterPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filter();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alleAnzeigen();
            }
        });

        ladeDaten();
        setVisible(true);
    }
    private void ladeDaten(){

            CsvReader reader = new CsvReader();
            List<Pruefung> pruefungen = reader.lesePruefungen("pruefungen.csv");
            List<Anmeldung> anmeldungen = reader.leseAnmeldungen("anmeldungen.csv");

            KollisionsFinder finder = new KollisionsFinder();
            List<Kollision> listeKollisionen = finder.findeKollisionen(pruefungen, anmeldungen);


            allKollisionen = new ArrayList<>();
            List<String> bereits = new ArrayList<>();

            for (Kollision k : listeKollisionen) {
                String key1 = k.pruefung1 + ":" + k.pruefung2;
                String key2 = k.pruefung2 + ":" + k.pruefung1;

                if (!bereits.contains(key1) && !bereits.contains(key2)) {
                    allKollisionen.add(k);
                    bereits.add(key1);
                }
            }

            alleAnzeigen();
        }
    private void alleAnzeigen(){
        model.setRowCount(0);
        for (Kollision k : allKollisionen) {
            model.addRow(new String[]{k.pruefung1, k.pruefung2, String.valueOf(k.anzahl)});
        }

    }
    private void filter(){

        String filterText = filterFeld.getText().toLowerCase();
        //hier leeren wir die Tabelle und fügen am Ende nur die suchrelative Ergebnisse
        model.setRowCount(0);

        for (Kollision k : allKollisionen) {
            if (k.pruefung1.toLowerCase().contains(filterText) ||
                    k.pruefung2.toLowerCase().contains(filterText)) {
                model.addRow(new String[]{k.pruefung1, k.pruefung2, String.valueOf(k.anzahl)});

            }
        }

    }
}
