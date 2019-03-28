package it.ltc.database.dao.locali;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;

public class IndirizzoSimulazioneDao extends CRUDDao<IndirizzoSimulazione> {

	public IndirizzoSimulazioneDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, IndirizzoSimulazione.class);
	}
	
	public List<IndirizzoSimulazione> trovaTutti() {
		List<IndirizzoSimulazione> entities = findAll();
		return entities;
	}
	
	public List<IndirizzoSimulazione> trovaTuttiDaInserimentoManuale() {
		List<IndirizzoSimulazione> entities = findAllEqualTo("inserimentoManuale", true);
		return entities;
	}
	
	public List<IndirizzoSimulazione> trovaCorrispondenti(CriteriSelezioneIndirizzi criteri) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IndirizzoSimulazione> criteria = cb.createQuery(IndirizzoSimulazione.class);
        Root<IndirizzoSimulazione> member = criteria.from(IndirizzoSimulazione.class);
        criteria.select(member).where(criteri.getCriteriSimulazione(cb, criteria, member));
		List<IndirizzoSimulazione> lista = em.createQuery(criteria).getResultList();
		em.close();
        return lista;
	}

	public IndirizzoSimulazione trovaDaID(int id) {
		IndirizzoSimulazione indirizzo = findByID(id);
		return indirizzo;
	}
	
	public IndirizzoSimulazione salvaIndirizzo(IndirizzoSimulazione indirizzo) {
		//Controllo se l'indirizzo esiste già, se non esiste lo inserisco.
		IndirizzoSimulazione esistente = trovaIndirizzo(indirizzo);
		if (esistente == null) {
			//Nel caso in cui mi hanno chiesto di salvare un indirizzo già presente a sistema (cioè con ID valorizzato) 
			//ma comunque abbastanza diverso dalla versione precedente ne creo uno nuovo.
			indirizzo.setId(0);
			esistente = insert(indirizzo);
		} else {
			//Se cambiano pochi dettagli (es. numero di telefono) aggiorno quello che serve e lo restituisco.
			indirizzo.setId(esistente.getId());
			esistente = update(indirizzo, indirizzo.getId());
		}
		return esistente;
	}

	protected IndirizzoSimulazione trovaIndirizzo(IndirizzoSimulazione nuovoIndirizzo) {
		EntityManager em = getManager();
		//Controllo se ne ho già almeno un altro con le stesse caratteristiche.
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IndirizzoSimulazione> criteria = cb.createQuery(c);
        Root<IndirizzoSimulazione> member = criteria.from(c);
        Predicate likeRagioneSociale = cb.like(member.get("ragioneSociale"), "%" + nuovoIndirizzo.getRagioneSociale() + "%");
        Predicate likeIndirizzo = cb.like(member.get("indirizzo"), "%" + nuovoIndirizzo.getIndirizzo() + "%");
        Predicate likeLocalita = cb.like(member.get("localita"), "%" + nuovoIndirizzo.getLocalita() + "%");
        Predicate cap = cb.equal(member.get("cap"), nuovoIndirizzo.getCap());
        Predicate provincia = cb.equal(member.get("provincia"), nuovoIndirizzo.getProvincia());
        Predicate nazione = cb.equal(member.get("nazione"), nuovoIndirizzo.getNazione());
        criteria.select(member).where(cb.and(cap, provincia, nazione, likeRagioneSociale, likeIndirizzo, likeLocalita));
		List<IndirizzoSimulazione> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		IndirizzoSimulazione indirizzo = lista.isEmpty() ? null : lista.get(0);
		return indirizzo;
	}
	
	public IndirizzoSimulazione elimina(IndirizzoSimulazione indirizzo) {
		IndirizzoSimulazione entity = delete(indirizzo.getId());
		return entity;
	}

	@Override
	protected void updateValues(IndirizzoSimulazione oldEntity, IndirizzoSimulazione entity) {
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

}
