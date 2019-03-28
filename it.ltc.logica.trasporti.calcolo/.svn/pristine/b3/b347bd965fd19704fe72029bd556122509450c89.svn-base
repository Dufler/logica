package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class SupplementoCampioneItalia extends IAmbitoTrasporto {
	
	public static final String CAP_CAMPIONE = "22060";
	public static final String LOCALITA_CAMPIONE = "CAMPIONE";
	
	public SupplementoCampioneItalia(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Indirizzo destinazione = spedizione.getDestinazione();
		boolean cap = CAP_CAMPIONE.equalsIgnoreCase(destinazione.getCap());
		boolean localita = destinazione.getLocalita().toUpperCase().contains(LOCALITA_CAMPIONE);
		boolean applicabile = cap && localita;
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

}
