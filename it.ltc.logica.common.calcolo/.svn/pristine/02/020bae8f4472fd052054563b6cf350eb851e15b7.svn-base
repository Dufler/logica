package it.ltc.logica.common.calcolo.algoritmi;

public class AlgoritmoPercentuale implements IAlgoritmo {

	public static final String VERSIONE = "1.0";
	
	private final String nome;
	private final StrategiaPercentuale strategia;
	
	private AlgoritmoPercentuale(StrategiaPercentuale strategy) {
		nome = strategy.getNome();
		strategia = strategy;
	}
	
	public String toString() {
		String toString = (nome != null && !nome.isEmpty()) ? nome : TipoAlgoritmo.PERCENTUALE.getNome();
		return toString;
	}
	
	public static AlgoritmoPercentuale getInstance(StrategiaPercentuale strategy) {
		return new AlgoritmoPercentuale(strategy);
	}
	
	@Override
	public String getVersione() {
		return VERSIONE;
	}

	@Override
	public String getNome() {
		return TipoAlgoritmo.PERCENTUALE.getNome();
	}
	
	public double calcolaCosto(double base, double percentuale, Double minimo, Double massimo) {
		double costo = base * (percentuale / 100);
		//Aggiustamento su minimo e massimo, se necessario
		if (minimo != null && minimo > costo)
			costo = minimo;
		if (massimo != null && massimo < costo)
			costo = massimo;
		return costo;
	}

	@Override
	public TipoAlgoritmo getTipoAlgoritmo() {
		return TipoAlgoritmo.PERCENTUALE;
	}

	public StrategiaPercentuale getStrategia() {
		return strategia;
	}

}
