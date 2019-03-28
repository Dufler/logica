package it.ltc.logica.database.model.centrale.utenti;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Permesso {
		
	private Integer id;
	private String nome;
	private String categoria;
	private Integer idPadre;
	
	//Non-mapped element
	@JsonIgnore
	private final Set<Permesso> figli = new HashSet<Permesso>();
	
	public void aggiungiFiglio(Permesso permesso) {
		figli.add(permesso);		
	}
	
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
		Permesso other = (Permesso) obj;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}

	public Collection<Permesso> getFigli() {
		return figli;
	}

}
