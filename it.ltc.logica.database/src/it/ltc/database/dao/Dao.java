package it.ltc.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * Classe che consente l'accesso ai dati presenti nel db.
 * @author Damiano
 *
 */
public abstract class Dao {
	
	private static final Logger logger = Logger.getLogger("Dao");
	
	public static final String LOCAL_PERSISTENCE_UNIT_NAME = "locale";
	
	protected final String persistenceUnit;
	
	/**
	 * Costruttore di default.<br>
	 * La persistence unit passata come argomento fornisce indicazioni su quale db andare ad utilizzare.
	 * @param persistenceUnit identifica il nome della Persistence Unit così come definita nel file persistence.xml
	 */
	public Dao(String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}
	
	/**
	 * Restituisce un oggetto <code>EntityManager</code> da utilizzare per eseguire l'accesso ai dati.<br>
	 * E' molto importante chiudere tramite il metodo <code>close</code> l'oggetto una volta concluso l'utilizzo.
	 * @return L'oggetto utilizzabile per l'accesso ai dati.
	 */
	protected EntityManager getManager() {
		EntityManager em = FactoryManager.getInstance().getFactory(persistenceUnit).createEntityManager();
		return em;
	}
	
	protected Connection getConnection() {
		String url = "jdbc:sqlite:C:/logica/db/logica.db";
		Connection connection;
		try {
			connection = DriverManager.getConnection(url);
		} catch (Exception e) {
			connection = null;
			System.out.println(e);
		}
		return connection;
	}
	
	/**
	 * Esegue le query nativa specificata.
	 * @param nativeQuery la String contenente codice SQL da eseguire.
	 * @return l'esito dell'operazione.
	 */
	protected boolean executeNativeQuery(String nativeQuery) {
		boolean result;
		EntityManager em = getManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			Query query = em.createNativeQuery(nativeQuery);
			transaction.begin();
			query.executeUpdate();
			transaction.commit();
			result = true;
		} catch (Exception e) {
			logger.error(e);
			if (transaction != null && transaction.isActive())
				transaction.rollback();
			result = false;
		} finally {
			em.close();
		}
		return result;
	}

}
