package it.ltc.logica.common.calcolo.algoritmi;

import java.util.HashMap;

public class AlgoritmoProporzionale implements IAlgoritmo {
	
	public static final String VERSIONE = "1.0";
	
	private static final HashMap<StrategiaProporzionale, AlgoritmoProporzionale> tipi = new HashMap<StrategiaProporzionale, AlgoritmoProporzionale>(3);
	
	private final String nome;
	private final StrategiaProporzionale strategia;
	
	private AlgoritmoProporzionale(String name, StrategiaProporzionale strategy) {
		nome = name;
		strategia = strategy;
	}
	
	public static AlgoritmoProporzionale getInstance(StrategiaProporzionale tipo) {
		if (!tipi.containsKey(tipo)) {
			tipi.put(tipo, new AlgoritmoProporzionale(tipo.getNome(), tipo));			
		}
		AlgoritmoProporzionale algoritmo = tipi.get(tipo);
		return algoritmo;
	}
	
	public String toString() {
		String toString = (nome != null && !nome.isEmpty()) ? nome : TipoAlgoritmo.PROPORZIONALE.getNome();
		return toString;
	}
	
	public boolean equals(Object o) {
		boolean uguali = false;
		if (o != null && o instanceof AlgoritmoProporzionale) {
			AlgoritmoProporzionale algoritmo = (AlgoritmoProporzionale) o;
			if (algoritmo.getStrategia().equals(strategia))
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
		return TipoAlgoritmo.PROPORZIONALE.getNome();
	}
	
	public StrategiaProporzionale getStrategia() {
		return strategia;
	}

	public double calcolaCosto(double valorePerElemento, double elementi, Double minimo, Double massimo) {
		double costo = valorePerElemento * elementi;
		//Aggiustamento su minimo e massimo, se necessario
		if (minimo != null && minimo > costo)
			costo = minimo;
		if (massimo != null && massimo < costo)
			costo = massimo;
		return costo;
	}

	@Override
	public TipoAlgoritmo getTipoAlgoritmo() {
		return TipoAlgoritmo.PROPORZIONALE;
	}

}
