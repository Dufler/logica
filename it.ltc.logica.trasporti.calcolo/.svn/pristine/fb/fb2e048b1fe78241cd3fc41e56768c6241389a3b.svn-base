package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SupplementoIsoleVenezia;

public class SupplementoIsoleVeneziaTrasporti extends SupplementoIsoleVenezia {

	public static final int ID = 26;
	
	private static SupplementoIsoleVeneziaTrasporti instance;

	private SupplementoIsoleVeneziaTrasporti(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static SupplementoIsoleVeneziaTrasporti getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoIsoleVeneziaTrasporti(ambito);
		}
		return instance;
	}
	
}
