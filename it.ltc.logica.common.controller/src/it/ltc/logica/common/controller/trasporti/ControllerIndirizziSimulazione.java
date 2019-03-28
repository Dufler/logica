package it.ltc.logica.common.controller.trasporti;

import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneIndirizzi;
import it.ltc.database.dao.locali.IndirizzoSimulazioneDao;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;

public class ControllerIndirizziSimulazione {
	
	private static ControllerIndirizziSimulazione instance;
	
	private final IndirizzoSimulazioneDao dao;

	private ControllerIndirizziSimulazione() {
		dao = new IndirizzoSimulazioneDao();
	}

	public static synchronized ControllerIndirizziSimulazione getInstance() {
		if (null == instance) {
			instance = new ControllerIndirizziSimulazione();
		}
		return instance;
	}
	
	public List<IndirizzoSimulazione> trovaTutti() {
		List<IndirizzoSimulazione> indirizzi = dao.trovaTutti();
		return indirizzi;
	}
	
	public List<IndirizzoSimulazione> trovaTuttiInserimentoManuale() {
		List<IndirizzoSimulazione> indirizzi = dao.trovaTuttiDaInserimentoManuale();
		return indirizzi;
	}
	
	public boolean salva(IndirizzoSimulazione indirizzo) {
		IndirizzoSimulazione entity = dao.salvaIndirizzo(indirizzo); 
		boolean insert = entity != null;
		return insert;
	}
	
	public boolean elimina(IndirizzoSimulazione indirizzo) {
		IndirizzoSimulazione entity = dao.elimina(indirizzo); 
		boolean insert = entity != null;
		return insert;
	}

	public List<IndirizzoSimulazione> selezionaIndirizzi(CriteriSelezioneIndirizzi criteriSelezione) {
		List<IndirizzoSimulazione> selezionati = criteriSelezione != null ? dao.trovaCorrispondenti(criteriSelezione) : dao.trovaTutti();
		return selezionati;
	}
	
	public IndirizzoSimulazione getIndirizzo(int idDestinatario) {
		IndirizzoSimulazione entity = dao.trovaDaID(idDestinatario);
		return entity;
	}

}
