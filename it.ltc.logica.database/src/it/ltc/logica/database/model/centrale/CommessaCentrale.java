package it.ltc.logica.database.model.centrale;

public class CommessaCentrale {
	
	private Integer id;
	private Integer idCliente;
	private int idSede;
	private String nome;
	private String descrizione;
	private String vecchioDbNome;
	private String vecchioDbIndirizzo;
	private String nomeRisorsa;
	private boolean legacy;
	
	public CommessaCentrale() {}
	
	public String toString() {
		return (nome != null) ? nome : "";
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
		CommessaCentrale other = (CommessaCentrale) obj;
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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdSede() {
		return idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getVecchioDbNome() {
		return vecchioDbNome;
	}

	public void setVecchioDbNome(String nomeVecchioDB) {
		this.vecchioDbNome = nomeVecchioDB;
	}

	public String getVecchioDbIndirizzo() {
		return vecchioDbIndirizzo;
	}

	public void setVecchioDbIndirizzo(String indirizzoVecchioDB) {
		this.vecchioDbIndirizzo = indirizzoVecchioDB;
	}

	public String getNomeRisorsa() {
		return nomeRisorsa;
	}

	public void setNomeRisorsa(String nomeRisorsa) {
		this.nomeRisorsa = nomeRisorsa;
	}

	public boolean isLegacy() {
		return legacy;
	}

	public void setLegacy(boolean legacy) {
		this.legacy = legacy;
	}

}
