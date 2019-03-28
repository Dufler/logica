package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaAiPrivati;

public class ConsegnaPrivatiTrasporti extends ConsegnaAiPrivati {
	
	public static final int ID = 3;
	
	private static ConsegnaPrivatiTrasporti instance;

	private ConsegnaPrivatiTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ConsegnaPrivatiTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ConsegnaPrivatiTrasporti(ambito);
		}
		return instance;
	}

}
