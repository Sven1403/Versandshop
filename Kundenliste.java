package shop;
/**
 * Klasse Kundenliste.
 * 
 * @author S
 *
 */
public class Kundenliste {

    /**
     * Attribut kunden (Array).
     */
    private Kunde[] kunden;

    /**
     * Konstruktor Kundenliste, der Array von kunden enthält.
     * 
     * @param kunden
     */
    public Kundenliste(Kunde[] kunden) {
        this.kunden = kunden;
    }

    /**
     * Setter für anzahl Kunden.
     * 
     * @param groeßeArray 
     */
    public void setAnzahlKunden(int groeßeArray) {
        this.kunden = new Kunde[groeßeArray];
    }

    /**
     * Getter für anzahl Kunden.
     * 
     * @return kunden.length Länge des Arrays - 1 = anzahl Kunden
     */
    public int getAnzahlKunden() {
        return kunden.length - 1;
    }

    /**
     * Methode die Kunde an bestimmten Platz des Array hinzufügt.
     * 
     * @param stelleArray wo Kunde hin soll
     * @param kunde Kunde der ins Array gepackt wird
     */
    public void kundeHinzufuegen(int stelleArray, Kunde kunde) {
        this.kunden[stelleArray] = kunde;
    }

    /**
     * Methode die alle Kunden anzeigt.
     */
    public void zeigeKunden() {
        System.out.println("Wählen Sie den Kunden");
        System.out.println("Welcher Kunde?");

        for (int i = 0; i < this.kunden.length; i++) {
            System.out.println(i + 1 + ") " + this.kunden[i].getName() + " "
                    + this.kunden[i].getGeld() + "€");

        }
    }

    /**
     * "Getter" der ausgewaehlten Kunde zurückgibt.
     * 
     * @param auswahlKunde
     * @return kunden Kunde wird vom ausgewählten Array Platz returnt
     */
    public String getAusgewaehltenKunde(int auswahlKunde) {

        int aktuellerArrayPlatz = auswahlKunde - 1;

        return this.kunden[aktuellerArrayPlatz].getName();
    }

    /**
     * "Getter" der Geld von ausgewaehlten Kunde zurückgibt.
     * 
     * @param auswahlKunde
     * @return kunden Kundengeld wird vom ausgewählten Array Platz returnt
     */
    public double getGeldVonausgewaehltenKunden(int auswahlKunde) {
        int aktuellerArrayPlatz = auswahlKunde - 1;

        return this.kunden[aktuellerArrayPlatz].getGeld();

    }

    /**
     * Methode die arme Kunden entfernt.
     * 
     * @param auswahlKunde
     */
    public void kundeEntfernen(int auswahlKunde) {
        // neues Array was erstellen was um 1 kleiner ist
        Kunde[] neueListe = new Kunde[kunden.length - 1];
        // kopiere alle Referenzen bis vor auswahlKunde
        for (int i = 0; i < auswahlKunde - 1; i++) {
            neueListe[i] = kunden[i];
        }
        // kopiere alle Referenzen nach auswahlKunde
        // neueListe ist dabei ums eins geringer
        for (int i = auswahlKunde; i < kunden.length; i++) {
            neueListe[i - 1] = kunden[i];
        }
        kunden = neueListe;
    }


}
