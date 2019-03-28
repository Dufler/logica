package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class ProvaDiConsegna extends IAmbitoTrasporto {
	
	public static final int ID = 32;
	
	private static ProvaDiConsegna instance;
	
	private ProvaDiConsegna(SottoAmbitoFattura ambito) {
		super(ambito);
	}
	
	public static ProvaDiConsegna getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new ProvaDiConsegna(ambito);
		}
		return instance;
	}
	
	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		//Va sempre inserito manualmente
		return false;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.EXTRA;
	}

}
