package it.ltc.logica.database.model.centrale.fatturazione;

public class PreferenzeFatturazione {
	
	private int commessa;
	private int ambito;
	private String descrizioneFattura;
	private ModalitaPagamentoFattura modalitaPagamento;
	private int coordinatePagamento;
	private String layout;
	
	public PreferenzeFatturazione() {}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public int getAmbito() {
		return ambito;
	}

	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}

	public String getDescrizioneFattura() {
		return descrizioneFattura;
	}

	public void setDescrizioneFattura(String descrizioneFattura) {
		this.descrizioneFattura = descrizioneFattura;
	}

	public ModalitaPagamentoFattura getModalitaPagamento() {
		return modalitaPagamento;
	}

	public void setModalitaPagamento(ModalitaPagamentoFattura modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}

	public int getCoordinatePagamento() {
		return coordinatePagamento;
	}

	public void setCoordinatePagamento(int coordinatePagamento) {
		this.coordinatePagamento = coordinatePagamento;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ambito;
		result = prime * result + commessa;
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
		PreferenzeFatturazione other = (PreferenzeFatturazione) obj;
		if (ambito != other.ambito)
			return false;
		if (commessa != other.commessa)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PreferenzeFatturazione [commessa=" + commessa + ", ambito=" + ambito + ", descrizioneVoce=" + descrizioneFattura + ", modalitaPagamento=" + modalitaPagamento + "]";
	}

}
