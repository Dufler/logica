package it.ltc.database.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

public abstract class CRUDDaoConSincronizzazione<T> extends CRUDDao<T> {
	
	private static final Logger logger = Logger.getLogger("CRUDDaoConSincronizzazione");
	
	public CRUDDaoConSincronizzazione(String persistenceUnit, Class<T> c) {
		super(persistenceUnit, c);
	}

	/**
	 * Inserisce le entities passate come argomento nel db.
	 * @param entities le entities da salvare.
	 * @return Il risultato dell'operazione.
	 */
	protected synchronized boolean mergeEntities(List<T> entities) {
		boolean insert;
		if (entities != null) {
			EntityManager em = getManager();
			EntityTransaction t = em.getTransaction();
			T current = null; //debug
			try {
				t.begin();
				for (T entity : entities) {
					current = entity;
					em.merge(entity);
					aumentaProgresso();
				}
				t.commit();
				insert = true;
			} catch (Exception e) {
				logger.error(e);
				logger.info(current);
				if (t != null && t.isActive())
					t.rollback();
				insert = false;
			} finally {
				em.clear();
				em.close();
			}
		} else {
			insert = false;
		}
		return insert;
	}
	
	/**
	 * Metodo da ridefinire se si vuole usare un <code>Processo</code> o oggetti simili per mostrare l'avanzamento delle operazioni.
	 */
	protected abstract void aumentaProgresso();
	
	public Date trovaDataUltimoAggiornamento() {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Date> criteria = cb.createQuery(Date.class);
        Root<T> member = criteria.from(c);
        criteria.select(cb.greatest(member.get("dataUltimaModifica")));
        List<Date> result = em.createQuery(criteria).getResultList();
        Date max = result.isEmpty() ? null : result.get(0);
		return max;
	}

}
