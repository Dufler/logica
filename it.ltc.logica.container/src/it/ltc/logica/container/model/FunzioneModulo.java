package it.ltc.logica.container.model;

public class FunzioneModulo {
	
	private final String nome;
	private final String partID;
	private final String imagePath;
	
	private Modulo padre;
	
	public FunzioneModulo(String nome, String partID, String imagePath) {
		this.nome = nome;
		this.partID = partID;
		this.imagePath = imagePath;
	}

	public String getNome() {
		return nome;
	}

	public String getPartID() {
		return partID;
	}

	public String getImagePath() {
		return imagePath;
	}

	public Modulo getPadre() {
		return padre;
	}

	public void setPadre(Modulo padre) {
		this.padre = padre;
	}

	@Override
	public String toString() {
		return "Funzione [nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partID == null) ? 0 : partID.hashCode());
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
		FunzioneModulo other = (FunzioneModulo) obj;
		if (partID == null) {
			if (other.partID != null)
				return false;
		} else if (!partID.equals(other.partID))
			return false;
		return true;
	}

}
