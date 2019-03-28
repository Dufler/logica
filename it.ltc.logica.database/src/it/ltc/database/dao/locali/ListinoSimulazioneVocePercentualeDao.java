package it.ltc.database.dao.locali;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVocePercentuale;

public class ListinoSimulazioneVocePercentualeDao extends CRUDDao<ListinoSimulazioneVocePercentuale> {

	public ListinoSimulazioneVocePercentualeDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ListinoSimulazioneVocePercentuale.class);
	}

	@Override
	protected void updateValues(ListinoSimulazioneVocePercentuale oldEntity, ListinoSimulazioneVocePercentuale entity) {
		oldEntity.setMassimo(entity.getMassimo());
		oldEntity.setMinimo(entity.getMinimo());
		oldEntity.setValore(entity.getValore());
	}

	public ListinoSimulazioneVocePercentuale trovaDaVoce(ListinoSimulazioneVoce voceDiListino) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ListinoSimulazioneVocePercentuale> criteria = cb.createQuery(ListinoSimulazioneVocePercentuale.class);
        Root<ListinoSimulazioneVocePercentuale> member = criteria.from(ListinoSimulazioneVocePercentuale.class);
        criteria.select(member).where(cb.equal(member.get("id"), voceDiListino.getId()));
		List<ListinoSimulazioneVocePercentuale> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		ListinoSimulazioneVocePercentuale percentuale = lista.isEmpty() ? null : lista.get(0);
		return percentuale;
	}

	public boolean aggiorna(ListinoSimulazioneVocePercentuale voceDiListinoPercentuale) {
		ListinoSimulazioneVocePercentuale entity = update(voceDiListinoPercentuale, voceDiListinoPercentuale.getId());
		boolean update = entity != null;
		return update;
	}

	public boolean inserisci(ListinoSimulazioneVocePercentuale percentuale) {
		ListinoSimulazioneVocePercentuale entity = insert(percentuale);
		boolean insert = entity != null;
		return insert;
	}

}
