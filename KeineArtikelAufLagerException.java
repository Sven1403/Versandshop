package exceptions;

/**
 * Klasse KeineArtikelAufLagerException.
 * 
 * @author S
 *
 */
public class KeineArtikelAufLagerException extends RuntimeException {
    /**
     * Attribut serialVersionUID.
     */
    private static final long serialVersionUID = -8585202369565911743L;
    /**
     * Attribut lieferzeit.
     */
    private int lieferzeit;

    /**
     * Konstruktor der Fehlermeldung ausgibt.
     * 
     * @param lieferzeit wird in Artikelliste berechnet
     */
    public KeineArtikelAufLagerException(int lieferzeit) {
        this.lieferzeit = lieferzeit;
        System.out.println("Nicht genug Artikel auf Lager. "
                + "Ihre gewünschte Bestellmenge ist in "
                + lieferzeit + " Tagen wieder verfügbar");

    }
    
    /**
     * Getter für Lieferzeit.
     * 
     * @return lieferzeit
     */
    public int getLieferzeit() {
        return lieferzeit;
    }

}
