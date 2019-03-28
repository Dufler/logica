package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoZoneDisagiate;

public class SupplementoZoneDisagiateCorriere extends SupplementoZoneDisagiate {
	
	public static final int ID = 56;
	
	private static SupplementoZoneDisagiateCorriere instance;

	private SupplementoZoneDisagiateCorriere(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoZoneDisagiateCorriere getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoZoneDisagiateCorriere(ambito);
		}
		return instance;
	}

}
