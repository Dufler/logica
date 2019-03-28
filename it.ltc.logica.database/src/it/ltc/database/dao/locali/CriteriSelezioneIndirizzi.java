package it.ltc.database.dao.locali;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;

public class CriteriSelezioneIndirizzi {
	
	private String testo;
	
	public CriteriSelezioneIndirizzi() {}
	
	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	protected Predicate getCriteri(CriteriaBuilder cb, CriteriaQuery<Indirizzo> criteria, Root<Indirizzo> member) {
		Predicate predicatoRagioneSociale = cb.like(member.get("ragioneSociale"), "%" + testo + "%");
		Predicate predicatoIndirizzo = cb.like(member.get("indirizzo"), "%" + testo + "%");
		Predicate predicatoLocalita = cb.like(member.get("localita"), "%" + testo + "%");
		Predicate predicatoNeBastaUno = cb.or(predicatoRagioneSociale, predicatoIndirizzo, predicatoLocalita);
		return predicatoNeBastaUno;
	}
	
	protected Predicate getCriteriSimulazione(CriteriaBuilder cb, CriteriaQuery<IndirizzoSimulazione> criteria, Root<IndirizzoSimulazione> member) {
		Predicate predicatoRagioneSociale = cb.like(member.get("ragioneSociale"), "%" + testo + "%");
		Predicate predicatoIndirizzo = cb.like(member.get("indirizzo"), "%" + testo + "%");
		Predicate predicatoLocalita = cb.like(member.get("localita"), "%" + testo + "%");
		Predicate predicatoNeBastaUno = cb.or(predicatoRagioneSociale, predicatoIndirizzo, predicatoLocalita);
		return predicatoNeBastaUno;
	}

}
