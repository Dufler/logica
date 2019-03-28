package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class DirittoFisso extends IAmbitoTrasporto {

	public DirittoFisso(SottoAmbitoFattura ambito) {
		super(ambito);
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.EXTRA;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		return true;
	}

}
