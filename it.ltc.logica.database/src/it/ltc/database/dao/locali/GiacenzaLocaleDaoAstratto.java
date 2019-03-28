package it.ltc.database.dao.locali;

import java.util.Collection;
import java.util.List;

import it.ltc.database.dao.CRUDDaoConSincronizzazione;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;

public abstract class GiacenzaLocaleDaoAstratto extends CRUDDaoConSincronizzazione<Giacenza> {

	public GiacenzaLocaleDaoAstratto() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, Giacenza.class);
	}
	
	public Collection<Giacenza> trovaTutte() {
		List<Giacenza> giacenze = findAll();
		return giacenze;
	}

	public Giacenza trovaDaID(int id) {
		Giacenza giacenza = findByID(id);
		return giacenza;
	}
	
	public Giacenza inserisci(Giacenza giacenza) {
		Giacenza entity = insert(giacenza);
		return entity;
	}
	
	public Giacenza aggiorna(Giacenza giacenza) {
		Giacenza entity = update(giacenza, giacenza.getId());
		return entity;
	}
	
	public Giacenza elimina(Giacenza giacenza) {
		Giacenza entity = delete(giacenza.getId());
		return entity;
	}

	@Override
	protected void updateValues(Giacenza oldEntity, Giacenza entity) {
		oldEntity.setCosto(entity.getCosto());
		oldEntity.setDataApertura(entity.getDataApertura());
		oldEntity.setDataChiusura(entity.getDataChiusura());
		oldEntity.setFatturazione(entity.getFatturazione());
		oldEntity.setIdCommessa(entity.getIdCommessa());
		oldEntity.setIdDestinatario(entity.getIdDestinatario());
		oldEntity.setIdDocumento(entity.getIdDocumento());
		oldEntity.setIdMittente(entity.getIdMittente());
		oldEntity.setIdSpedizione(entity.getIdSpedizione());
		oldEntity.setLetteraDiVettura(entity.getLetteraDiVettura());
		oldEntity.setLetteraDiVetturaOriginale(entity.getLetteraDiVetturaOriginale());
		oldEntity.setNote(entity.getNote());
		oldEntity.setRicavo(entity.getRicavo());
	}
	
//	@Override
//	public Date trovaDataUltimoAggiornamento() {
//		EntityManager em = getManager();
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Number> criteria = cb.createQuery(Number.class);
//        Root<Giacenza> member = criteria.from(Giacenza.class);
//        criteria.select(cb.max(member.get("dataUltimaModifica")));
//        List<Number> result = em.createQuery(criteria).getResultList();
//        Number max = result.isEmpty() ? null : result.get(0);
//        Date date = max != null ? new Date(max.longValue()) : null;
//		return date;
//	}

}
