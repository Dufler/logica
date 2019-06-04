package it.ltc.database.dao.locali;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ProprietaLogica;

public class ProprietaLogicaDao extends CRUDDao<ProprietaLogica> {

	public ProprietaLogicaDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ProprietaLogica.class);
	}
	
	public ProprietaLogica trovaDaChiave(String key) {
		ProprietaLogica entity = findByID(key);
		return entity;
	}
	
	public List<ProprietaLogica> trovaTutte() {
		List<ProprietaLogica> entities = findAll();
		return entities;
	}
	
	public ProprietaLogica inserisci(ProprietaLogica proprieta) {
		ProprietaLogica entity = insert(proprieta);
		return entity;
	}
	
	public ProprietaLogica aggiorna(ProprietaLogica proprieta) {
		ProprietaLogica entity = update(proprieta, proprieta.getKey());
		return entity;
	}
	
	public ProprietaLogica elimina(ProprietaLogica proprieta) {
		ProprietaLogica entity = delete(proprieta.getKey());
		return entity;
	}

	@Override
	protected void updateValues(ProprietaLogica oldEntity, ProprietaLogica entity) {
		oldEntity.setValue(entity.getValue());		
	}

}
