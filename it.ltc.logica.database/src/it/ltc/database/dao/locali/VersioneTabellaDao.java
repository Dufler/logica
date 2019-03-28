package it.ltc.database.dao.locali;

import java.util.Date;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.VersioneTabella;

public class VersioneTabellaDao extends CRUDDao<VersioneTabella> {
	
	private static final Logger logger = Logger.getLogger("VersioneTabellaDao");
	
	private static VersioneTabellaDao instance;
	
	private static final String[] tabelle = {"spedizione", "spedizione_giacenza", "spedizione_contrassegno", "indirizzo", "cap"}; 

	private VersioneTabellaDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, VersioneTabella.class);
		checkPresenzaVersioni();
	}

	public static VersioneTabellaDao getInstance() {
		if (instance == null) {
			instance = new VersioneTabellaDao();
		}
		return instance;
	}
	
	private void checkPresenzaVersioni() {
		for (String tabella : tabelle) {
			//Cerco se esiste il record corrispondente.
			VersioneTabella versione = findByID(tabella);
			//Se non esiste ancora allora la inserisco.
			if (versione == null) {
				VersioneTabella nuovaVersione = new VersioneTabella();
				nuovaVersione.setTabella(tabella);
				nuovaVersione.setDataVersione(new Date(0));
				insert(nuovaVersione);
				logger.info("Inserito nuovo record per le versioni delle tabelle: " + tabella);
			}
		}
	}
	
	public VersioneTabella getVersione(String nomeTabella) {
		VersioneTabella versione = findByID(nomeTabella);
		return versione;
	}
	
	public boolean setVersione(String nomeTabella, Date ultimaVersione) {
		boolean update;
		VersioneTabella versione = findByID(nomeTabella);
		if (versione != null) {
			versione.setDataVersione(ultimaVersione);
			VersioneTabella nuovaVersione = update(versione, nomeTabella);
			update = nuovaVersione != null;
		} else {
			update = false;
		}
		return update;
	}

	@Override
	protected void updateValues(VersioneTabella oldEntity, VersioneTabella entity) {
		oldEntity.setDataVersione(entity.getDataVersione());		
	}

}
