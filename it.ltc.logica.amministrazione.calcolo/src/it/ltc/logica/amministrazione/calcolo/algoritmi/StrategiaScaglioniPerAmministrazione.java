package it.ltc.logica.amministrazione.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.StrategiaScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaScaglioniRipetuti;

public enum StrategiaScaglioniPerAmministrazione implements StrategiaScaglioni, StrategiaScaglioniRipetuti {

	ORDINI("ORDINI","Ordini", 0),
	COLLI("COLLI", "Colli", 0);

	private final String codifica;
	private final String nome;
	private final int maxDecimali;

	private StrategiaScaglioniPerAmministrazione(String codifica, String nome, int maxDecimali) {
		this.codifica = codifica;
		this.nome = nome;
		this.maxDecimali = maxDecimali;
	}

	public String getCodifica() {
		return codifica;
	}

	public String getNome() {
		return nome;
	}

	public String toString() {
		return nome;
	}

	@Override
	public int getMaxDecimali() {
		return maxDecimali;
	}

}