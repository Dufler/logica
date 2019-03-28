package it.ltc.logica.trasporti.calcolo.ambiti.trasporti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class AnnullamentoContrassegno extends IAmbitoTrasporto {

	public static final int ID = 1;
	
	private static AnnullamentoContrassegno instance;
	
	private AnnullamentoContrassegno(SottoAmbitoFattura ambito) {
		super(ambito);
	}
	
	public static AnnullamentoContrassegno getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new AnnullamentoContrassegno(ambito);
		}
		return instance;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		boolean applicabile = false;
		if (spedizione.getContrassegno() != null)
			applicabile = spedizione.getContrassegno().getAnnullato();
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.CONTRASSEGNO;
	}
	
}
