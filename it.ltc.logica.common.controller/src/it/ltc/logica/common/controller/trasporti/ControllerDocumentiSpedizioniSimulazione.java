package it.ltc.logica.common.controller.trasporti;

import java.util.List;

import it.ltc.database.dao.locali.DocumentoSpedizioniSimulazioneDao;
import it.ltc.logica.database.model.centrale.trasporti.DocumentoSpedizioniSimulazione;

public class ControllerDocumentiSpedizioniSimulazione {
	
	private static ControllerDocumentiSpedizioniSimulazione instance;
	
	private final DocumentoSpedizioniSimulazioneDao dao;

	private ControllerDocumentiSpedizioniSimulazione() {
		dao = new DocumentoSpedizioniSimulazioneDao();
	}

	public static synchronized ControllerDocumentiSpedizioniSimulazione getInstance() {
		if (null == instance) {
			instance = new ControllerDocumentiSpedizioniSimulazione();
		}
		return instance;
	}
	
	public List<DocumentoSpedizioniSimulazione> trovaTutti() {
		return dao.trovaTutti();
	}
	
	public DocumentoSpedizioniSimulazione trovaDaID(int id) {
		return dao.trovaDaID(id);
	}
	
	public boolean inserisci(DocumentoSpedizioniSimulazione documento) {
		DocumentoSpedizioniSimulazione entity = dao.inserisci(documento);
		boolean insert = entity != null;
		if (insert) {
			documento.setId(entity.getId());
		}
		return insert;
	}
	
	public boolean aggiorna(DocumentoSpedizioniSimulazione documento) {
		DocumentoSpedizioniSimulazione entity = dao.aggiorna(documento);
		return entity != null;
	}
	
	public boolean elimina(DocumentoSpedizioniSimulazione documento) {
		DocumentoSpedizioniSimulazione entity = dao.elimina(documento);
		return entity != null;
	}

}
