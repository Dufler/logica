package it.ltc.logica.database.model.centrale.fatturazione;

public class AmbitoFattura {
	
	public static final int ID_SPEDIZIONI = 1;
	public static final int ID_GIACENZE = 2;
	public static final int ID_RITIRI = 9;
	public static final int ID_CORRIERI = 4;
	
	public static final int ID_LOGISTICA = 5;
	public static final int ID_LOGISTICA_GIACENZE_SPAZI = 6;
	
	public static final int ID_EXTRA = 7;
	
	public static final int ID_SIMULAZIONI_TRASPORTI = 3;
	public static final int ID_SIMULAZIONI_CORRIERI = 8;
	
	public enum Categoria {
		
		TRASPORTI,
		LOGISTICA,
		EXTRA,
		SIMULAZIONE,
		CORRIERI;
		
	}
	
	private Integer id;
	private String categoria;
	private String nome;
	private String descrizione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public Categoria getECategoria() {
		Categoria c = null;
		if (categoria != null && !categoria.isEmpty())
			c = Categoria.valueOf(categoria);
		return c;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
		AmbitoFattura other = (AmbitoFattura) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
