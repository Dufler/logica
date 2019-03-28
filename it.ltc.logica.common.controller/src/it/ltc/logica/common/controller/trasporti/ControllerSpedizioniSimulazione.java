package it.ltc.logica.common.controller.trasporti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.database.dao.locali.SpedizioneSimulazioneDao;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.Fatturazione;
import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.gui.task.Processo;

public class ControllerSpedizioniSimulazione {
	
	private static ControllerSpedizioniSimulazione instance;
	
	protected final SpedizioneSimulazioneDao dao;

	private ControllerSpedizioniSimulazione() {
		dao = new SpedizioneSimulazioneDao();
	}

	public static synchronized ControllerSpedizioniSimulazione getInstance() {
		if (null == instance) {
			instance = new ControllerSpedizioniSimulazione();
		}
		return instance;
	}
	
	public boolean inserisci(SpedizioneSimulazione spedizione) {
		SpedizioneSimulazione entity = dao.inserisci(spedizione);
		boolean result = entity != null;
		if (result) {
			spedizione.setId(entity.getId());
		}
		return result;
	}
	
	public boolean aggiorna(SpedizioneSimulazione spedizione) {
		SpedizioneSimulazione entity = dao.aggiorna(spedizione);
		boolean result = entity != null;
		return result;
	}
	
	public boolean elimina(SpedizioneSimulazione spedizione) {
		SpedizioneSimulazione entity = dao.elimina(spedizione);
		boolean result = entity != null;
		return result;
	}
	
	public List<Spedizione> selezionaSpedizioni(CriteriSelezioneSpedizioni criteri) {
		List<Spedizione> spedizioni = new LinkedList<>();
		List<SpedizioneSimulazione> spedizioniSimulazione = selezionaSpedizioniSimulazione(criteri);
		for (SpedizioneSimulazione simulazione : spedizioniSimulazione) {
			Spedizione spedizione = convertiOggetto(simulazione);
			spedizioni.add(spedizione);
		}
		return spedizioni;
	}
	
	public List<SpedizioneSimulazione> selezionaSpedizioniSimulazione(CriteriSelezioneSpedizioni criteri) {
		ProcessoSelezioneSpedizioni processo = new ProcessoSelezioneSpedizioni(dao, criteri);
		processo.eseguiOperazioniSenzaProgresso();
		return processo.getLista();
	}
	
	public Spedizione convertiOggetto(SpedizioneSimulazione entity) {
		Spedizione spedizione = new Spedizione();
		spedizione.setAssicurazione(entity.getAssicurazione());
		spedizione.setCodiceCliente(entity.getCodiceCliente());
		spedizione.setColli(entity.getColli());
		spedizione.setContrassegno(entity.getContrassegno());
		spedizione.setCosto(entity.getCosto());
		spedizione.setDataPartenza(entity.getDataPartenza());
		spedizione.setDatiCompleti(entity.getDatiCompleti());
		spedizione.setFatturazione(Fatturazione.NON_FATTURABILE);
		spedizione.setGiacenza(entity.getGiacenza());
		spedizione.setIdCommessa(entity.getIdCommessa());
		spedizione.setIdCorriere(entity.getIdCorriere());
		spedizione.setIdDocumento(entity.getIdDocumento());
		spedizione.setIndirizzoDestinazione(entity.getIndirizzoDestinazione());
		spedizione.setIndirizzoPartenza(entity.getIndirizzoPartenza());
		spedizione.setInRitardo(entity.getInRitardo());
		spedizione.setLetteraDiVettura(entity.getLetteraDiVettura());
		spedizione.setNote(entity.getNote());
		spedizione.setParticolarita(entity.getParticolarita());
		spedizione.setPeso(entity.getPeso());
		spedizione.setPezzi(entity.getPezzi());
		spedizione.setRagioneSocialeDestinatario(entity.getRagioneSocialeDestinatario());
		spedizione.setRicavo(entity.getRicavo());
		spedizione.setRiferimentoCliente(entity.getRiferimentoCliente());
		spedizione.setRiferimentoMittente(entity.getRiferimentoMittente());
		spedizione.setServizio(entity.getServizio());
		spedizione.setStato(entity.getStato());
		spedizione.setTipo(entity.getTipo());
		spedizione.setValoreMerceDichiarato(entity.getValoreMerceDichiarato());
		spedizione.setVolume(entity.getVolume());
		return spedizione;
	}
	
	private class ProcessoSelezioneSpedizioni extends Processo {

		private static final String title = "Filtraggio delle spedizioni";
		
		private final SpedizioneSimulazioneDao dao;
		private final List<SpedizioneSimulazione> spedizioni;
		private final CriteriSelezioneSpedizioni criteri;
		
		public ProcessoSelezioneSpedizioni(SpedizioneSimulazioneDao dao, CriteriSelezioneSpedizioni criteri) {
			super(title, -1);
			this.dao = dao;
			this.criteri = criteri;
			this.spedizioni = new ArrayList<>();
		}
		
		public List<SpedizioneSimulazione> getLista() {
			return spedizioni;
		}
		
		@Override
		public void eseguiOperazioni() throws Exception {
			List<SpedizioneSimulazione> corrispondenze = dao.trovaCorrispondenti(criteri);
			//elimino i risultati non idonei, se richiesto.
			for (SpedizioneSimulazione spedizione : corrispondenze) {
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

}
