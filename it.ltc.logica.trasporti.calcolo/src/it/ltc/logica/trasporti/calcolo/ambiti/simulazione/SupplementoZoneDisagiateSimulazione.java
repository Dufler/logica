package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoZoneDisagiate;

public class SupplementoZoneDisagiateSimulazione extends SupplementoZoneDisagiate {
	
	public static final int ID = 92;
	
	private static SupplementoZoneDisagiateSimulazione instance;

	private SupplementoZoneDisagiateSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoZoneDisagiateSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoZoneDisagiateSimulazione(ambito);
		}
		return instance;
	}

}
