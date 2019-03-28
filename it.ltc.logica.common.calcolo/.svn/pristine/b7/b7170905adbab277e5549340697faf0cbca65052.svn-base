package it.ltc.logica.common.calcolo.algoritmi;

import java.util.LinkedList;
import java.util.List;

public enum TipoAlgoritmo {
	
	NESSUNO("XXX", "Nessun Algoritmo", false),
	FISSO("FIS", "Fisso", true),
	PROPORZIONALE("PRO", "Proporzionale", true),
	SCAGLIONI("SCA", "A Scaglioni", true),
	SCAGLIONI_RIPETUTI("SCR", "A Scaglioni ripetuti", true),
	PERCENTUALE("PER", "Percentuale", true);
	
	private static List<TipoAlgoritmo> tipiVisualizzabili;
	
	private final String codifica;
	private final String nome;
	private final boolean visibile;
	
	private TipoAlgoritmo(String codifica, String nome, boolean visibile) {
		this.codifica = codifica;
		this.nome = nome;
		this.visibile = visibile;
	}
	
	public String toString() {
		return nome;
	}
 
	public String getCodifica() {
		return codifica;
	}

	public String getNome() {
		return nome;
	}
	
	public boolean isVisibile() {
		return visibile;
	}
	
	public static List<TipoAlgoritmo> getValoriVisualizzabili() {
		if (tipiVisualizzabili == null) {
			tipiVisualizzabili = new LinkedList<TipoAlgoritmo>();
			for (TipoAlgoritmo t : values()) {
				if (t.isVisibile()) {
					tipiVisualizzabili.add(t);
				}
			}
		}
		return tipiVisualizzabili;
	}
	
	public static TipoAlgoritmo getTipo(String codice) {
		TipoAlgoritmo tipo = NESSUNO;
		for (TipoAlgoritmo t : TipoAlgoritmo.values()) {
			if (t.getCodifica().equals(codice)) {
				tipo = t;
				break;
			}
		}
		return tipo;
	}

}
