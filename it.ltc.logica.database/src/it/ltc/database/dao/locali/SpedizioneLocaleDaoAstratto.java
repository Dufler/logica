package it.ltc.database.dao.locali;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDaoConSincronizzazione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;

public abstract class SpedizioneLocaleDaoAstratto extends CRUDDaoConSincronizzazione<Spedizione> {

	public SpedizioneLocaleDaoAstratto() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, Spedizione.class);
	}
	
	public Spedizione inserisci(Spedizione spedizione) {
		Spedizione entity = insert(spedizione);
		return entity;
	}
	
	public Spedizione aggiorna(Spedizione spedizione) {
		Spedizione entity = update(spedizione, spedizione.getId());
		return entity;
	}
	
	public Spedizione elimina(Spedizione spedizione) {
		Spedizione entity = delete(spedizione.getId());
		return entity;
	}

	@Override
	protected void updateValues(Spedizione oldEntity, Spedizione entity) {
		oldEntity.setAssicurazione(entity.getAssicurazione());
		oldEntity.setCodiceCliente(entity.getCodiceCliente());
		oldEntity.setColli(entity.getColli());
		oldEntity.setContrassegno(entity.getContrassegno());
		oldEntity.setCosto(entity.getCosto());
		oldEntity.setDataPartenza(entity.getDataPartenza());
		oldEntity.setDatiCompleti(entity.getDatiCompleti());
		oldEntity.setFatturazione(entity.getFatturazione());
		oldEntity.setGiacenza(entity.getGiacenza());
		oldEntity.setIdCommessa(entity.getIdCommessa());
		oldEntity.setIdCorriere(entity.getIdCorriere());
		oldEntity.setIdDocumento(entity.getIdDocumento());
		oldEntity.setIndirizzoDestinazione(entity.getIndirizzoDestinazione());
		oldEntity.setIndirizzoPartenza(entity.getIndirizzoPartenza());
		oldEntity.setInRitardo(entity.getInRitardo());
		oldEntity.setLetteraDiVettura(entity.getLetteraDiVettura());
		oldEntity.setNote(entity.getNote());
		oldEntity.setParticolarita(entity.getParticolarita());
		oldEntity.setPeso(entity.getPeso());
		oldEntity.setPezzi(entity.getPezzi());
		oldEntity.setRagioneSocialeDestinatario(entity.getRagioneSocialeDestinatario());
		oldEntity.setRicavo(entity.getRicavo());
		oldEntity.setRiferimentoCliente(entity.getRiferimentoCliente());
		oldEntity.setRiferimentoMittente(entity.getRiferimentoMittente());
		oldEntity.setServizio(entity.getServizio());
		oldEntity.setStato(entity.getStato());
		oldEntity.setTipo(entity.getTipo());
		oldEntity.setValoreMerceDichiarato(entity.getValoreMerceDichiarato());
		oldEntity.setVolume(entity.getVolume());
	}

	public Collection<Spedizione> trovaTutte() {
		List<Spedizione> spedizioni = findAll();
		return spedizioni;
	}
	
	public Collection<Spedizione> trovaTutteTopN(int max) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Spedizione> criteria = cb.createQuery(Spedizione.class);
        Root<Spedizione> member = criteria.from(Spedizione.class);
        criteria.select(member).orderBy(cb.desc(member.get("dataPartenza")));
		List<Spedizione> lista = em.createQuery(criteria).setMaxResults(max).getResultList();
		em.close();
        return lista;
	}

	public Spedizione trovaDaID(int id) {
		Spedizione spedizione = findByID(id);
		return spedizione;
	}
	
	public List<Spedizione> trovaCorrispondenti(CriteriSelezioneSpedizioni criteri) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Spedizione> criteria = cb.createQuery(Spedizione.class);
        Root<Spedizione> member = criteria.from(Spedizione.class);
        criteria.select(member).where(criteri.getCriteri(cb, criteria, member));
		List<Spedizione> lista = em.createQuery(criteria).getResultList();
		em.clear();
		em.close();
        return lista;
	}

}
