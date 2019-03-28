package it.ltc.database.dao.locali;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDaoConSincronizzazione;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.database.model.centrale.ProdottoPK;

public abstract class ProdottoLocaleDaoAstratto extends CRUDDaoConSincronizzazione<Prodotto> {
	
	protected final Commessa commessa;

	public ProdottoLocaleDaoAstratto(Commessa commessa) {
		super(LOCAL_PERSISTENCE_UNIT_NAME, Prodotto.class);
		this.commessa = commessa;
	}
	
	public Prodotto trovaDaID(int id, int commessa) {
		ProdottoPK pk = new ProdottoPK();
		pk.setId(id);
		pk.setCommessa(commessa);
		Prodotto entity = findByID(pk);
		return entity;
	}
	
	public List<Prodotto> trovaCorrispondenti(Prodotto filtro) {		
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Prodotto> criteria = cb.createQuery(Prodotto.class);
        Root<Prodotto> member = criteria.from(c);
        Predicate[] predicates = new Predicate[1];
        List<Predicate> condizioniAccessorie = new LinkedList<>();
        Predicate condizioneCommessa = cb.equal(member.get("commessa"), commessa.getId());
        String sku = filtro.getChiaveCliente();
		if (sku != null && !sku.isEmpty()) {
			Predicate condizioneSKU = cb.like(member.get("chiaveCliente"), "%" + sku + "%");
			condizioniAccessorie.add(condizioneSKU);
		}
		String modello = filtro.getCodiceModello();
		if (modello != null && !modello.isEmpty()) {
			Predicate condizioneModello = cb.like(member.get("codiceModello"), "%" + modello + "%");
			condizioniAccessorie.add(condizioneModello);
		}
		String descrizione = filtro.getDescrizione();
		if (descrizione != null && !descrizione.isEmpty()) {
			Predicate condizioneDescrizione = cb.like(member.get("descrizione"), "%" + descrizione + "%");
			condizioniAccessorie.add(condizioneDescrizione);
		}
		String stagione = filtro.getStagione();
		if (stagione != null && !stagione.isEmpty()) {
			Predicate condizioneStagione = cb.like(member.get("stagione"), "%" + stagione + "%");
			condizioniAccessorie.add(condizioneStagione);
		}
        predicates = condizioniAccessorie.toArray(predicates);
        criteria.select(member).where(condizioniAccessorie.isEmpty() ? condizioneCommessa : cb.and(condizioneCommessa, cb.or(predicates)));
        List<Prodotto> entities = em.createQuery(criteria).setMaxResults(1000).getResultList();
        return entities;
	}
	
	public Prodotto inserisci(Prodotto prodotto) {
		Prodotto entity = insert(prodotto);
		return entity;
	}
	
	public Prodotto aggiorna(Prodotto prodotto) {
		Prodotto entity = update(prodotto, prodotto.getPK());
		return entity;
	}
	
	public Prodotto elimina(Prodotto prodotto) {
		Prodotto entity = delete(prodotto.getPK());
		return entity;
	}
	
	public final Date trovaDataUltimoAggiornamento() {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Date> criteria = cb.createQuery(Date.class);
        Root<Prodotto> member = criteria.from(c);
        criteria.select(cb.greatest(member.get("dataUltimaModifica"))).where(cb.equal(member.get("commessa"), commessa.getId()));
        List<Date> result = em.createQuery(criteria).getResultList();
        Date max = result.isEmpty() ? null : result.get(0);
		return max;
	}
	
	@Override
	protected void updateValues(Prodotto oldEntity, Prodotto entity) {
		oldEntity.setBarcode(entity.getBarcode());
		oldEntity.setBarcodeFornitore(entity.getBarcodeFornitore());
		oldEntity.setBrand(entity.getBrand());
		oldEntity.setCassa(entity.getCassa());
		oldEntity.setCategoria(entity.getCategoria());
		oldEntity.setChiaveCliente(entity.getChiaveCliente());
		oldEntity.setCodiceModello(entity.getCodiceModello());
		oldEntity.setColore(entity.getColore());
		oldEntity.setComposizione(entity.getComposizione());
		oldEntity.setDataUltimaModifica(entity.getDataUltimaModifica());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setDescrizioneAggiuntiva(entity.getDescrizioneAggiuntiva());
		oldEntity.setH(entity.getH());
		oldEntity.setL(entity.getL());
		oldEntity.setZ(entity.getZ());
		oldEntity.setMadeIn(entity.getMadeIn());
		oldEntity.setNote(entity.getNote());
		oldEntity.setParticolarita(entity.getParticolarita());
		oldEntity.setPeso(entity.getPeso());
		oldEntity.setSkuFornitore(entity.getSkuFornitore());
		oldEntity.setSottoCategoria(entity.getSottoCategoria());
		oldEntity.setStagione(entity.getStagione());
		oldEntity.setTaglia(entity.getTaglia());
		oldEntity.setValore(entity.getValore());
	}

}
