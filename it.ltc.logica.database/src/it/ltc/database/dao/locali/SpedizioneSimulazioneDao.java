package it.ltc.database.dao.locali;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;

public class SpedizioneSimulazioneDao extends CRUDDao<SpedizioneSimulazione> {

	public SpedizioneSimulazioneDao() {
		super(LOCAL_PERSISTENCE_UNIT_NAME, SpedizioneSimulazione.class);
	}
	
	public SpedizioneSimulazione inserisci(SpedizioneSimulazione spedizione) {
		SpedizioneSimulazione entity = insert(spedizione);
		return entity;
	}
	
	public SpedizioneSimulazione aggiorna(SpedizioneSimulazione spedizione) {
		SpedizioneSimulazione entity = update(spedizione, spedizione.getId());
		return entity;
	}
	
	public SpedizioneSimulazione elimina(SpedizioneSimulazione spedizione) {
		SpedizioneSimulazione entity = delete(spedizione.getId());
		return entity;
	}

	@Override
	protected void updateValues(SpedizioneSimulazione oldEntity, SpedizioneSimulazione entity) {
		oldEntity.setAssicurazione(entity.getAssicurazione());
		oldEntity.setCodiceCliente(entity.getCodiceCliente());
		oldEntity.setColli(entity.getColli());
		oldEntity.setContrassegno(entity.getContrassegno());
		oldEntity.setCosto(entity.getCosto());
		oldEntity.setDataPartenza(entity.getDataPartenza());
		oldEntity.setDatiCompleti(entity.getDatiCompleti());
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

	public Collection<SpedizioneSimulazione> trovaTutte() {
		List<SpedizioneSimulazione> spedizioni = findAll();
		return spedizioni;
	}
	
	public Collection<SpedizioneSimulazione> trovaTutteTopN(int max) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SpedizioneSimulazione> criteria = cb.createQuery(c);
        Root<SpedizioneSimulazione> member = criteria.from(c);
        criteria.select(member).orderBy(cb.desc(member.get("dataPartenza")));
		List<SpedizioneSimulazione> lista = em.createQuery(criteria).setMaxResults(max).getResultList();
		em.close();
        return lista;
	}
	
	public Collection<SpedizioneSimulazione> trovaDaDocumento(int idDocumento) {
		List<SpedizioneSimulazione> entities = findAllEqualTo("idDocumento", idDocumento);
		return entities;
	}

	public SpedizioneSimulazione trovaDaID(int id) {
		SpedizioneSimulazione spedizione = findByID(id);
		return spedizione;
	}
	
	public List<SpedizioneSimulazione> trovaCorrispondenti(CriteriSelezioneSpedizioni criteri) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SpedizioneSimulazione> criteria = cb.createQuery(c);
        Root<SpedizioneSimulazione> member = criteria.from(c);
        criteria.select(member).where(criteri.getCriteri(cb, criteria, member));
		List<SpedizioneSimulazione> lista = em.createQuery(criteria).getResultList();
		em.clear();
		em.close();
        return lista;
	}

}
