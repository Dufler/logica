package it.ltc.logica.common.calcolo.algoritmi;

public class MVoceProporzionale implements MDettaglioVoce {
	
	private final double valore;
	private final Double minimo;
	private final Double massimo;
	
	public MVoceProporzionale(double valore, Double minimo, Double massimo) {
		this.valore = valore;
		this.minimo = minimo;
		this.massimo = massimo;
	}

	public double getValore() {
		return valore;
	}

	public Double getMinimo() {
		return minimo;
	}

	public Double getMassimo() {
		return massimo;
	}

}
