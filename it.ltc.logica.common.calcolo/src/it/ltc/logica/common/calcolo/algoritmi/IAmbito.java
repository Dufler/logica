package it.ltc.logica.common.calcolo.algoritmi;

public abstract class IAmbito<T extends ModelDaCalcolare> {
	
	protected final int id;
	protected final String nome;
	protected final String descrizione;
	protected final String categoria;
	
	public IAmbito(int id, String nome, String descrizione, String categoria) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getCategoria() {
		return categoria;
	}
	
	@Override
	public String toString() {
		return nome;
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
		IAmbito<?> other = (IAmbito<?>) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public final double calcolaValore(T model, MVoceDiListino<T> voce) {
		double computo = 0;
		if (isApplicabile(model)) {
			computo = getValore(model, voce);
		}
		return computo;
	}
	
	protected TipoAlgoritmo getTipoAlgoritmo(MVoceDiListino<T> voce) {
		IAlgoritmo algoritmo = voce.getAlgoritmo();
		TipoAlgoritmo tipoAlgoritmo = algoritmo.getTipoAlgoritmo();
		return tipoAlgoritmo;
	}

	public abstract boolean isApplicabile(T model);
	
	public abstract double getValore(T model, MVoceDiListino<T> voce);
	
}
