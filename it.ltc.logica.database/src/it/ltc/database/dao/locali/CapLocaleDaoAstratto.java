package it.ltc.database.dao.locali;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDaoConSincronizzazione;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.database.model.centrale.trasporti.CapPK;

public abstract class CapLocaleDaoAstratto extends CRUDDaoConSincronizzazione<Cap> {

	public CapLocaleDaoAstratto() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, Cap.class);
	}
	
	public Collection<Cap> trovaTutte() {
		List<Cap> giacenze = findAll();
		return giacenze;
	}
	
	public List<Cap> trovaPrimiN(int maxResult) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cap> criteria = cb.createQuery(Cap.class);
        Root<Cap> member = criteria.from(Cap.class);
        criteria.select(member);
		List<Cap> lista = em.createQuery(criteria).setMaxResults(maxResult).getResultList();
		em.close();
        return lista;
	}
	
	public List<Cap> trovaCorrispondenti(CriteriSelezioneCap criteri) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cap> criteria = cb.createQuery(Cap.class);
        Root<Cap> member = criteria.from(Cap.class);
        Predicate predicatoRagioneSociale = cb.like(member.get("cap"), criteri.getTesto() + "%");
		Predicate predicatoLocalita = cb.like(member.get("localita"), "%" + criteri.getTesto() + "%");
		Predicate predicatoNeBastaUno = cb.or(predicatoRagioneSociale, predicatoLocalita);
        criteria.select(member).where(predicatoNeBastaUno);
		List<Cap> lista = em.createQuery(criteria).getResultList();
		em.close();
        return lista;
	}

	public Cap trovaDaID(String cap, String localita) {
		CapPK pk = new CapPK();
		pk.setCap(cap);
		pk.setLocalita(localita);
		Cap giacenza = findByID(pk);
		return giacenza;
	}
	
//	public Cap trovaDaSoloCap(String cap) {
//		EntityManager em = getManager();
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Cap> criteria = cb.createQuery(Cap.class);
//		Root<Cap> member = criteria.from(Cap.class);
//		criteria.select(member).where(cb.equal(member.get("cap"), cap));
//		List<Cap> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
//		em.close();
//		Cap corrispondenza = lista.isEmpty() ? null : lista.get(0);
//		return corrispondenza;
//	}
	
	public Cap trovaDaSoloCap(String cap) {
		Cap corrispondenza = findFirstOneEqualTo("cap", cap);
		return corrispondenza;
	}
	
	public Cap trovaDaLocalita(String localita) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cap> criteria = cb.createQuery(Cap.class);
        Root<Cap> member = criteria.from(Cap.class);
        criteria.select(member).where(cb.equal(member.get("localita"), localita));
		List<Cap> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		Cap corrispondenza = lista.isEmpty() ? null : lista.get(0);
		return corrispondenza;
	}
	
	public Cap inserisci(Cap cap) {
		Cap entity = insert(cap);
		return entity;
	}
	
	public Cap aggiorna(Cap cap) {
		Cap entity = update(cap, cap.getPK());
		return entity;
	}
	
	public Cap elimina(Cap cap) {
		Cap entity = delete(cap.getPK());
		return entity;
	}

	@Override
	protected void updateValues(Cap oldEntity, Cap entity) {
		oldEntity.setBrtDisagiate(entity.getBrtDisagiate());
		oldEntity.setBrtIsole(entity.getBrtIsole());
		oldEntity.setBrtZtl(entity.getBrtZtl());
		oldEntity.setProvincia(entity.getProvincia());
		oldEntity.setRegione(entity.getRegione());
		oldEntity.setTntOreDieci(entity.getTntOreDieci());
		oldEntity.setTntOreDodici(entity.getTntOreDodici());
	}

}
