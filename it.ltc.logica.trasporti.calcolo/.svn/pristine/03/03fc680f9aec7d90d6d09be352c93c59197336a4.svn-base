package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloLight;

public class NoloLightSimulazione extends NoloLight {
	
	public static final int ID = 98;
	
	private static NoloLightSimulazione instance;

	private NoloLightSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static NoloLightSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new NoloLightSimulazione(ambito);
		}
		return instance;
	}

}
