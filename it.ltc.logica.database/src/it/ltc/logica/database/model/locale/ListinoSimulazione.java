package it.ltc.logica.database.model.locale;

import java.io.Serializable;
import javax.persistence.*;

import it.ltc.logica.database.model.centrale.listini.Listino;

@Entity
@Table(name="listino_simulazione")
public class ListinoSimulazione implements Listino, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true, nullable=false, insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic
	private int id;
	
	private int tipo;
	private String nome;
	private String descrizione;

	
	public String getTableDefinition() {
		String tableDefinition = "CREATE TABLE IF NOT EXISTS listino_simulazione ("
                + "	id INTEGER PRIMARY KEY, "
                + " tipo INTEGER NOT NULL, "
                + "	nome text NOT NULL,"
                + " descrizione text );";
        return tableDefinition;
	}

	@Override
	public String toString() {
		return "ListinoSimulazione [tipo=" + tipo + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		ListinoSimulazione other = (ListinoSimulazione) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
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

}
