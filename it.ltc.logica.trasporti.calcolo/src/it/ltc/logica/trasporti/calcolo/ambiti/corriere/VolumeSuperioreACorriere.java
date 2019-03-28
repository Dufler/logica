package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.VolumeSuperioreA;

public class VolumeSuperioreACorriere extends VolumeSuperioreA {
	
	public static final int ID = 136;

	public VolumeSuperioreACorriere(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

}
