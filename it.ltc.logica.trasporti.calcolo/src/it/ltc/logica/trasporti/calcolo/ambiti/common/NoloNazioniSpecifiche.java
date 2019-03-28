package it.ltc.logica.trasporti.calcolo.ambiti.common;

import java.util.HashSet;
import java.util.Set;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public abstract class NoloNazioniSpecifiche extends NoloItalia {
	
	private final Set<String> nazioni;

	public NoloNazioniSpecifiche(SottoAmbitoFattura ambito, String valore) {
		super(ambito);
		nazioni = getNazioni(valore);
	}

	private Set<String> getNazioni(String valore) {
		Set<String> nazioniSelezionate = new HashSet<String>();
		String[] valori = valore.split("-");
		for (String regione : valori) {
			nazioniSelezionate.add(regione);
		}
		return nazioniSelezionate;
	}
	
	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		Indirizzo destinazione = model.getDestinazione();
		String nazione = destinazione.getNazione();
		boolean applicabile = nazioni.contains(nazione);
		return applicabile;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_BASE;
	}

}
