package it.ltc.logica.database.model.centrale.fatturazione;

public class VoceFattura {

	private Integer id;
	private Integer idAmbito;
	private Integer idSottoAmbito;
	private Integer idRiferimento;
	private Integer idCommessa;
	private Integer idListino;
	private Integer idVoce;
	private Integer idDocumento;
	private Double quantita;
	private Double costoUnitario;
	private Double importo;
	private String note;

	@Override
	public String toString() {
		return "Voce di Fattura id=" + id + ", Voce=" + idVoce + ", Documento=" + idDocumento + ", quantita=" + quantita + ", costoUnitario=" + costoUnitario + ", importo="	+ importo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoceFattura other = (VoceFattura) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAmbito() {
		return idAmbito;
	}

	public void setIdAmbito(Integer idAmbito) {
		this.idAmbito = idAmbito;
	}

	public Integer getIdSottoAmbito() {
		return idSottoAmbito;
	}

	public void setIdSottoAmbito(Integer idSottoAmbito) {
		this.idSottoAmbito = idSottoAmbito;
	}

	public Integer getIdRiferimento() {
		return idRiferimento;
	}

	public void setIdRiferimento(Integer idRiferimento) {
		this.idRiferimento = idRiferimento;
	}

	public Integer getIdCommessa() {
		return idCommessa;
	}

	public void setIdCommessa(Integer idCommessa) {
		this.idCommessa = idCommessa;
	}

	public Integer getIdListino() {
		return idListino;
	}

	public void setIdListino(Integer idListino) {
		this.idListino = idListino;
	}

	public Integer getIdVoce() {
		return idVoce;
	}

	public void setIdVoce(Integer idVoce) {
		this.idVoce = idVoce;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Double getQuantita() {
		return quantita;
	}

	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

	public Double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
