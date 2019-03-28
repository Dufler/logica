package it.ltc.logica.trasporti.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.StrategiaPercentuale;

public enum StrategiaPercentualePerTrasporti implements StrategiaPercentuale {
	
	SOLO_NOLO_BASE("Solo nolo base"),
	SOLO_NOLO_EXTRA("Solo nolo extra"),
	SOLO_NOLO("Solo nolo"),
	SOLO_EXTRA("Solo extra"),
	SOLO_CONTRASSEGNO("Solo contrassegno"),
	TUTTO("Su tutto");

	private final String nome;
	
	private StrategiaPercentualePerTrasporti(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
