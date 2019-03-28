package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoZoneDisagiate;

public class SupplementoZoneDisagiateTrasporti extends SupplementoZoneDisagiate {
	
	public static final int ID = 22;
	
	private static SupplementoZoneDisagiateTrasporti instance;

	private SupplementoZoneDisagiateTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoZoneDisagiateTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoZoneDisagiateTrasporti(ambito);
		}
		return instance;
	}

}
