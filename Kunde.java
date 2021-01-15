package shop;

import exceptions.KeinGeldException;
import exceptions.KeineArtikelAufLagerException;

/**
 * Klasse Kunde.
 * 
 * @author S
 *
 */
public class Kunde {

    /**
     * Attribut name.
     */
    private String name;
    /**
     * Attribut geld.
     */
    private double geld;

    /**
     * Konstruktor Kunde der name und geld enthält.
     * 
     * @param name
     * @param geld
     */
    public Kunde(String name, double geld) {
        this.name = name;
        this.geld = geld;

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
     * Getter für Geld.
     * 
     * @return geld
     */
    public double getGeld() {
        return geld;
    }



    /**
     * Methode die Geld (Preis vom artikel) vom Kundengeld abzieht.
     * 
     * @param artikel       um auf Artikel zuzugreifen
     * @param geld
     * @param auswahlAnzahl ausgewählte Anzahl von Artikel
     * 
     * @return abgezogenes Geld
     */
    public double zieheGeldab(Artikel artikel, double geld, int auswahlAnzahl) {
        this.geld = this.geld - artikel.getPreis() * auswahlAnzahl;

        return this.geld;
    }

    /**
     * Methode kaufe wo Artikel gekauft wird und geld abgezogen wird.
     * 
     * @param artikel       um auf Artikel zuzugreifen
     * @param auswahlAnzahl ausgewählte Anzahl von Artikel
     * @throws KeinGeldException Ausnahme wird geworfen wenn Kunden Geld kleiner
     *                           als Artikelpreis*anzahl
     */
    public void kaufe(Artikel artikel, int auswahlAnzahl)
        throws KeinGeldException {
        double preisTotal = artikel.getPreis() * auswahlAnzahl;

        if (this.geld < preisTotal) {
            throw new KeinGeldException();
        }
        try {
            artikel.entferne(auswahlAnzahl);
            System.out.println("Sie kaufen " + auswahlAnzahl + "x "
                    + artikel.getName() + " für " + preisTotal + "€");
            zieheGeldab(artikel, this.geld, auswahlAnzahl);
        } catch (KeineArtikelAufLagerException lieferzeit) {

        }

    }


}
