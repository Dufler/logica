package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.AmbitoContrassegno;

public class ContrassegnoSimulazione extends AmbitoContrassegno {
	
	public static final int ID = 79;
	
	private static ContrassegnoSimulazione instance;

	private ContrassegnoSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ContrassegnoSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ContrassegnoSimulazione(ambito);
		}
		return instance;
	}

}
