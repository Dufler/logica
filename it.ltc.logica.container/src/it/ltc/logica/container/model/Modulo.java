package it.ltc.logica.container.model;

import java.util.HashSet;
import java.util.Set;

public class Modulo implements Comparable<Modulo> {
	
	private final String nome;
	private final String perspectiveID;
	private final String imagePath;
	private final int colorSWT;
	private final Set<FunzioneModulo> funzioni;
	private final int posizione;
	
	public Modulo(String nome, String perspectiveID, String imagePath, int colorSWT, int poizione) {
		this.nome = nome;
		this.perspectiveID = perspectiveID;
		this.imagePath = imagePath;
		this.colorSWT = colorSWT;
		this.funzioni = new HashSet<>();
		this.posizione = poizione;
	}

	public String getNome() {
		return nome;
	}

	public String getPerspectiveID() {
		return perspectiveID;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public int getColorSWT() {
		return colorSWT;
	}

	public int getPosizione() {
		return posizione;
	}

	public Set<FunzioneModulo> getFunzioni() {
		return funzioni;
	}
	
	public void addFunzione(FunzioneModulo funzione) {
		funzioni.add(funzione);
		funzione.setPadre(this);
	}

	@Override
	public String toString() {
		return "Feature [nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perspectiveID == null) ? 0 : perspectiveID.hashCode());
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
		Modulo other = (Modulo) obj;
		if (perspectiveID == null) {
			if (other.perspectiveID != null)
				return false;
		} else if (!perspectiveID.equals(other.perspectiveID))
			return false;
		return true;
	}

	@Override
	public int compareTo(Modulo o) {
		Integer p1 = posizione;
		Integer p2 = o.posizione;
		int compare = p1.compareTo(p2);
		return compare;
	}

}
