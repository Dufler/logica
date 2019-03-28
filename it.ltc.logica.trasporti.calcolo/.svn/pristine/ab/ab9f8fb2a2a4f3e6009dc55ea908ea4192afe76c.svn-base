package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.ConsegnaAlPiano;

public class ConsegnaPianoTrasporti extends ConsegnaAlPiano {
	
	public static final int ID = 2;
	
	private static ConsegnaPianoTrasporti instance;

	private ConsegnaPianoTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static ConsegnaPianoTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ConsegnaPianoTrasporti(ambito);
		}
		return instance;
	}

}
