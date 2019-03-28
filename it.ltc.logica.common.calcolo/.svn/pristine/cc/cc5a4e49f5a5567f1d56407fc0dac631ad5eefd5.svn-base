package it.ltc.logica.common.calcolo.algoritmi;

import java.util.HashMap;

public class AlgoritmoScaglioniRipetuti implements IAlgoritmo {
	
	public static final String VERSIONE = "1.0";
	
	private static final HashMap<StrategiaScaglioniRipetuti, AlgoritmoScaglioniRipetuti> tipi = new HashMap<StrategiaScaglioniRipetuti, AlgoritmoScaglioniRipetuti>(8);
	
	private String nome;
	private StrategiaScaglioniRipetuti tipo;
	
	private AlgoritmoScaglioniRipetuti(StrategiaScaglioniRipetuti type) {
		nome = type.getNome();
		tipo = type;
	}
	
	public static AlgoritmoScaglioniRipetuti getInstance(StrategiaScaglioniRipetuti tipo) {
		if (!tipi.containsKey(tipo)) {
			tipi.put(tipo, new AlgoritmoScaglioniRipetuti(tipo));			
		}
		AlgoritmoScaglioniRipetuti algoritmo = tipi.get(tipo);
		return algoritmo;
	}
	
	public StrategiaScaglioniRipetuti getTipo() {
		return tipo;
	}
	
	public String toString() {
		String toString = (nome != null && !nome.isEmpty()) ? nome : TipoAlgoritmo.SCAGLIONI_RIPETUTI.getNome();
		return toString;
	}

	@Override
	public String getVersione() {
		return VERSIONE;
	}

	@Override
	public String getNome() {
		return TipoAlgoritmo.SCAGLIONI_RIPETUTI.getNome();
	}
	
	public double calcolaCosto(double valore, double intervallo, double costoSingoloScaglione, Double minimo, Double massimo) {
		//Aggiusto il valore rispetto al massimo applicabile, se presente
		if (massimo != null && massimo < valore)
			valore = massimo;
		//Se non è stato inserito un minimo considero 0 come il minimo.
		if (minimo == null)
			minimo = 0.0;
		//Detraggo dal valore di calcolo il minimo
		valore = valore - minimo;
		//Nel caso in cui il valore sia più basso della soglia minima lo considero comunque come 0
		if (valore < 0)
			valore = 0.0;
		//Calcolo il numero di scaglioni e successivamente il costo
		double ripetizioni = Math.ceil(valore/intervallo);
		double costo = costoSingoloScaglione * ripetizioni;
		return costo;
	}
	
	public static int getMaxDecimali(StrategiaScaglioniRipetuti tipo) {
		int maxDecimali = tipo.getMaxDecimali();
		return maxDecimali;
	}

	@Override
	public TipoAlgoritmo getTipoAlgoritmo() {
		return TipoAlgoritmo.SCAGLIONI_RIPETUTI;
	}

}
