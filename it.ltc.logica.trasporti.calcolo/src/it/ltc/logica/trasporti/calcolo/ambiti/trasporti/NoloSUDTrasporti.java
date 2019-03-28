package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloRegioniSpecifiche;

public class NoloSUDTrasporti extends NoloRegioniSpecifiche {
	
	public static final int ID = 30;

	public NoloSUDTrasporti(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

}
