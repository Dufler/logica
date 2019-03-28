package it.ltc.logica.trasporti.calcolo.ambiti.simulazione;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoIsoleMinori;

public class SupplementoIsoleSimulazione extends SupplementoIsoleMinori {
	
	public static final int ID = 93;
	
	private static SupplementoIsoleSimulazione instance;

	private SupplementoIsoleSimulazione(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoIsoleSimulazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoIsoleSimulazione(ambito);
		}
		return instance;
	}

}
