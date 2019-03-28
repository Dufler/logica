package it.ltc.logica.trasporti.calcolo.ambiti.corriere;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class SupplementoAltaUrbanizzazione extends IAmbitoTrasporto {
	
	public static final int ID = 101;
	
	private static SupplementoAltaUrbanizzazione instance;
	
	private final List<String> comuni;

	private SupplementoAltaUrbanizzazione(SottoAmbitoFattura ambito) {
		super(ambito);
		comuni = new LinkedList<String>();
		comuni.add("NAPOLI");
		comuni.add("MILANO");
		comuni.add("TORINO");
		comuni.add("PALERMO");
		comuni.add("PESCARA");
		comuni.add("FIRENZE");
		comuni.add("BERGAMO");
		comuni.add("BARI");
		comuni.add("BOLOGNA");
		comuni.add("GENOVA");
		comuni.add("TRIESTE");
		comuni.add("SALERNO");
		comuni.add("PADOVA");
		comuni.add("BRESCIA");
		comuni.add("ROMA");
	}

	public static SupplementoAltaUrbanizzazione getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new SupplementoAltaUrbanizzazione(ambito);
		}
		return instance;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		Indirizzo destinazione = model.getDestinazione();
		String localita = destinazione.getLocalita() != null ? destinazione.getLocalita().toUpperCase() : "";
		boolean applicabile = comuni.contains(localita);
		return applicabile;
	}

}
