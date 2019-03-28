package it.ltc.database.dao.locali;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceScaglioni;

public class ListinoSimulazioneVoceScaglioniDao extends CRUDDao<ListinoSimulazioneVoceScaglioni> {

	public ListinoSimulazioneVoceScaglioniDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ListinoSimulazioneVoceScaglioni.class);
	}

	@Override
	protected void updateValues(ListinoSimulazioneVoceScaglioni oldEntity, ListinoSimulazioneVoceScaglioni entity) {
		oldEntity.setFine(entity.getFine());
		oldEntity.setInizio(entity.getInizio());
		oldEntity.setValore(entity.getValore());
	}

	public List<ListinoSimulazioneVoceScaglioni> trovaDaVoce(ListinoSimulazioneVoce voceDiListino) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ListinoSimulazioneVoceScaglioni> criteria = cb.createQuery(ListinoSimulazioneVoceScaglioni.class);
        Root<ListinoSimulazioneVoceScaglioni> member = criteria.from(ListinoSimulazioneVoceScaglioni.class);
        criteria.select(member).where(cb.equal(member.get("id"), voceDiListino.getId()));
		List<ListinoSimulazioneVoceScaglioni> lista = em.createQuery(criteria).getResultList();
		em.close();
		return lista;
	}

	public boolean inserisci(ListinoSimulazioneVoceScaglioni scaglione) {
		ListinoSimulazioneVoceScaglioni entity = insert(scaglione);
		boolean insert = entity != null;
		return insert;
	}

	public void eliminaDaID(int id) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ListinoSimulazioneVoceScaglioni> criteria = cb.createQuery(ListinoSimulazioneVoceScaglioni.class);
        Root<ListinoSimulazioneVoceScaglioni> member = criteria.from(ListinoSimulazioneVoceScaglioni.class);
        criteria.select(member).where(cb.equal(member.get("id"), id));
		List<ListinoSimulazioneVoceScaglioni> lista = em.createQuery(criteria).getResultList();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for (ListinoSimulazioneVoceScaglioni scaglione : lista) {
			em.remove(scaglione);
		}
		transaction.commit();
		em.close();
	}

}
