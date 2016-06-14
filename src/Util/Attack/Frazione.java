package Util.Attack; /**
 * 
 */

import java.math.BigInteger;

/**
 * Questa classe rappresenta una frazione.
 * 
 * @author Eugenio
 */
public class Frazione {
	
	/**
	 * Numeratore della frazione.
	 */
	private BigInteger _numeratore;
	/**
	 * Denominatore della frazione.
	 */
	private BigInteger _denominatore;
	
	
	/**
	 * Costruttore.
	 * 
	 * @param _numeratore
	 * @param _denominatore
	 */
	public Frazione(BigInteger _numeratore, BigInteger _denominatore) {
		this._numeratore = _numeratore;
		this._denominatore = _denominatore;
	}
	/**
	 * Metodo per ridurre una frazione.
	 */
	private void riduci() {
		// Prendo l'MCD
		BigInteger MCD = _numeratore.gcd(_denominatore);
		// Divido numeratore e denominatore per MCD.
		this._numeratore = this._numeratore.divide(MCD);
		this._denominatore = this._denominatore.divide(MCD);
	}
	
	/**
	 * Metodo per verificare che la frazione sia positiva.
	 * 
	 * @return True se frazione positiva. False altrimenti.
	 */
	public boolean isPositiva() 
    {
        boolean positiva = false;
        if (_numeratore.compareTo(BigInteger.ZERO) > 0) {
        	positiva=true;
        }
        return positiva;
    }
	/**
	 * Metodo per sommare una frazione con un'altra.
	 * 
	 * @param frazione Frazione da sommare.
	 * @return Frazione sommata.
	 */
	public Frazione somma(Frazione frazione) {
		// Elementi del numeratore della frazione risultato
		BigInteger n1 = this._numeratore.multiply(frazione.get_denominatore());
		BigInteger n2 = this._denominatore.multiply(frazione.get_numeratore());
		BigInteger n = n1.add(n2);
		// Denominatore della frazione risultato
		BigInteger d = this._denominatore.multiply(frazione.get_denominatore());
		// Frazione risultato
		Frazione frazioneRisultato = new Frazione(n, d);
		// Riduco la frazione
		frazioneRisultato.riduci();
		
		return frazioneRisultato;
	}
	/**
	 * Metodo per sottrarre ad una frazione un'altra.
	 * 
	 * @param frazione Frazione da sottrarre.
	 * @return Frazione risultato.
	 */
	public Frazione sottrai(Frazione frazione) {
		// Elementi del numeratore della frazione risultato
		BigInteger n1 = this._numeratore.multiply(frazione.get_denominatore());
		BigInteger n2 = this._denominatore.multiply(frazione.get_numeratore());
		BigInteger n = n1.subtract(n2);
		// Denominatore della frazione risultato
		BigInteger d = this._denominatore.multiply(frazione.get_denominatore());
		// Frazione risultato
		Frazione frazioneRisultato = new Frazione(n, d);
		// Riduco la frazione
		frazioneRisultato.riduci();
		
		return frazioneRisultato;
	}
	
	/**
	 * ToString dell'oggetto.
	 */
	public String toString() {
		return "Numeratore: " + _numeratore.toString() + " \n" + "Denominatore: " + _denominatore.toString();
	}
	/**
	 * @return the _numeratore
	 */
	public BigInteger get_numeratore() {
		return _numeratore;
	}
	/**
	 * @param _numeratore the _numeratore to set
	 */
	public void set_numeratore(BigInteger _numeratore) {
		this._numeratore = _numeratore;
	}
	/**
	 * @return the _denominatore
	 */
	public BigInteger get_denominatore() {
		return _denominatore;
	}
	/**
	 * @param _denominatore the _denominatore to set
	 */
	public void set_denominatore(BigInteger _denominatore) {
		this._denominatore = _denominatore;
	}
	
	
}
