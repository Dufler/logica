package it.ltc.logica.trasporti.calcolo.ambiti.giacenza;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.ambiti.common.NoloItalia;

public class GiacenzaReso extends NoloItalia {
	
	public static final int ID = 15;
	
	private static GiacenzaReso instance;

	private GiacenzaReso(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	public static GiacenzaReso getInstance(SottoAmbitoFattura ambito) {
		if (instance == null) {
			instance = new GiacenzaReso(ambito);
		}
		return instance;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Indirizzo mittente = spedizione.getPartenza();
		Indirizzo destinatario = spedizione.getDestinatarioGiacenza();
		//Prima facevo un'equals sull'indirizzo ma è troppo stringente
		//boolean applicabile = mittente.equals(destinatario);
		boolean applicabile = mittente.quasiUguali(destinatario);
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.GIACENZA;
	}

}
