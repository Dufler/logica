package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoIsoleMinori;

public class SupplementoIsoleMinoriTrasporti extends SupplementoIsoleMinori {
	
	public static final int ID = 25;
	
	private static SupplementoIsoleMinoriTrasporti instance;

	private SupplementoIsoleMinoriTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoIsoleMinoriTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoIsoleMinoriTrasporti(ambito);
		}
		return instance;
	}

}
