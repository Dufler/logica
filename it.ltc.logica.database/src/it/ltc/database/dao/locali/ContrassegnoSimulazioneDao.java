package it.ltc.database.dao.locali;

import java.util.Collection;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.centrale.trasporti.ContrassegnoSimulazione;

public class ContrassegnoSimulazioneDao extends CRUDDao<ContrassegnoSimulazione> {

	public ContrassegnoSimulazioneDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ContrassegnoSimulazione.class);
	}
	
	public Collection<ContrassegnoSimulazione> trovaTutte() {
		List<ContrassegnoSimulazione> entities = findAll();
		return entities;
	}

	public ContrassegnoSimulazione trovaDaID(int id) {
		ContrassegnoSimulazione entity = findByID(id);
		return entity;
	}
	
	public ContrassegnoSimulazione inserisci(ContrassegnoSimulazione contrassegno) {
		ContrassegnoSimulazione entity = insert(contrassegno);
		return entity;
	}
	
	public ContrassegnoSimulazione aggiorna(ContrassegnoSimulazione contrassegno) {
		ContrassegnoSimulazione entity = update(contrassegno, contrassegno.getIdSpedizione());
		return entity;
	}
	
	public ContrassegnoSimulazione elimina(ContrassegnoSimulazione contrassegno) {
		ContrassegnoSimulazione entity = delete(contrassegno.getIdSpedizione());
		return entity;
	}

	@Override
	protected void updateValues(ContrassegnoSimulazione oldEntity, ContrassegnoSimulazione entity) {
		oldEntity.setAnnullato(entity.getAnnullato());
		oldEntity.setValore(entity.getValore());
		oldEntity.setValuta(entity.getValuta());
		oldEntity.setTipo(entity.getTipo());		
	}	

}
