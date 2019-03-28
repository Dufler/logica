package it.ltc.logica.amministrazione.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.StrategiaProporzionale;

public enum StrategiaProporzionalePerAmministrazione implements StrategiaProporzionale {
	
	COLLI("Colli"),
	PEZZI("Pezzi"),
	ORE("Ore"),
	ORDINI("Ordini"),
	PALLET("Pallet"),
	CASSA("Cassa"),
	METRO_QUADRO("Metro quadro");
	
	private final String nome;
	
	private StrategiaProporzionalePerAmministrazione(String nome) {
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
