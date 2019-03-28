package it.ltc.logica.database.model.centrale.listini;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ListinoCommessa implements Listino {
	
	@JsonIgnore
	public static final int TIPO_SIMULAZIONE_TRASPORTI = 3;
	
	@JsonIgnore
	private static ListinoCommessa listinoNonInserito;
	
	private Integer id;
	private Integer idCommessa;
	private Integer tipo;
	private String nome;
	private String descrizione;
	
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
		ListinoCommessa other = (ListinoCommessa) obj;
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

	public Integer getIdCommessa() {
		return idCommessa;
	}

	public void setIdCommessa(Integer idCommessa) {
		this.idCommessa = idCommessa;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	/**
	 * Restituisce un listino fittizio da usare per i casi in cui si va a simulare il costo 
	 * di una spedizione per cui non è stato ancora inserito un listino.
	 * @return
	 */
	public static ListinoCommessa getListinoNonInserito() {
		if (listinoNonInserito == null) {
			listinoNonInserito = new ListinoCommessa();
			listinoNonInserito.setId(-1);
			listinoNonInserito.setIdCommessa(32);
			listinoNonInserito.setNome("Listino Non Inserito");
			listinoNonInserito.setTipo(TIPO_SIMULAZIONE_TRASPORTI);
			listinoNonInserito.setDescrizione("Listino non inserito!");
		}
		return listinoNonInserito;
	}
	
}
