package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;

import it.ltc.database.dao.locali.ContrassegnoSimulazioneDao;
import it.ltc.logica.database.model.centrale.trasporti.ContrassegnoSimulazione;

public class ControllerContrassegniSimulazione {
	
	private static ControllerContrassegniSimulazione instance;
	
	private final ContrassegnoSimulazioneDao dao;

	private ControllerContrassegniSimulazione() {
		dao = new ContrassegnoSimulazioneDao();
	}

	public static synchronized ControllerContrassegniSimulazione getInstance() {
		if (null == instance) {
			instance = new ControllerContrassegniSimulazione();
		}
		return instance;
	}
	
	public Collection<ContrassegnoSimulazione> trovaTutti() {
		Collection<ContrassegnoSimulazione> contrassegni = dao.trovaTutte();
		return contrassegni;
	}

	public ContrassegnoSimulazione getContrassegno(int id) {
		ContrassegnoSimulazione contrassegno = dao.trovaDaID(id);
		return contrassegno;
	}

	public boolean inserisci(ContrassegnoSimulazione cs) {
		ContrassegnoSimulazione entity = dao.inserisci(cs);
		return entity != null;
	}
	
	public boolean aggiorna(ContrassegnoSimulazione cs) {
		ContrassegnoSimulazione entity = dao.aggiorna(cs);
		return entity != null;
	}
	
	public boolean elimina(ContrassegnoSimulazione cs) {
		ContrassegnoSimulazione entity = dao.aggiorna(cs);
		return entity != null;
	}

}
