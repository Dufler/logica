package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.ambiti.common.SpedizioneConMoltiColli;

public class SpedizioneConMoltiColliTrasporti extends SpedizioneConMoltiColli {
	
	public static final int ID = 19;
	
	public SpedizioneConMoltiColliTrasporti(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

}
