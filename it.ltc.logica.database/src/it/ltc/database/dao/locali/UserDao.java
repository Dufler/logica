package it.ltc.database.dao.locali;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.User;

public class UserDao extends CRUDDao<User> {

	public UserDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, User.class);
	}
	
	public List<User> trovaTutti() {
		List<User> lista = findAll();
		return lista;
	}
	
	public User inserisci(User user) {
		User entity = insert(user);
		return entity;
	}
	
	public User aggiorna(User user) {
		User entity = update(user, user.getUsername());
		return entity;
	}

	@Override
	protected void updateValues(User oldEntity, User entity) {
		oldEntity.setPassword(entity.getPassword());
		oldEntity.setUltimoLogin(entity.getUltimoLogin());
	}

}
