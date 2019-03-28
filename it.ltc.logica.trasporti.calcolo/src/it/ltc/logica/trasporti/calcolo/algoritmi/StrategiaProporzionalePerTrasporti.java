package it.ltc.logica.trasporti.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.StrategiaProporzionale;

public enum StrategiaProporzionalePerTrasporti implements StrategiaProporzionale {
	
	COLLI("Colli"),
	PEZZI("Pezzi");
	
	private final String nome;
	
	private StrategiaProporzionalePerTrasporti(String nome) {
		this.nome = nome;
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
