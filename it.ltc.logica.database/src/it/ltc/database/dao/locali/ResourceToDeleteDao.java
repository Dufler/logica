package it.ltc.database.dao.locali;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ResourceToDelete;

public class ResourceToDeleteDao extends CRUDDao<ResourceToDelete> {

	public ResourceToDeleteDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ResourceToDelete.class);
	}
	
	public ResourceToDelete inserisci(ResourceToDelete risorsa) {
		ResourceToDelete entity = insert(risorsa);
		return entity;
	}

	@Override
	protected void updateValues(ResourceToDelete oldEntity, ResourceToDelete entity) {
		oldEntity.setName(entity.getName());		
	}

}
