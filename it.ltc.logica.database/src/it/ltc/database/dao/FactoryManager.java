package it.ltc.database.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import it.ltc.database.dao.locali.ProprietaLogicaDao;
import it.ltc.logica.database.model.locale.ProprietaLogica;

/**
 * Classe Factory che produce e memorizza <code>EntityManagerFactory</code> centralizzandoli.
 * Tramite questi oggetti è possibile creare <code>EntityManager</code> per accedere al db.
 * @author Damiano
 *
 */
public class FactoryManager {
	
	public static final String sqlCreateScript = "/it/ltc/logica/database/resources/create_tables.sql";
	public static final String sqlUpdateScript = "/it/ltc/logica/database/resources/alter_table_#.sql";
	public static final String CHIAVE_VERSIONE_TABELLE = "table_version";
	
	private static final Logger logger = Logger.getLogger("FactoryManager");
	
	private static FactoryManager instance;
	
	private final HashMap<String, EntityManagerFactory> factories;

	private FactoryManager() {
		factories = new HashMap<String, EntityManagerFactory>();
	}

	public static FactoryManager getInstance() {
		if (null == instance) {
			instance = new FactoryManager();
		}
		return instance;
	}
	
	/**
	 * Restituisce l'oggetto <code>EntityManagerFactory</code> a partire dalla persistence unit passata come argomento.
	 * @param persistenceUnitName il nome della persistence unit così come definito nel file persistence.xml.
	 * @return una Factory capace di creare oggetti <code>EntityManager</code>.
	 */
	public EntityManagerFactory getFactory(String persistenceUnitName) {
		if (!factories.containsKey(persistenceUnitName)) {
			logger.info("Istanzio una nuova factory per la persistence unit: '" + persistenceUnitName + "'");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
			factories.put(persistenceUnitName, emf);
			if ("locale".equals(persistenceUnitName)) {
				createLocalDB(emf);
				updateLocalDB(emf);
			}			
		}
		return factories.get(persistenceUnitName);
	}
	
	private void createLocalDB(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		updateLocalDB(em, sqlCreateScript);
	}
	
	private void updateLocalDB(EntityManagerFactory emf) {
		ProprietaLogicaDao dao = new ProprietaLogicaDao();
		ProprietaLogica versione = dao.trovaDaChiave(CHIAVE_VERSIONE_TABELLE);
		//Se non c'è lo inserisco.
		if (versione == null) {
			versione = inserisciVerioneBase(dao);
		}
		boolean update = true;
		while (update) {
			EntityManager em = emf.createEntityManager();
			String pathUpdate = sqlUpdateScript.replaceFirst("#", versione.getValue());
			update = updateLocalDB(em, pathUpdate);
			if (update) {
				int value = Integer.parseInt(versione.getValue()) + 1;
				versione.setValue(Integer.toString(value));
				versione = dao.aggiorna(versione);
			}
		}
	}
	
	private ProprietaLogica inserisciVerioneBase(ProprietaLogicaDao dao) {
		ProprietaLogica versione = new ProprietaLogica();
		versione.setKey(CHIAVE_VERSIONE_TABELLE);
		versione.setValue("1");
		return dao.inserisci(versione);
	}
	
	private boolean updateLocalDB(EntityManager em, String sqlScriptPath) {
		boolean update = false;
		InputStream stream = getClass().getResourceAsStream(sqlScriptPath);
		if (stream != null)	try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			StringBuffer sb = new StringBuffer();
			String line = reader.readLine();
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			String scriptSQL = sb.toString();
			String[] scripts = scriptSQL.split(";");
			for (String script : scripts) {
				Query query = em.createNativeQuery(script);
				EntityTransaction transaction = em.getTransaction();
				transaction.begin();
				query.executeUpdate();
				transaction.commit();
			}
			update = true;
			logger.info("Script eseguito!");
		} catch (Exception e) {
			update = false;
			logger.error(e);
		} finally {
			em.close();
		}
		return update;
	}

}
