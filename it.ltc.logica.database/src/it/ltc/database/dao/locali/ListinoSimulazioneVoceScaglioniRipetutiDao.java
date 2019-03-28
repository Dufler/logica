package it.ltc.database.dao.locali;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceScaglioniRipetuti;

public class ListinoSimulazioneVoceScaglioniRipetutiDao extends CRUDDao<ListinoSimulazioneVoceScaglioniRipetuti> {

	public ListinoSimulazioneVoceScaglioniRipetutiDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ListinoSimulazioneVoceScaglioniRipetuti.class);
	}

	@Override
	protected void updateValues(ListinoSimulazioneVoceScaglioniRipetuti oldEntity, ListinoSimulazioneVoceScaglioniRipetuti entity) {
		oldEntity.setIntervallo(entity.getIntervallo());
		oldEntity.setMassimo(entity.getMassimo());
		oldEntity.setMinimo(entity.getMinimo());
		oldEntity.setValore(entity.getValore());
	}

	public ListinoSimulazioneVoceScaglioniRipetuti trovaDaVoce(ListinoSimulazioneVoce voceDiListino) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ListinoSimulazioneVoceScaglioniRipetuti> criteria = cb.createQuery(ListinoSimulazioneVoceScaglioniRipetuti.class);
        Root<ListinoSimulazioneVoceScaglioniRipetuti> member = criteria.from(ListinoSimulazioneVoceScaglioniRipetuti.class);
        criteria.select(member).where(cb.equal(member.get("id"), voceDiListino.getId()));
		List<ListinoSimulazioneVoceScaglioniRipetuti> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		ListinoSimulazioneVoceScaglioniRipetuti ripetuti = lista.isEmpty() ? null : lista.get(0);
		return ripetuti;
	}

	public boolean aggiorna(ListinoSimulazioneVoceScaglioniRipetuti voceDiListinoScaglioniRipetuti) {
		ListinoSimulazioneVoceScaglioniRipetuti entity = update(voceDiListinoScaglioniRipetuti, voceDiListinoScaglioniRipetuti.getId());
		boolean update = entity != null;
		return update;
	}

	public boolean inserisci(ListinoSimulazioneVoceScaglioniRipetuti ripetuti) {
		ListinoSimulazioneVoceScaglioniRipetuti entity = insert(ripetuti);
		boolean insert = entity != null;
		return insert;
	}

}
