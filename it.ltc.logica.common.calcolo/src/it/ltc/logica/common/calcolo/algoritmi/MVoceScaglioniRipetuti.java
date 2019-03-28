package it.ltc.logica.common.calcolo.algoritmi;

public class MVoceScaglioniRipetuti implements MDettaglioVoce {
	
	private final double valore;
	private final double intervallo;
	private final Double sogliaMinima;
	private final Double sogliaMassima;
	
	public MVoceScaglioniRipetuti(double valore, double intervallo, Double sogliaMinima, Double sogliaMassima) {
		this.valore = valore;
		this.intervallo = intervallo;
		this.sogliaMinima = sogliaMinima;
		this.sogliaMassima = sogliaMassima;
	}

	public double getValore() {
		return valore;
	}

	public double getIntervallo() {
		return intervallo;
	}

	public Double getSogliaMinima() {
		return sogliaMinima;
	}

	public Double getSogliaMassima() {
		return sogliaMassima;
	}

}
