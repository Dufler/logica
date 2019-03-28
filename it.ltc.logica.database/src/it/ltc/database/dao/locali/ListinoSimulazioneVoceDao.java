package it.ltc.database.dao.locali;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;

public class ListinoSimulazioneVoceDao extends CRUDDao<ListinoSimulazioneVoce> {

	public ListinoSimulazioneVoceDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, ListinoSimulazioneVoce.class);
	}
	
	public boolean elimina(ListinoSimulazioneVoce voce) {
		ListinoSimulazioneVoce entity = delete(voce.getId());
		boolean delete = entity != null;
		return delete;
	}

	@Override
	protected void updateValues(ListinoSimulazioneVoce oldEntity, ListinoSimulazioneVoce entity) {
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setIdListino(entity.getIdListino());
		oldEntity.setIdsottoAmbito(entity.getIdsottoAmbito());
		oldEntity.setNome(entity.getNome());
		oldEntity.setStrategia(entity.getStrategia());
		oldEntity.setTipo(entity.getTipo());
		oldEntity.setValoreSottoAmbito(entity.getValoreSottoAmbito());
	}

	public List<ListinoSimulazioneVoce> trovaVociDaListino(ListinoSimulazione listino) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ListinoSimulazioneVoce> criteria = cb.createQuery(ListinoSimulazioneVoce.class);
        Root<ListinoSimulazioneVoce> member = criteria.from(ListinoSimulazioneVoce.class);
        criteria.select(member).where(cb.equal(member.get("idListino"), listino.getId()));
		List<ListinoSimulazioneVoce> lista = em.createQuery(criteria).getResultList();
		em.close();
		return lista;
	}

	public boolean aggiorna(ListinoSimulazioneVoce voceDiListino) {
		ListinoSimulazioneVoce entity = update(voceDiListino, voceDiListino.getId());
		boolean update = entity != null;
		return update;
	}

	public ListinoSimulazioneVoce inserisci(ListinoSimulazioneVoce voce) {
		ListinoSimulazioneVoce entity = insert(voce);
		return entity;
	}

}
