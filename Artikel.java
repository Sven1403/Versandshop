package shop;

import exceptions.KeineArtikelAufLagerException;

/**
 * Klasse Artikel.
 * 
 * @author S
 *
 */
public class Artikel {

    /**
     * Attribut name.
     */
    private String name;
    /**
     * Attribut preis.
     */
    private double preis;
    /**
     * Attribut anzahl.
     */
    private int anzahl;

    /**
     * Konstruktor Artikel der name, preis und anzahl enthält.
     * 
     * @param name
     * @param preis
     * @param anzahl
     */
    public Artikel(String name, double preis, int anzahl) {
        this.name = name;
        this.preis = preis;
        this.anzahl = anzahl;
    }

    /**
     * Getter für anzahl.
     * 
     * @return anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * Getter für Name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter für Preis.
     * 
     * @return preis
     */
    public double getPreis() {
        return preis;
    }

    /**
     * Methode die Artikel entfernt wenn Kunde bestimmte Menge kauft.
     * 
     * @param auswahlAnzahl ausgewählte Menge vom Kunden
     * @throws KeineArtikelAufLagerException gebe Methode lieferzeit über
     *                                       Exception aus
     */
    public void entferne(int auswahlAnzahl)
        throws KeineArtikelAufLagerException {
        if (auswahlAnzahl > this.anzahl) {
            throw new KeineArtikelAufLagerException(lieferzeit());
        } else {
            this.anzahl = this.anzahl - auswahlAnzahl;
        }
    }

    /**
     * Methode die Zufallszahl als lieferzeit erstellt.
     * 
     * @return zufallsZahl
     */
    private int lieferzeit() {
        int zufallsZahl = (int) (Math.random() * 21);

        return zufallsZahl;
    }

}
