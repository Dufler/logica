package it.ltc.logica.common.controller.processi.specifici;

import java.util.ArrayList;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.dao.SpedizioneLocaleDao;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.task.Processo;

public class ProcessoSelezioneSpedizioni extends Processo {

	private static final String title = "Filtraggio delle spedizioni";
	
	private final SpedizioneLocaleDao dao;
	private final List<Spedizione> spedizioni;
	private final CriteriSelezioneSpedizioni criteri;
	
	public ProcessoSelezioneSpedizioni(SpedizioneLocaleDao dao, CriteriSelezioneSpedizioni criteri) {
		super(title, -1);
		this.dao = dao;
		this.criteri = criteri;
		this.spedizioni = new ArrayList<Spedizione>();
	}
	
	public List<Spedizione> getLista() {
		return spedizioni;
	}
	
	@Override
	public void eseguiOperazioni() throws Exception {
		List<Spedizione> corrispondenze = dao.trovaCorrispondenti(criteri);
		//elimino i risultati non idonei, se richiesto.
		for (Spedizione spedizione : corrispondenze) {
			boolean aggiungi = true;
			if (criteri.isPesoNecessario()) {
				Double peso = spedizione.getPeso();
				int colli = spedizione.getColli() > 0 ? spedizione.getColli() : 1;
				aggiungi = peso != null && peso > 0.1 * colli && peso <= 35 * colli;
			}
			if (aggiungi && criteri.isVolumeNecessario()) {
				Double volume = spedizione.getVolume();
				int colli = spedizione.getColli() > 0 ? spedizione.getColli() : 1;
				aggiungi = volume != null && volume > 0.001 * colli && volume <= 0.25 * colli;
			}
			if (aggiungi && criteri.isPezziNecessari()) {
				Integer pezzi = spedizione.getPezzi();
				aggiungi = pezzi != null && pezzi > 0;
			}
			if (aggiungi && criteri.isColliNecessari()) {
				Integer colli = spedizione.getColli();
				aggiungi = colli != null && colli > 0;
			}
			if (aggiungi)
				spedizioni.add(spedizione);
		}
	}

}
