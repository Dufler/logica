package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class Fuel extends IAmbitoTrasporto {
	
	public static final int ID_TRASPORTI = 12;
	public static final int ID_CORRIERI = 59;
	public static final int ID_GIACENZE = 83;
	public static final int ID_RITIRI = 84;

	protected Fuel(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public Tipo getTipo() {
		return Tipo.FUEL;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		return true;
	}

}
