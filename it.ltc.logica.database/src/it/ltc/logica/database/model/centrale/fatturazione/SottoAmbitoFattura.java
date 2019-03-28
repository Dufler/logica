package it.ltc.logica.database.model.centrale.fatturazione;

public class SottoAmbitoFattura {
	
	private Integer id;
	private String nome;
	private String descrizione;
	private Integer idAmbito;
	private String categoriaAmbito;
	private Boolean valoreAmmesso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getIdAmbito() {
		return idAmbito;
	}

	public void setIdAmbito(Integer idAmbito) {
		this.idAmbito = idAmbito;
	}

	public String getCategoriaAmbito() {
		return categoriaAmbito;
	}

	public void setCategoriaAmbito(String categoria) {
		this.categoriaAmbito = categoria;
	}

	public Boolean getValoreAmmesso() {
		return valoreAmmesso;
	}

	public void setValoreAmmesso(Boolean valoreAmmesso) {
		this.valoreAmmesso = valoreAmmesso;
	}

	@Override
	public String toString() {
		return nome;
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
		SottoAmbitoFattura other = (SottoAmbitoFattura) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
