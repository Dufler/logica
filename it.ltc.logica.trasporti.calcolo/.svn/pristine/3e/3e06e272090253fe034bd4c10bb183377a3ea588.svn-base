package it.ltc.logica.trasporti.calcolo.ambiti.giacenza;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloItalia;

public class GiacenzaRiconsegna extends NoloItalia {
	
	public static final int ID = 16;
	
	private static GiacenzaRiconsegna instance;

	private GiacenzaRiconsegna(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static GiacenzaRiconsegna getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new GiacenzaRiconsegna(ambito);
		}
		return instance;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		boolean applicabile = false;
		Indirizzo destinatarioOriginale = spedizione.getDestinazione();
		Indirizzo destinatarioGiacenza = spedizione.getDestinatarioGiacenza();
		if (destinatarioOriginale != null && destinatarioGiacenza != null)
			applicabile = destinatarioOriginale.quasiUguali(destinatarioGiacenza);
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.GIACENZA;
	}

}
