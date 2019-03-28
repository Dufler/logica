package it.ltc.database.dao.locali;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceFissa;

public class ListinoSimulazioneVoceFissaDao extends CRUDDao<ListinoSimulazioneVoceFissa> {

	public ListinoSimulazioneVoceFissaDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ListinoSimulazioneVoceFissa.class);
	}

	@Override
	protected void updateValues(ListinoSimulazioneVoceFissa oldEntity, ListinoSimulazioneVoceFissa entity) {
		oldEntity.setValore(entity.getValore());		
	}

	public ListinoSimulazioneVoceFissa trovaDaVoce(ListinoSimulazioneVoce voceDiListino) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ListinoSimulazioneVoceFissa> criteria = cb.createQuery(ListinoSimulazioneVoceFissa.class);
        Root<ListinoSimulazioneVoceFissa> member = criteria.from(ListinoSimulazioneVoceFissa.class);
        criteria.select(member).where(cb.equal(member.get("id"), voceDiListino.getId()));
		List<ListinoSimulazioneVoceFissa> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		ListinoSimulazioneVoceFissa fissa = lista.isEmpty() ? null : lista.get(0);
		return fissa;
	}

	public boolean aggiorna(ListinoSimulazioneVoceFissa voceDiListinoFissa) {
		ListinoSimulazioneVoceFissa entity = update(voceDiListinoFissa, voceDiListinoFissa.getId());
		boolean update = entity != null;
		return update;
	}

	public boolean inserisci(ListinoSimulazioneVoceFissa fissa) {
		ListinoSimulazioneVoceFissa entity = insert(fissa);
		boolean insert = entity != null;
		return insert;
	}

}
