package it.ltc.logica.database.model.centrale.trasporti;

public class TipoContrassegno {
	
	private String codice;
	private String nome;
	
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
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
		TipoContrassegno other = (TipoContrassegno) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public static List<TipoContrassegno> getTipiContrassegno() {
//		if (listaTipiContrassegno.isEmpty()) {
//			listaTipiContrassegno.addAll(manager.getEntities());
//		}
//		return listaTipiContrassegno;
//	}
//	
//	public static TipoContrassegno getTipoContrassegno(String codice) {
//		TipoContrassegno tipo = null;
//		for (TipoContrassegno t : getTipiContrassegno()) {
//			if (t.getCodice().equals(codice)) {
//				tipo = t;
//				break;
//			}
//		}
//		return tipo;
//	}

}
