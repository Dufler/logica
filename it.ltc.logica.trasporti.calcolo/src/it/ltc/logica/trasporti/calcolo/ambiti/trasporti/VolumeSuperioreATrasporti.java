package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.VolumeSuperioreA;

public class VolumeSuperioreATrasporti extends VolumeSuperioreA {
	
	public static final int ID = 136;

	public VolumeSuperioreATrasporti(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

}
