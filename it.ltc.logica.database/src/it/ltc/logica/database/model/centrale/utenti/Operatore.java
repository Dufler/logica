package it.ltc.logica.database.model.centrale.utenti;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the operatore database table.
 * 
 */
@Entity
@Table(name="operatore")
@NamedQuery(name="Operatore.findAll", query="SELECT o FROM Operatore o")
public class Operatore implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=45)
	private String username;

	@Column(nullable=false)
	private int associazione;

	public Operatore() {}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAssociazione() {
		return this.associazione;
	}

	public void setAssociazione(int associazione) {
		this.associazione = associazione;
	}

}