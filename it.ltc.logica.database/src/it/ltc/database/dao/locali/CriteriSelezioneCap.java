package it.ltc.database.dao.locali;

public class CriteriSelezioneCap {
	
	private String testo;
	
	public CriteriSelezioneCap() {}
	
	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

//	protected Predicate getCriteri(CriteriaBuilder cb, CriteriaQuery<Cap> criteria, Root<Cap> member) {
//		Predicate predicatoRagioneSociale = cb.like(member.get("cap"), testo + "%");
//		Predicate predicatoLocalita = cb.like(member.get("localita"), "%" + testo + "%");
//		Predicate predicatoNeBastaUno = cb.or(predicatoRagioneSociale, predicatoLocalita);
//		return predicatoNeBastaUno;
//	}

}
