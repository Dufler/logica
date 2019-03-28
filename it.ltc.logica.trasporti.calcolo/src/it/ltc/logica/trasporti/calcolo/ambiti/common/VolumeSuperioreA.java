package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasportoConValore;

public class VolumeSuperioreA extends IAmbitoTrasportoConValore {
	
	protected final double sogliaVolume;
	
	public VolumeSuperioreA(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
		sogliaVolume = parseValue(valore);
	}
	
	private double parseValue(String valore) {
		double value;
		try {
			value = Double.parseDouble(valore);
		} catch(NumberFormatException e) {
			value = 0;
		}
		return value;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		boolean applicabile = false;
		Double volume = model.getSpedizione().getVolume();
		if (volume != null && volume > sogliaVolume && sogliaVolume != 0) {
			applicabile = true;
		}
		return applicabile;
	}

}
