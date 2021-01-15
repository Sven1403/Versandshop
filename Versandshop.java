package shop;

import java.util.InputMismatchException;
import java.util.Scanner;
import exceptions.KeinGeldException;

/**
 * Klasse Versandshop wo Artikel und Kunden gemanaget werden.
 * 
 * @author S
 *
 */
public class Versandshop {
    /**
     * Main methode die alle wichtigen Aufgaben der Aufgabenstellung übernimmt.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner eingabe = new Scanner(System.in);
        // kundenListe erstellen und über Methode befüllen/Größe bestimmen
        Kundenliste kundenListe = new Kundenliste(null);
        kundenListe = kundenListeVersandshop(kundenListe);

        // artikelListe erstellen und über Methode befüllen/Größe bestimmen
        Artikelliste artikelListe = new Artikelliste(null);
        artikelListe = artikelListeVersandshop(artikelListe);
        // booleans für Programmende
        boolean kundeVorhanden = true;
        boolean artikelVorhanden = true;
        boolean nutzerWillKaufen = true;
        // Schleife für komplettes Programm
        while (kundeVorhanden && artikelVorhanden && nutzerWillKaufen) {
            nutzerWillKaufen = auswahlObManKaufenWill(eingabe,
                    nutzerWillKaufen);
            // Zeigt alle kunden der Kundenliste an
            kundenListe.zeigeKunden();
            // Auswahl des Kundes
            int auswahlKunde = auswahlDesKunden(eingabe, kundenListe);

            // ausgewaehlten Kunden in String initialisieren
            String ausgewaehlterKunde = kundenListe
                    .getAusgewaehltenKunde(auswahlKunde);
            // erstelle neuen Kunden mit ausgewaehlten Kunden und dessen Geld
            Kunde aktuellerKunde = new Kunde(ausgewaehlterKunde,
                    kundenListe.getGeldVonausgewaehltenKunden(auswahlKunde));

            System.out.println("Sie haben den Kunden " + ausgewaehlterKunde
                    + " ausgewählt. Er besitzt " + aktuellerKunde.getGeld()
                    + "€");
            // booleans für Prüfung ob gelöscht werden muss
            boolean armAmStart = false;
            boolean kundegeloescht = false;
            // Kunde entfernen wenn er nicht genug Geld hat
            if (artikelListe.getNiedrigstenPreis() > aktuellerKunde.getGeld()) {
                System.out
                        .println(aktuellerKunde + " hat nicht genug Geld um was"
                        + " zu kaufen. Er bräuchte "
                        + artikelListe.getNiedrigstenPreis() + " €.");
                armAmStart = true;
                kundenListe.kundeEntfernen(auswahlKunde);
            }
            // Artikel auswählen lassen wenn er genug Geld hat
            if (!armAmStart) {
                System.out.println("Welchen Artikel soll " + ausgewaehlterKunde
                        + " kaufen?");
                // Zeigt alle Artikel
                artikelListe.zeigeArtikel();
                // Auswahl des Artikels
                int auswahlArtikel = auswahlDesArtikels(eingabe, artikelListe);
                // ausgewaehlten Artikel in String initialisieren
                String ausgewaehlterArtikel = artikelListe
                        .getAusgewaehltenArtikel(auswahlArtikel);
                // Preis von aktuellen Artikel in double initialisieren
                double aktuellerArtikelPreis = artikelListe
                        .getPreisVonausgewaehltenArtikel(auswahlArtikel);
                // Anzahl von aktuellen Artikel in int initialisieren
                int aktuelleArtikelAnzahl = artikelListe
                        .getAnzahlVonausgewaehltenArtikel(auswahlArtikel);
                // erstelle neuen Artikel mit ausgewaehlten Artikel und dessen
                // Preis und Anzahl
                Artikel aktuellerArtikel = new Artikel(ausgewaehlterArtikel,
                        aktuellerArtikelPreis, aktuelleArtikelAnzahl);
                System.out
                        .println("Sie haben den Artikel " + ausgewaehlterArtikel
                        + " ausgewählt. Wie viel wollen Sie kaufen?");

                // Auswahl Anzahl
                int auswahlAnzahl = auswahlDerAnzahl(eingabe,
                        ausgewaehlterArtikel);
                // Prüfen ob Kunde genug Geld hat
                try {
                    // kaufe Methode mit Artikel + Anzahl
                    aktuellerKunde.kaufe(aktuellerArtikel, auswahlAnzahl);
                } catch (KeinGeldException e) {
                    // Wenn der Kunde mehr Artikel kaufen will als es überhaupt
                    // gibt -> erneut probieren lassen
                    if (auswahlAnzahl > aktuelleArtikelAnzahl) {
                        System.out.println(
                                "Es wurden mehr Artikel ausgewählt, als gekauft"
                                + " werden können. Bitte nochmal probieren.");
                    } else {
                        // Ansonsten löschen
                        System.out.println(ausgewaehlterKunde
                                + " ist arm und wird gelöscht :(");
                        kundenListe.kundeEntfernen(auswahlKunde);
                        kundegeloescht = true;
                    }
                }

                // Füge den Kunden mit neuen Geld an die Stelle des Arrays wo
                // der ausgewählte Kunde ist
                if (!kundegeloescht) {
                    kundenListe.kundeHinzufuegen(auswahlKunde - 1,
                            aktuellerKunde);
                }
                // Füge den Artikel mit neuer Anzahl an die Stelle des Arrays wo
                // der ausgewählte Artikel ist
                if (!kundegeloescht) {
                    artikelListe.artikelHinzufuegen(auswahlArtikel - 1,
                            aktuellerArtikel);
                }

            }
            kundeVorhanden = kundenListeLeer(kundenListe, kundeVorhanden);
            artikelVorhanden = artikelMenge0(artikelListe, artikelVorhanden);
        }
    }

    /**
     * Methode die kundenListe Größe bestimmt und mit Kunden befüllt.
     * 
     * @param kundenListe die erstelle kundenListe(Array)
     * 
     * @return kundenListe
     */
    public static Kundenliste kundenListeVersandshop(Kundenliste kundenListe) {
        // Lege kunden an
        kundenListe.setAnzahlKunden(8);
        Kunde[] kunden = new Kunde[kundenListe.getAnzahlKunden() + 1];
        kunden[0] = new Kunde("Naruto", zufaelligesGeld());
        kunden[1] = new Kunde("Sasuke", zufaelligesGeld());
        kunden[2] = new Kunde("Kakashi", zufaelligesGeld());
        kunden[3] = new Kunde("Deku", zufaelligesGeld());
        kunden[4] = new Kunde("Kaneki Ken", zufaelligesGeld());
        kunden[5] = new Kunde("Akame", zufaelligesGeld());
        kunden[6] = new Kunde("Saitama", zufaelligesGeld());
        kunden[7] = new Kunde("Eren", zufaelligesGeld());

        for (int i = 0; i < kundenListe.getAnzahlKunden() + 1; i++) {
            kundenListe.kundeHinzufuegen(i, kunden[i]);
        }
        return kundenListe;
    }
    /**
     * Methode die zufälliges Geld (0-500) für Kunden erstellt. auf 2
     * Kommastellen gerundet.
     * 
     * @return preis
     */
    public static double zufaelligesGeld() {
        double geld = Math.round((Math.random() * 500) * 100d) / 100d;
        return geld;
    }

    /**
     * Methode die artikelListe Größe bestimmt und mit Artikel befüllt.
     * 
     * @param artikelListe die erstelle artikelListe(Array)
     * 
     * @return artikelListe
     */
    public static Artikelliste artikelListeVersandshop(
            Artikelliste artikelListe) {
        // Lege artikel an
        artikelListe.setAnzahlArtikel(6);
        Artikel[] artikel = new Artikel[artikelListe.getAnzahlArtikel()];
        artikel[0] = new Artikel("Kunai", zufaelligerPreis(),
                zufaelligeAnzahl());
        artikel[1] = new Artikel("Stirnband", zufaelligerPreis(),
                zufaelligeAnzahl());
        artikel[2] = new Artikel("Magische Bohne", zufaelligerPreis(),
                zufaelligeAnzahl());
        artikel[3] = new Artikel("Shuriken", zufaelligerPreis(),
                zufaelligeAnzahl());
        artikel[4] = new Artikel("Schwert", zufaelligerPreis(),
                zufaelligeAnzahl());
        artikel[5] = new Artikel("Schriftrolle", zufaelligerPreis(),
                zufaelligeAnzahl());
        // Größe artikelListe bestimmen und artikel hinzufügen
        for (int i = 0; i < artikelListe.getAnzahlArtikel(); i++) {
            artikelListe.artikelHinzufuegen(i, artikel[i]);
        }

        return artikelListe;
    }

    /**
     * Methode die zufälligen Preis (0-150) für Artikel erstellt. auf 2
     * Kommastellen gerundet.
     * 
     * @return preis
     */
    public static double zufaelligerPreis() {
        double preis = Math.round((Math.random() * 150) * 100d) / 100d;
        return preis;
    }
    /**
     * Methode die zufällige Anzahl (0-25) für Artikel erstellt. auf 2
     * Kommastellen gerundet.
     * 
     * @return preis
     */
    public static int zufaelligeAnzahl() {
        int anzahl = (int) (Math.round((Math.random() * 25) * 100d) / 100d);
        return anzahl;
    }

    /**
     * Methode die Nutzer auswählen lässt was er machen will mit Exception.
     * 
     * @param eingabe
     * @param nutzerWillKaufen
     * 
     * @return nutzerWillKaufen wird nur in true zurückgegeben wenn Nutzer 1
     *         eingibt
     */
    public static boolean auswahlObManKaufenWill(Scanner eingabe,
            boolean nutzerWillKaufen) {
        System.out.println("Was wollen Sie tun?");
        System.out.println("1.) Kunde auswählen und etwas kaufen");
        System.out.println("2.) Nichts -> beendet Programm");
        boolean korrekteEingabe = false;
        int auswahlObManKaufenWill = 0;
        do {
            // Exception für Text
            try {
                auswahlObManKaufenWill = eingabe.nextInt();
                korrekteEingabe = true;
            } catch (InputMismatchException e) {
                System.out.println("Sie haben Text und keine Zahl eingegeben. "
                        + " Bitte nochmal probieren");
            } finally {
                eingabe.nextLine();
            }
            // Prüfen ob 1 oder 2 eingegeben wird
            if (auswahlObManKaufenWill == 1) {
                nutzerWillKaufen = true;
            } else if (auswahlObManKaufenWill == 2) {
                nutzerWillKaufen = false;
                System.out.println("Programm beendet.");
                break;
            } else {
                System.out.println("Bitte 1 oder 2 eingeben");
                korrekteEingabe = false;
            }
        } while (!korrekteEingabe);

        return nutzerWillKaufen;
    }

    /**
     * Methode die Kunden auswählen lässt und auf Exceptions prüft.
     * 
     * @param eingabe     Scanner
     * @param kundenListe Kundenliste des Versandshops
     * 
     * @return auswahlKunde welchen Kunde man ausgewählt hat
     */
    public static int auswahlDesKunden(Scanner eingabe,
            Kundenliste kundenListe) {
        int auswahlKunde = 0;

        boolean korrekteEingabe = false;
        boolean korrekteEingabe2 = false;

        do {
            // Exception für Text
            try {
                auswahlKunde = eingabe.nextInt();
                korrekteEingabe = true;
            } catch (InputMismatchException e) {
                System.out.println("Sie haben Text und keine Zahl eingegeben. "
                        + "Bitte nochmal probieren");
                kundenListe.zeigeKunden();
            } finally {

                eingabe.nextLine();
            }
        } while (!korrekteEingabe);
        do {
            // Exception für ArrayOutOfBounds
            try {
                kundenListe.getAusgewaehltenKunde(auswahlKunde);
                korrekteEingabe2 = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(
                        "Kunde existiert nicht. Bitte nochmal probieren");
                kundenListe.zeigeKunden();
                auswahlKunde = eingabe.nextInt();
                korrekteEingabe2 = false;
            }
        } while (!korrekteEingabe2);
        return auswahlKunde;
    }

    /**
     * Methode die Artikel auswählen lässt und auf Exceptions prüft.
     * 
     * @param eingabe      Scanner
     * @param artikelListe Artikellistee des Versandshops
     * 
     * @return auswahlArtikel welchen Artikel man ausgewählt hat
     */
    public static int auswahlDesArtikels(Scanner eingabe,
            Artikelliste artikelListe) {
        int auswahlArtikel = 0;

        boolean korrekteEingabe = false;
        boolean korrekteEingabe2 = false;

        do {
            // Exception für Text
            try {
                auswahlArtikel = eingabe.nextInt();
                korrekteEingabe = true;
            } catch (InputMismatchException e) {
                System.out.println("Sie haben Text und keine Zahl eingegeben. "
                        + "Bitte nochmal probieren");
                artikelListe.zeigeArtikel();
            } finally {

                eingabe.nextLine();
            }
        } while (!korrekteEingabe);
        do {
            // Exception für ArrayOutOfBounds
            try {
                artikelListe.getAusgewaehltenArtikel(auswahlArtikel);
                korrekteEingabe2 = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out
                        .println("A existiert nicht. Bitte nochmal probieren");
                artikelListe.zeigeArtikel();
                auswahlArtikel = eingabe.nextInt();
                korrekteEingabe2 = false;
            }
        } while (!korrekteEingabe2);
        return auswahlArtikel;

    }

    /**
     * Methode die zu kaufende Anzahl auswählen lässt und auf Exception prüft.
     * 
     * @param eingabe              Scanner
     * @param ausgewaehlterArtikel wird für Textausgabe genutzt
     * 
     * @return auswahlAnzahl welche Anzahl man ausgewaehlt hat
     */
    public static int auswahlDerAnzahl(Scanner eingabe,
            String ausgewaehlterArtikel) {
        // Auswahl der Anzahl
        int auswahlAnzahl = 1;
        boolean korrekteEingabe = false;
        do {
            // Man muss mindest 1 Artikel kaufen
            if (auswahlAnzahl <= 0) {
                System.out.println("Sie müssen mindestens eine Mengenheit "
                        + "des Artikels " + ausgewaehlterArtikel
                        + " kaufen. Bitte nochmal probieren.");
            }
            // Exception für Text
            try {
                auswahlAnzahl = eingabe.nextInt();
                korrekteEingabe = true;
            } catch (InputMismatchException e) {
                System.out.println("Sie haben Text und keine Zahl "
                        + "eingegeben. Bitte nochmal probieren. "
                        + "Wie viel wollen Sie von " + ausgewaehlterArtikel
                        + " kaufen?");
            } finally {
                eingabe.nextLine();
            }
        } while (!korrekteEingabe || auswahlAnzahl <= 0);
        return auswahlAnzahl;
    }

    /**
     * Methode die prüft ob kundenListe leer ist.
     * 
     * @param kundenListe    Array kundenListe
     * @param kundeVorhanden wird zur Prüfung genutzt ob noch Kunde vorhanden
     *                       ist
     * @return kundeVorhanden false = kein Kunde mehr in Array -> Abbruch
     *         Programm
     */
    public static boolean kundenListeLeer(Kundenliste kundenListe,
            boolean kundeVorhanden) {
        if (kundenListe.getAnzahlKunden() < 0) {
            kundeVorhanden = false;
            System.out.println("Keine Kunden mehr vorhanden. "
                    + "Der Versandshop geht nun bankrott :( "
                    + "- Programm beendet");
        }
        return kundeVorhanden;
    }

    /**
     * Methode die prüft ob irgendein Artikel noch Menge zum kaufen besitzt.
     * 
     * @param artikelListe     Array artikelListe
     * @param artikelVorhanden wird zur Prüfung genutzt ob noch Artikelmenge
     *                         vorhanden ist
     * @return artikelVorhanden false = kein Artikel mehr mit Menge verfügbar ->
     *         Abbruch Programm
     */
    public static boolean artikelMenge0(Artikelliste artikelListe,
            boolean artikelVorhanden) {
        if (artikelListe.getMaximumAnzahl() <= 0) {
            artikelVorhanden = false;
            System.out.println("Keine Artikel mehr vorhanden. "
                    + "Sie können nichts mehr kaufen :( "
                    + "- Programm beendet");
        }
        return artikelVorhanden;
    }

}
