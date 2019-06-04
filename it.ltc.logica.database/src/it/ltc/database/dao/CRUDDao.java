package it.ltc.database.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 * Classe per l'accesso allo strato di persistenza da estendere e parametrizzare per la specifica entity.
 * Offre i metodi CRUD per l'accesso ai dati.
 * @author Damiano
 *
 * @param <T>
 */
public abstract class CRUDDao<T> extends ReadOnlyDao<T> {
	
	private static final Logger logger = Logger.getLogger("CRUDDao");

	public CRUDDao(String persistenceUnit, Class<T> c) {
		super(persistenceUnit, c);
	}
	
	/**
	 * Inserisce la entity passata come argomento nel db.
	 * @param entity la entity da salvare.
	 * @return la entity salvata, alcuni campi potrebbero essere stati valorizzati automaticamente in seguito all'inserimento (es. ID autoincrement) oppure <code>null</code> in caso di fallimento.
	 */
	protected T insert(T entity) {
		if (entity != null) {
			EntityManager em = getManager();
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				em.persist(entity);
				t.commit();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				if (t != null && t.isActive())
					t.rollback();
				entity = null;
			} finally {
				em.close();
			}
		}
		return entity;
	}
	
	/**
	 * Inserisce le entities passate come argomento nel db.
	 * @param entities le entities da salvare.
	 * @return le entities salvate, alcuni campi potrebbero essere stati valorizzati automaticamente in seguito all'inserimento (es. ID autoincrement) oppure <code>null</code> in caso di fallimento.
	 */
	protected List<T> insert(List<T> entities) {
		if (entities != null && !entities.isEmpty()) {
			EntityManager em = getManager();
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				for (T entity : entities)
					em.persist(entity);
				t.commit();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				if (t != null && t.isActive())
					t.rollback();
				entities = null;
			} finally {
				em.close();
			}
		}
		return entities;
	}
	
	/**
	 * Restituisce il nome della tabella così come annotato tramite @Table.
	 * @return Una stringa che rappresenta il nome della tabella o <code>null</code> se non è stato definito.
	 */
	public String getTableName() {
		Table table = c.getAnnotation(Table.class);
		String tableName = table != null ? table.name() : null;
		return tableName;
	}
	
	/**
	 * Elimina tutti i dati in tabella.<br>
	 * Questo metodo sfrutta la query nativa "truncate table MyTable" dove il nome della tabella viene recuperato tramite l'annotazione <code>Table</code>.
	 * @return l'esito dell'operazione.
	 */
	public boolean truncate() {
		String tableName = getTableName();
		String truncateQuery = "delete from " + tableName;
		boolean truncate = executeNativeQuery(truncateQuery);
		return truncate;
	}
	
	/**
	 * Esegue l'aggiornamento della entity identificabile tramite il paramentro <code>key</code> con i valori contenuti nel paramentro <code>entity</code>.<br>
	 * La copia di tali valori viene eseguita tramite il metodo <method>updateValues</method> che deve essere opportunamente implementato.
	 * @param entity la entity che contiene i valori da aggiornare.
	 * @param key la chiave che identifica l'entity già presente nel db.
	 * @return la entity salvata, alcuni campi potrebbero essere stati valorizzati automaticamente in seguito all'aggiornamento (es. data_modifica con un Trigger) oppure <code>null</code> in caso di fallimento.
	 */
	protected T update(T entity, Object key) {
		if (entity != null && key != null) {
			EntityManager em = getManager();
			T oldEntity = em.find(c, key);
			//Se l'ho trovata provvedo all'aggiornamento
			if (oldEntity != null) {
				updateValues(oldEntity, entity);
				EntityTransaction t = em.getTransaction();
				try {
					t.begin();
					em.merge(oldEntity); //C'era em.merge(entity); ma secondo me era un errore!
					t.commit();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					if (t != null && t.isActive())
						t.rollback();
					entity = null;
				} finally {
					em.close();
				}
			} else {
				logger.error("Update fallito, entity non trovata tramite l'ID");
				em.close();
				entity = null;
			}
		} else {
			entity = null;
		}
		return entity;
	}
	
	/**
	 * Esegue l'aggiornamento delle entities identificabili tramite le <code>key</code> con i valori contenuti nella mappa.<br>
	 * La copia di tali valori viene eseguita tramite il metodo <method>updateValues</method> che deve essere opportunamente implementato.
	 * @param entities la mappa che contiene le entities da aggiornare.
	 * @return la entities salvate, alcuni campi potrebbero essere stati valorizzati automaticamente in seguito all'aggiornamento (es. data_modifica con un Trigger) oppure <code>null</code> in caso di fallimento.
	 */
	protected List<T> update(Map<Object, T> entities) {
		List<T> updatedEntities;
		if (entities != null && !entities.isEmpty()) {
			updatedEntities = new LinkedList<>();
			EntityManager em = getManager();
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				for (Object key : entities.keySet()) {
					T entity = entities.get(key);
					T oldEntity = em.find(c, key);
					if (oldEntity != null) {
						updateValues(oldEntity, entity);
						em.merge(oldEntity);
						updatedEntities.add(oldEntity);
					} else {
						throw new RuntimeException("Entity non trovata, ID: " + key);
					}
				}
				t.commit();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				if (t != null && t.isActive())
					t.rollback();
				updatedEntities = null;
			} finally {
				em.close();
			}
		} else {
			updatedEntities = null;
		}
		return updatedEntities;
	}

	/**
	 * Metodo da implementare per la specifica entity.
	 * Viene eseguito durante l'aggiornamento di una entity per copiarne i valori.
	 * @param oldEntity la vecchia entity recuperata dal db tramite la key.
	 * @param entity la entity che contiene i nuovi valori.
	 */
	protected abstract void updateValues(T oldEntity, T entity);
	
	/**
	 * Esegue l'eliminazione di una entity presente nel db identificabile tramite la <code>key</code> passata come argomento.
	 * @param key la chiave che identifica l'entity già presente nel db.
	 * @return la entity eliminata oppure <code>null</code> in caso di fallimento.
	 */
	protected T delete(Object key) {
		T entity;
		if (key != null) {
			EntityManager em = getManager();
			entity = em.find(c, key);
			//Se l'ho trovata provvedo all'eliminazione
			if (entity != null) {
				EntityTransaction t = em.getTransaction();
				try {
					t.begin();
					em.remove(entity);
					t.commit();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					if (t != null && t.isActive())
						t.rollback();
					entity = null;
				} finally {
					em.close();
				}
			} else {
				logger.error("Delete fallito, entity non trovata tramite l'ID");
				em.close();
			}
		} else {
			logger.error("Delete fallito, L'ID passato come argomento è null");
			entity = null;
		}
		return entity;
	}
	
	/**
	 * Esegue l'eliminazione di una entity presente nel db identificabile tramite la <code>key</code> passata come argomento.
	 * @param key la chiave che identifica l'entity già presente nel db.
	 * @return la entity eliminata oppure <code>null</code> in caso di fallimento.
	 */
	protected List<T> delete(List<Object> keys) {
		List<T> entities;
		if (keys != null && !keys.isEmpty()) {
			entities = new LinkedList<>();
			EntityManager em = getManager();
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				for (Object key : keys) {
					T entity = em.find(c, key);
					if (entity != null) {
						em.remove(entity);
						entities.add(entity);
					} else {
						throw new RuntimeException("Entity non trovata, ID: " + key);
					}
				}
				t.commit();	
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				if (t != null && t.isActive())
					t.rollback();
				entities = null;
			} finally {
				em.close();
			}
		} else {
			logger.error("Delete fallito, la lista di ID passati come argomento è null o vuota");
			entities = null;
		}
		return entities;
	}

}
