package it.ltc.logica.trasporti.calcolo.ambiti.giacenza;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class GiacenzaApertura extends IAmbitoTrasporto {
	
	public static final int ID = 14;
	
	private static GiacenzaApertura instance;

	private GiacenzaApertura(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static GiacenzaApertura getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new GiacenzaApertura(ambito);
		}
		return instance;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		return true;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.GIACENZA;
	}

}
