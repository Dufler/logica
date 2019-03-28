package it.ltc.database.dao.locali;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDaoConSincronizzazione;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;

public abstract class IndirizzoLocaleDaoAstratto extends CRUDDaoConSincronizzazione<Indirizzo> {

	public IndirizzoLocaleDaoAstratto() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, Indirizzo.class);
	}
	
	public Collection<Indirizzo> trovaTutte() {
		List<Indirizzo> giacenze = findAll();
		return giacenze;
	}

	public Indirizzo trovaDaID(int id) {
		Indirizzo indirizzo = findByID(id);
		return indirizzo;
	}
	
	public Indirizzo inserisci(Indirizzo indirizzo) {
		Indirizzo entity = insert(indirizzo);
		return entity;
	}
	
	public Indirizzo aggiorna(Indirizzo indirizzo) {
		Indirizzo entity = update(indirizzo, indirizzo.getId());
		return entity;
	}
	
	public Indirizzo elimina(Indirizzo indirizzo) {
		Indirizzo entity = delete(indirizzo.getId());
		return entity;
	}

	@Override
	protected void updateValues(Indirizzo oldEntity, Indirizzo entity) {
		oldEntity.setCap(entity.getCap());
		oldEntity.setConsegnaAlPiano(entity.getConsegnaAlPiano());
		oldEntity.setConsegnaAppuntamento(entity.getConsegnaAppuntamento());
		oldEntity.setConsegnaGdo(entity.getConsegnaGdo());
		oldEntity.setConsegnaPrivato(entity.getConsegnaPrivato());
		oldEntity.setEmail(entity.getEmail());
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setLocalita(entity.getLocalita());
		oldEntity.setNazione(entity.getNazione());
		oldEntity.setProvincia(entity.getProvincia());
		oldEntity.setRagioneSociale(entity.getRagioneSociale());
		oldEntity.setTelefono(entity.getTelefono());
	}
	
	public List<Indirizzo> trovaCorrispondenti(CriteriSelezioneIndirizzi criteri) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Indirizzo> criteria = cb.createQuery(Indirizzo.class);
        Root<Indirizzo> member = criteria.from(Indirizzo.class);
        criteria.select(member).where(criteri.getCriteri(cb, criteria, member));
		List<Indirizzo> lista = em.createQuery(criteria).getResultList();
		em.close();
        return lista;
	}
	
	public List<Indirizzo> trovaTopN(int maxResult) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Indirizzo> criteria = cb.createQuery(Indirizzo.class);
        Root<Indirizzo> member = criteria.from(Indirizzo.class);
        criteria.select(member).orderBy(cb.asc(member.get("ragioneSociale")));
		List<Indirizzo> lista = em.createQuery(criteria).setMaxResults(maxResult).getResultList();
		em.close();
        return lista;
	}

}
