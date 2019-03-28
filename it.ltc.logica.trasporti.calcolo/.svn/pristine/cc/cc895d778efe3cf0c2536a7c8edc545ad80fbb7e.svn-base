package it.ltc.logica.trasporti.calcolo.ambiti.giacenza;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloRegioniSpecifiche;

public class GiacenzaRiconsegnaRegioneSpecifica extends NoloRegioniSpecifiche {

	public static final int ID = 65;

	public GiacenzaRiconsegnaRegioneSpecifica(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		boolean applicabile = super.isApplicabile(spedizione);
		if (applicabile) {
			Indirizzo destinatarioOriginale = spedizione.getDestinazione();
			Indirizzo destinatarioGiacenza = spedizione.getDestinatarioGiacenza();
			if (destinatarioOriginale != null && destinatarioGiacenza != null) {
				applicabile = destinatarioOriginale.quasiUguali(destinatarioGiacenza);
			} else {
				applicabile = false;
			}
		}
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.GIACENZA;
	}
	
}
