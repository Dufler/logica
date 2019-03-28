package it.ltc.database.dao.locali;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.centrale.trasporti.DocumentoSpedizioniSimulazione;

public class DocumentoSpedizioniSimulazioneDao extends CRUDDao<DocumentoSpedizioniSimulazione> {

	public DocumentoSpedizioniSimulazioneDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, DocumentoSpedizioniSimulazione.class);
	}
	
	public DocumentoSpedizioniSimulazione inserisci(DocumentoSpedizioniSimulazione documento) {
		DocumentoSpedizioniSimulazione entity = insert(documento);
		return entity;
	}
	
	public DocumentoSpedizioniSimulazione aggiorna(DocumentoSpedizioniSimulazione documento) {
		DocumentoSpedizioniSimulazione entity = update(documento, documento.getId());
		return entity;
	}
	
	public DocumentoSpedizioniSimulazione elimina(DocumentoSpedizioniSimulazione documento) {
		DocumentoSpedizioniSimulazione entity = delete(documento.getId());
		return entity;
	}
	
	public List<DocumentoSpedizioniSimulazione> trovaTutti() {
		return findAll();
	}
	
	public DocumentoSpedizioniSimulazione trovaDaID(int id) {
		DocumentoSpedizioniSimulazione entity = findByID(id);
		return entity;
	}

	@Override
	protected void updateValues(DocumentoSpedizioniSimulazione oldEntity, DocumentoSpedizioniSimulazione entity) {
		oldEntity.setDataImportazione(entity.getDataImportazione());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setNomeFile(entity.getNomeFile());
		oldEntity.setTipo(entity.getTipo());
	}

}
