package it.ltc.database.dao.locali;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ListinoSimulazione;

public class ListinoSimulazioneDao extends CRUDDao<ListinoSimulazione> {

	public ListinoSimulazioneDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ListinoSimulazione.class);
	}
	
	public boolean elimina(ListinoSimulazione listino) {
		ListinoSimulazione entity = delete(listino.getId());
		return entity != null;
	}

	@Override
	protected void updateValues(ListinoSimulazione oldEntity, ListinoSimulazione entity) {
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setNome(entity.getNome());
		oldEntity.setTipo(entity.getTipo());
	}

	public List<ListinoSimulazione> trovaTutti() {
		List<ListinoSimulazione> lista = findAll();
		return lista;
	}

	public ListinoSimulazione inserisci(ListinoSimulazione listino) {
		ListinoSimulazione entity = insert(listino);
		return entity;
	}

	public boolean aggiorna(ListinoSimulazione listino) {
		ListinoSimulazione entity = update(listino, listino.getId());
		boolean update = entity != null;
		return update;
	}

}
