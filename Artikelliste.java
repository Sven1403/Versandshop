package shop;

/**
 * Klasse Artikelliste.
 * 
 * @author S
 *
 */
public class Artikelliste {
    /**
     * Attribut artikel (Array).
     */
    private Artikel[] artikel;

    /**
     * Konstruktor Artikelliste der Array von artikel enthält.
     * 
     * @param artikel
     */
    public Artikelliste(Artikel[] artikel) {
        this.artikel = artikel;
    }

    /**
     * Setter für Anzahl Artikel.
     * 
     * @param groeßeArray
     */
    public void setAnzahlArtikel(int groeßeArray) {
        this.artikel = new Artikel[groeßeArray];
    }

    /**
     * Getter für anzahl Artikel.
     * 
     * @return artikel.length Länge des Arrays - 1 = anzahl Artikel
     */
    public int getAnzahlArtikel() {
        return artikel.length;
    }

    /**
     * Methode die Artikel an bestimmten Platz des Array hinzufügt.
     * 
     * @param stelleArray wo Artikel hin soll
     * @param artikel     Artikel der ins Array gepackt wird
     */
    public void artikelHinzufuegen(int stelleArray, Artikel artikel) {
        this.artikel[stelleArray] = artikel;
    }

    /**
     * Methode die alle Artikel anzeigt.
     */
    public void zeigeArtikel() {

        for (int i = 0; i < this.artikel.length; i++) {
            System.out.println(i + 1 + ") " + this.artikel[i].getName() + " "
                    + this.artikel[i].getPreis() + "€ "
                    + this.artikel[i].getAnzahl() + " Stück verfügbar");

        }
    }

    /**
     * "Getter" der ausgewaehlten Artikel zurückgibt.
     * 
     * @param auswahlArtikel
     * @return artikel Artikel wird vom ausgewählten Array Platz returnt
     */
    public String getAusgewaehltenArtikel(int auswahlArtikel) {

        int aktuellerArrayPlatz = auswahlArtikel - 1;

        return this.artikel[aktuellerArrayPlatz].getName();
    }

    /**
     * "Getter" der Preis von ausgewaehlten Artikel zurückgibt.
     * 
     * @param auswahlArtikel
     * @return artikel Artikelpreis wird vom ausgewählten Array Platz returnt
     */
    public double getPreisVonausgewaehltenArtikel(int auswahlArtikel) {
        int aktuellerArrayPlatz = auswahlArtikel - 1;

        return this.artikel[aktuellerArrayPlatz].getPreis();

    }

    /**
     * "Getter" der Anzahl von ausgewaehlten Artikel zurückgibt.
     * 
     * @param auswahlAnzahl
     * @return artikel Artikelanzahl wird vom ausgewählten Array Platz returnt
     */
    public int getAnzahlVonausgewaehltenArtikel(int auswahlAnzahl) {
        int aktuellerArrayPlatz = auswahlAnzahl - 1;

        return this.artikel[aktuellerArrayPlatz].getAnzahl();

    }

    /**
     * Methode die niedrigstenPreis der Artikel ermittelt. Dies wird in der main
     * mit dem Geld vom aktuellen Kunden verglichen
     * 
     * @return niedrigstenPreis des Artikel Arrays
     */
    public double getNiedrigstenPreis() {
        double niedrigsterPreis = this.artikel[0].getPreis();
        // Vergleiche alle Preise in Array mit ersten Wert des Arrays
        for (int i = 0; i < this.artikel.length; i++) {
            // Wenn geprüfter Preis kleiner erster Preis im Array, dann
            // geprüfter Preis = niedrigster Preis
            if (this.artikel[i].getPreis() < niedrigsterPreis) {
                niedrigsterPreis = this.artikel[i].getPreis();
            }
        }
        return niedrigsterPreis;
    }

    /**
     * Methode die anzahl maximum zurückgibt. Dies wird in der main benötigt um
     * zu prüfen ob noch Artikel in der Artikelliste vorhanden sind.
     * 
     * @return max maximaler wert (anzahl) des Arrays
     */
    public int getMaximumAnzahl() {
        int maximaleAnzahlvonArtikeln = this.artikel[0].getAnzahl();
        for (int i = 0; i < artikel.length; i++) {
            // Wenn geprüfte Anzahl größer erster Anzahl im Array, dann
            // geprüfte Anzahl = maximaleAnzahlvonArtikeln
            if (this.artikel[i].getAnzahl() > maximaleAnzahlvonArtikeln) {
                maximaleAnzahlvonArtikeln = this.artikel[i].getAnzahl();
            }
        }
        return maximaleAnzahlvonArtikeln;
    }


}
