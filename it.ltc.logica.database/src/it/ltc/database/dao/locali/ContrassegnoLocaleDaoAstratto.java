package it.ltc.database.dao.locali;

import java.util.Collection;
import java.util.List;

import it.ltc.database.dao.CRUDDaoConSincronizzazione;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;

public abstract class ContrassegnoLocaleDaoAstratto extends CRUDDaoConSincronizzazione<Contrassegno> {

	public ContrassegnoLocaleDaoAstratto() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, Contrassegno.class);
	}
	
	public Collection<Contrassegno> trovaTutte() {
		List<Contrassegno> entities = findAll();
		return entities;
	}

	public Contrassegno trovaDaID(int id) {
		Contrassegno entity = findByID(id);
		return entity;
	}
	
	public Contrassegno inserisci(Contrassegno contrassegno) {
		Contrassegno entity = insert(contrassegno);
		return entity;
	}
	
	public Contrassegno aggiorna(Contrassegno contrassegno) {
		Contrassegno entity = update(contrassegno, contrassegno.getIdSpedizione());
		return entity;
	}
	
	public Contrassegno elimina(Contrassegno contrassegno) {
		Contrassegno entity = delete(contrassegno.getIdSpedizione());
		return entity;
	}

	@Override
	protected void updateValues(Contrassegno oldEntity, Contrassegno entity) {
		oldEntity.setAnnullato(entity.getAnnullato());
		oldEntity.setDataUltimaModifica(entity.getDataUltimaModifica());
		oldEntity.setValore(entity.getValore());
		oldEntity.setValuta(entity.getValuta());
		oldEntity.setTipo(entity.getTipo());
	}

}
