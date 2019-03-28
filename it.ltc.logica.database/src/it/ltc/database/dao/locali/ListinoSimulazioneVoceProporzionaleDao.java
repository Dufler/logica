package it.ltc.database.dao.locali;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceProporzionale;

public class ListinoSimulazioneVoceProporzionaleDao extends CRUDDao<ListinoSimulazioneVoceProporzionale> {

	public ListinoSimulazioneVoceProporzionaleDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ListinoSimulazioneVoceProporzionale.class);
	}

	@Override
	protected void updateValues(ListinoSimulazioneVoceProporzionale oldEntity, ListinoSimulazioneVoceProporzionale entity) {
		oldEntity.setMassimo(entity.getMassimo());
		oldEntity.setMinimo(entity.getMinimo());
		oldEntity.setValore(entity.getValore());
	}

	public ListinoSimulazioneVoceProporzionale trovaDaVoce(ListinoSimulazioneVoce voceDiListino) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ListinoSimulazioneVoceProporzionale> criteria = cb.createQuery(ListinoSimulazioneVoceProporzionale.class);
        Root<ListinoSimulazioneVoceProporzionale> member = criteria.from(ListinoSimulazioneVoceProporzionale.class);
        criteria.select(member).where(cb.equal(member.get("id"), voceDiListino.getId()));
		List<ListinoSimulazioneVoceProporzionale> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		ListinoSimulazioneVoceProporzionale proporzionale = lista.isEmpty() ? null : lista.get(0);
		return proporzionale;
	}

	public boolean aggiorna(ListinoSimulazioneVoceProporzionale voceDiListinoProporzionale) {
		ListinoSimulazioneVoceProporzionale entity = update(voceDiListinoProporzionale, voceDiListinoProporzionale.getId());
		boolean update = entity != null;
		return update;
	}

	public boolean inserisci(ListinoSimulazioneVoceProporzionale proporzionale) {
		ListinoSimulazioneVoceProporzionale entity = insert(proporzionale);
		boolean insert = entity != null;
		return insert;
	}

}
