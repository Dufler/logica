package it.ltc.logica.cdg.gui.analisi.model;

/**
 * Rappresenta un elemento di bilancio per il controllo di gestione.<br>
 * E' possibile andare a crearne uno a partire da un entity CdgBilancio o CdgEventoRiepilogo.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ElementoBilancioCDG {
	
	private final int fase;
	private final double costoIpotetico;
	private final double ricavoIpotetico;
	private final double costoReale;
	private final String origine;
	private final Integer pezzi;
	private final Integer secondi;
	
	/**
	 * Costruttore di default a cui vanno specificati tutti i valori.
	 * @param fase
	 * @param costoIpotetico
	 * @param ricavoIpotetico
	 * @param costoReale
	 * @param origine
	 * @param pezzi
	 * @param secondi
	 */
	public ElementoBilancioCDG(int fase, double costoIpotetico, double ricavoIpotetico, double costoReale, String origine, Integer pezzi, Integer secondi) {
		this.fase = fase;
		this.costoIpotetico = costoIpotetico;
		this.ricavoIpotetico = ricavoIpotetico;
		this.costoReale = costoReale;
		this.origine = origine;
		this.pezzi = pezzi;
		this.secondi = secondi;
	}

//	/**
//	 * Costruisce l'elemento di riepilogo a partire dall'elemento di bilancio generico da spalmare sulle commesse.
//	 * @param costo
//	 * @param origine
//	 */
//	public ElementoBilancioCDG(double costo, String origine, int fase) {
//		this.fase = fase;
//		this.costoReale = costo;
//		this.costoIpotetico = costo;
//		this.ricavoIpotetico = 0;
//		this.origine = origine;
//		this.pezzi = null;
//		this.secondi = null;
//	}
//	
//	/**
//	 * Costruisce l'elemento di riepilogo a partire dallo specifico ingresso o uscita per la commessa.
//	 * @param bilancio
//	 */
//	public ElementoBilancioCDG(CdgCostoRicavoCommessa bilancio) {
//		this.fase = bilancio.getFase();
//		this.costoIpotetico = bilancio.getTipo() == TipoBilancioCdg.USCITA ? bilancio.getValore() : 0;
//		this.ricavoIpotetico = bilancio.getTipo() == TipoBilancioCdg.INGRESSO ? bilancio.getValore() : 0;
//		this.costoReale = 0;
//		this.origine = bilancio.getDescrizione();
//		this.pezzi = null;
//		this.secondi = null;
//	}
//	
//	/**
//	 * Costruisce un elemento di riepilogo a partire dall'evento registrato.
//	 * @param riepilogoEvento
//	 */
//	public ElementoBilancioCDG(CdgEventoRiepilogo riepilogoEvento) {
//		this.fase = trovaFaseDaRiepilogoEvento(riepilogoEvento);
//		this.costoIpotetico = calcolaCostoIpoteticoPezzo(riepilogoEvento);
//		this.ricavoIpotetico = calcolaRicavoIpoteticoPezzo(riepilogoEvento);
//		this.costoReale = calcolaValoreRiepilogoEvento(riepilogoEvento);
//		this.origine = trovaOrigineDaRiepilogoEvento(riepilogoEvento);
//		this.pezzi = riepilogoEvento.getPezzi();
//		this.secondi = riepilogoEvento.getDurataTotale();
//	}

	public int getFase() {
		return fase;
	}

	public double getCostoIpotetico() {
		return costoIpotetico;
	}

	public double getRicavoIpotetico() {
		return ricavoIpotetico;
	}

	public double getCostoReale() {
		return costoReale;
	}

	public String getOrigine() {
		return origine;
	}

	public Integer getPezzi() {
		return pezzi;
	}

	public Integer getSecondi() {
		return secondi;
	}

	@Override
	public String toString() {
		return "ElementoBilancioCDG [fase=" + fase + ", costoIpotetico=" + costoIpotetico + ", ricavoIpotetico="
				+ ricavoIpotetico + ", costoReale=" + costoReale + ", origine=" + origine + ", pezzi=" + pezzi
				+ ", secondi=" + secondi + "]";
	}

}
