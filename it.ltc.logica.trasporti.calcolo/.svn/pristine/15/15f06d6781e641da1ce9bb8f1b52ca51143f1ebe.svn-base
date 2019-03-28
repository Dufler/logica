package it.ltc.logica.trasporti.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.StrategiaScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaScaglioniRipetuti;

public enum StrategiaScaglioniPerTrasporti implements StrategiaScaglioni, StrategiaScaglioniRipetuti {

	COLLI(1, "COLLI", "Colli", 0), 
	PESO_VOLUME_100(100, "PESO_VOLUME_100", "Rapporto 1:100", 3), 
	PESO_VOLUME_150(150, "PESO_VOLUME_150", "Rapporto 1:150", 3), 
	PESO_VOLUME_167(167, "PESO_VOLUME_167",	"Rapporto 1:167", 3), 
	PESO_VOLUME_200(200, "PESO_VOLUME_200",	"Rapporto 1:200", 3), 
	PESO_VOLUME_250(250, "PESO_VOLUME_250", "Rapporto 1:250", 3), 
	PESO_VOLUME_300(300, "PESO_VOLUME_300", "Rapporto 1:300", 3);

	private final int rapporto;
	private final String codifica;
	private final String nome;
	private final int maxDecimali;

	private StrategiaScaglioniPerTrasporti(int rapporto, String codifica, String nome, int maxDecimali) {
		this.rapporto = rapporto;
		this.codifica = codifica;
		this.nome = nome;
		this.maxDecimali = maxDecimali;
	}

	public int getRapporto() {
		return rapporto;
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
