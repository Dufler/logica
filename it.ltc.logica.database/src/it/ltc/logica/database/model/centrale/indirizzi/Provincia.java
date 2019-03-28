package it.ltc.logica.database.model.centrale.indirizzi;

public class Provincia {

	private String nome;
	private String sigla;
	private String regione;
	
	public String toString() {
		return sigla + " - " + nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Provincia other = (Provincia) obj;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

//	public static List<Provincia> getProvince() {
//		if (listaProvince.isEmpty()) {
//			listaProvince.addAll(manager.getEntities());
//		}
//		return listaProvince;
//	}
//	
//	public static Provincia getProvincia(String sigla) {
//		Provincia provincia = null;
//		for (Provincia p : getProvince()) {
//			if (p.getSigla().equalsIgnoreCase(sigla)) {
//				provincia = p;
//				break;
//			}
//		}
//		return provincia;
//	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

}
