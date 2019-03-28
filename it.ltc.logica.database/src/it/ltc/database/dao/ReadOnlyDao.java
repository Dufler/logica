package it.ltc.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

/**
 * Dao per leggere dal DB.
 * @author Damiano
 *
 * @param <T> La classe entity di cui si occupa il dao.
 */
public class ReadOnlyDao<T> extends Dao {
	
	private static final Logger logger = Logger.getLogger("ReadOnlyDao");
	
	protected final Class<T> c;

	public ReadOnlyDao(String persistenceUnit, Class<T> c) {
		super(persistenceUnit);
		this.c = c;
	}
	
	/**
	 * Restituisce tutte le entity esistenti.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	protected List<T> findAll() {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        criteria.select(member);
			lista = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			logger.error(e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	/**
	 * Restituisce tutte le entity esistenti che corrispondono ai criteri specificati.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<T> findAll(List<CondizioneWhere> conditions) {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        //inizio
	        Predicate[] predicates = new Predicate[conditions.size()];
			int index = 0;
			for (CondizioneWhere condizione : conditions) {
				switch (condizione.getOperatore()) {
					case EQUAL : predicates[index] = cb.equal(member.get(condizione.getColonna()), condizione.getValore()); break;
					case LIKE : predicates[index] = cb.like(member.get(condizione.getColonna()), "%" + condizione.getValore().toString() + "%"); break;
					case START_WITH : predicates[index] = cb.like(member.get(condizione.getColonna()), condizione.getValore().toString() + "%"); break;
					case END_WITH : predicates[index] = cb.like(member.get(condizione.getColonna()), "%" + condizione.getValore().toString()); break;
					case GREATER : predicates[index] = cb.greaterThan(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
					case GREATER_OR_EQUAL : predicates[index] = cb.greaterThanOrEqualTo(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
					case LESSER : predicates[index] = cb.lessThan(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
					case LESSER_OR_EQUAL : predicates[index] = cb.lessThanOrEqualTo(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
				}
				index++;
			}
	        criteria.select(member).where(predicates);
	        //fine
//	        criteria.select(member).where(getConditions(conditions, cb, member));
			lista = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			logger.error(e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	/**
	 * Restituisce tutte le entity esistenti che corrispondono ai criteri specificati.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<T> findAll(List<CondizioneWhere> conditions, int maxResults) {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        //inizio
	        Predicate[] predicates = new Predicate[conditions.size()];
			int index = 0;
			for (CondizioneWhere condizione : conditions) {
				switch (condizione.getOperatore()) {
					case EQUAL : predicates[index] = cb.equal(member.get(condizione.getColonna()), condizione.getValore()); break;
					case LIKE : predicates[index] = cb.like(member.get(condizione.getColonna()), "%" + condizione.getValore().toString() + "%"); break;
					case START_WITH : predicates[index] = cb.like(member.get(condizione.getColonna()), condizione.getValore().toString() + "%"); break;
					case END_WITH : predicates[index] = cb.like(member.get(condizione.getColonna()), "%" + condizione.getValore().toString()); break;
					case GREATER : predicates[index] = cb.greaterThan(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
					case GREATER_OR_EQUAL : predicates[index] = cb.greaterThanOrEqualTo(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
					case LESSER : predicates[index] = cb.lessThan(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
					case LESSER_OR_EQUAL : predicates[index] = cb.lessThanOrEqualTo(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
				}
				index++;
			}
	        criteria.select(member).where(predicates);
	        //fine
			lista = em.createQuery(criteria).setMaxResults(maxResults).getResultList();
		} catch (Exception e) {
			logger.error(e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	/**
	 * Restituisce tutte le entity esistenti che hanno una certa proprietà.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	protected List<T> findAllEqualTo(String columnName, Object value) {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        criteria.select(member).where(cb.equal(member.get(columnName), value));
			lista = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			logger.error(e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	/**
	 * Restituisce la prima entity esistente che ha quella proprietà.<br>
	 * Il numero massimo di entity che verranno cercate è 1.
	 * @return una entity o <code>null</code> in caso di errori o nessuna corrispondenza.
	 */
	protected T findFirstOneEqualTo(String columnName, Object value) {
		T entity;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        criteria.select(member).where(cb.equal(member.get(columnName), value));
			List<T> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
			entity = lista.isEmpty() ? null : lista.get(0);
		} catch (Exception e) {
			logger.error(e);
			entity = null;
		} finally {
			em.close();
		}		
        return entity;
	}
	
	/**
	 * Restituisce la prima entity esistente che ha quella proprietà.<br>
	 * Il numero massimo di entity che verranno cercate è 1.
	 * @return una entity o <code>null</code> in caso di errori o nessuna corrispondenza.
	 */
	protected T findOnlyOneEqualTo(String columnName, Object value) {
		T entity;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        criteria.select(member).where(cb.equal(member.get(columnName), value));
			List<T> lista = em.createQuery(criteria).setMaxResults(2).getResultList();
			entity = lista.size() == 1 ? lista.get(0) : null;
		} catch (Exception e) {
			logger.error(e);
			entity = null;
		} finally {
			em.close();
		}		
        return entity;
	}
	
	/**
	 * Cerca la specifica entity usando la chiave passata come argomento.
	 * @param id il valore di chiave che identifica la entity desiderata.
	 * @return la entity trovata o <code>null</code> se non ci sono corrispondenze o in caso di errori.
	 */
	protected T findByID(Object id) {
		EntityManager em = getManager();
		T entity;
		try {
			entity = em.find(c, id);
		} catch (Exception e) {
			logger.error(e);
			entity = null;
		} finally {
			em.close();
		}	
		return entity;
	}

}
