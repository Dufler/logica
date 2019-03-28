package it.ltc.logica.trasporti.calcolo.ambiti.giacenza;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloRegioniSpecifiche;

public class GiacenzaResoRegioniSpecifiche extends NoloRegioniSpecifiche {
	
	public static final int ID = 99;
	
	public GiacenzaResoRegioniSpecifiche(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		boolean applicabile = super.isApplicabile(spedizione);
		if (applicabile) {
			Indirizzo mittente = spedizione.getPartenza();
			Indirizzo destinatario = spedizione.getDestinatarioGiacenza();
			//Prima facevo un'equals sull'indirizzo ma è troppo stringente
			//boolean applicabile = mittente.equals(destinatario);
			applicabile = mittente.quasiUguali(destinatario);
		}
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.GIACENZA;
	}

}
