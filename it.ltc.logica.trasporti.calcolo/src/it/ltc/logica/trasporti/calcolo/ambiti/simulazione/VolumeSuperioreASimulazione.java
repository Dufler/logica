package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.VolumeSuperioreA;

public class VolumeSuperioreASimulazione extends VolumeSuperioreA {
	
	public static final int ID = 135;

	public VolumeSuperioreASimulazione(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

}
