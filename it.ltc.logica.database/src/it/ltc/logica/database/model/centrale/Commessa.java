package it.ltc.logica.database.model.centrale;

public class Commessa {

	private int id;
	private int idCliente;
	private int idSede;
	private String nome;
	private String descrizione;
//	private String vecchioDbNome;
//	private String vecchioDbIndirizzo;
	private String nomeRisorsa;
	private boolean legacy;
	
	public String toString() {
		return (nome != null) ? nome : "";
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
		Commessa other = (Commessa) obj;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
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

//	public String getVecchioDbNome() {
//		return vecchioDbNome;
//	}
//
//	public void setVecchioDbNome(String nomeVecchioDB) {
//		this.vecchioDbNome = nomeVecchioDB;
//	}
//
//	public String getVecchioDbIndirizzo() {
//		return vecchioDbIndirizzo;
//	}
//
//	public void setVecchioDbIndirizzo(String indirizzoVecchioDB) {
//		this.vecchioDbIndirizzo = indirizzoVecchioDB;
//	}

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
