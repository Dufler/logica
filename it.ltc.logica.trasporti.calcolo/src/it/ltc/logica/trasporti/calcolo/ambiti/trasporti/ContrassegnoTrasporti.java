package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.AmbitoContrassegno;

public class ContrassegnoTrasporti extends AmbitoContrassegno {
	
	public static final int ID = 8;
	
	private static ContrassegnoTrasporti instance;

	private ContrassegnoTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ContrassegnoTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ContrassegnoTrasporti(ambito);
		}
		return instance;
	}

}
