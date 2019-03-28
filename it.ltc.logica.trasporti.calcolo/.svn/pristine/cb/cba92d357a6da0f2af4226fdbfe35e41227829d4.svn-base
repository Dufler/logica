package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaAGDO;

public class ConsegnaGDOTrasporti extends ConsegnaAGDO {
	
	public static final int ID = 4;
	
	private static ConsegnaGDOTrasporti instance;

	private ConsegnaGDOTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ConsegnaGDOTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ConsegnaGDOTrasporti(ambito);
		}
		return instance;
	}

}
