package it.ltc.logica.database.model.centrale.trasporti;

public class CodificaStatoCorriereTracking {

	private Integer idCorriere;
	private String codificaCorriere;
	private String stato;
	private String descrizione;
	
	public boolean isFinale() {
		boolean consegnato = "S03".equals(stato);
		boolean consegnatoInRitardo = "S04".equals(stato);
		boolean annullato = "C06".equals(stato);
		boolean finale = consegnato || consegnatoInRitardo || annullato;
		return finale;
	}

	public Integer getIdCorriere() {
		return idCorriere;
	}

	public void setIdCorriere(Integer idCorriere) {
		this.idCorriere = idCorriere;
	}

	public String getCodificaCorriere() {
		return codificaCorriere;
	}

	public void setCodificaCorriere(String codificaCorriere) {
		this.codificaCorriere = codificaCorriere;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
