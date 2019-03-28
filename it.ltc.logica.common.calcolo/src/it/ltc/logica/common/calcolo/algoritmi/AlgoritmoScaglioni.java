package it.ltc.logica.common.calcolo.algoritmi;

import java.util.HashMap;
import java.util.List;

public class AlgoritmoScaglioni implements IAlgoritmo {
	
	public static final String VERSIONE = "1.0";
	
	private static final HashMap<StrategiaScaglioni, AlgoritmoScaglioni> tipi = new HashMap<StrategiaScaglioni, AlgoritmoScaglioni>(8);
	
	private String nome;
	private StrategiaScaglioni tipo;
	
	private AlgoritmoScaglioni(StrategiaScaglioni type) {
		nome = type.getNome();
		tipo = type;
	}
	
	public static AlgoritmoScaglioni getInstance(StrategiaScaglioni tipo) {
		if (!tipi.containsKey(tipo)) {
			tipi.put(tipo, new AlgoritmoScaglioni(tipo));			
		}
		AlgoritmoScaglioni algoritmo = tipi.get(tipo);
		return algoritmo;
	}
	
	public StrategiaScaglioni getTipo() {
		return tipo;
	}
	
	public String toString() {
		String toString = (nome != null && !nome.isEmpty()) ? nome : TipoAlgoritmo.SCAGLIONI.getNome();
		return toString;
	}
	
	public boolean equals(Object o) {
		boolean uguali = false;
		if (o != null && o instanceof AlgoritmoScaglioni) {
			AlgoritmoScaglioni algoritmo = (AlgoritmoScaglioni) o;
			if (algoritmo.getTipo().equals(tipo))
				uguali = true;
		}
		return uguali;
	}

	@Override
	public String getVersione() {
		return VERSIONE;
	}

	@Override
	public String getNome() {
		return TipoAlgoritmo.SCAGLIONI.getNome();
	}
	
	public double calcolaCosto(double valore, List<Scaglione> scaglioni) {
		double costo = 0;
		valore = Math.floor(valore * 1000) / 1000; //Approssimazione al massimo di 3 cifre decimali.
		for (Scaglione scaglione : scaglioni) {
			if (scaglione.getInizio() <= valore && valore <= scaglione.getFine()) {
				costo = scaglione.getValore();
				break;
			}
		}
		if (costo == 0) {
			Scaglione ultimo = scaglioni.get(scaglioni.size() - 1);
			if (valore > ultimo.getFine())
				costo = ultimo.getValore();
		}
		return costo;
	}

	public static int getMaxDecimali(StrategiaScaglioni tipo) {
		int maxDecimali = tipo.getMaxDecimali();
		return maxDecimali;
		
	}

	@Override
	public TipoAlgoritmo getTipoAlgoritmo() {
		return TipoAlgoritmo.SCAGLIONI;
	}

}
