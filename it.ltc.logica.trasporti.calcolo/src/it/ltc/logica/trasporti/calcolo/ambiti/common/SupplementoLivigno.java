package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class SupplementoLivigno extends IAmbitoTrasporto {
	
	public static final String CAP_LIVIGNO = "23030";
	
	protected SupplementoLivigno(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Indirizzo destinazione = spedizione.getDestinazione();
		boolean applicabile = CAP_LIVIGNO.equals(destinazione.getCap());
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

}
