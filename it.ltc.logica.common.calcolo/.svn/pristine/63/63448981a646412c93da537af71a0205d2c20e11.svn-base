package it.ltc.logica.common.calcolo.algoritmi;

import it.ltc.logica.gui.decoration.Decorator;

/**
 * Classe che mappa uno scaglione.
 * E' possibile compararli fra loro per l'ordinamento.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class Scaglione implements Comparable<Scaglione> {

	private double inizio;
	private double fine;
	private double valore;
	private String tipo;
	
	public double getInizio() {
		return inizio;
	}
	
	public void setInizio(double inizio) {
		this.inizio = inizio;
	}
	
	public double getFine() {
		return fine;
	}
	
	public void setFine(double fine) {
		this.fine = fine;
	}
	
	public double getValore() {
		return valore;
	}
	
	public void setValore(double valore) {
		this.valore = valore;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
		if ("COLLI".equals(tipo)) {
			inizio = Math.round(inizio);
			fine = Math.round(fine);
		}
	}
	
	public String getInizioStringa() {
		String s;
		if ("COLLI".equals(tipo)) 
			s = Decorator.getIntegerValue(inizio);
		else
			s = Decorator.getDoubleWithDecimalValue(inizio); 
		return s;
	}
	
	public String getFineStringa() {
		String s;
		if ("COLLI".equals(tipo)) 
			s = Decorator.getIntegerValue(fine);
		else
			s = Decorator.getDoubleWithDecimalValue(fine);
		return s;
	}
	
	public String getValoreStringa() {
		return Decorator.getEuroValue3Decimals(valore);
	}

	@Override
	public int compareTo(Scaglione o) {
		double altroInizio = o.getInizio();
		int compare = 0;
		if (inizio < altroInizio)
			compare = -1;
		else if (inizio > altroInizio)
			compare = 1;
		return compare;
	}
	
	public String toString() {
		String descrizione = getInizioStringa() + " - " + getFineStringa() + ": " + getValoreStringa();
		return descrizione;
	}

}
