package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoLivigno;

public class SupplementoLivignoSimulazione extends SupplementoLivigno {
	
	public static final int ID = 96;
	
	private static SupplementoLivignoSimulazione instance;

	private SupplementoLivignoSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoLivignoSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoLivignoSimulazione(ambito);
		}
		return instance;
	}

}
