package it.ltc.logica.common.calcolo.algoritmi;

public enum TipoScaglioni {
	
	RAPPORTO_1_100 (100, "Rapporto 1:100"),
	RAPPORTO_1_150 (150, "Rapporto 1:150"),
	RAPPORTO_1_167 (167, "Rapporto 1:167"),
	RAPPORTO_1_200 (200, "Rapporto 1:200"),
	RAPPORTO_1_250 (250, "Rapporto 1:250"),
	RAPPORTO_1_300 (300, "Rapporto 1:300");
	
	private final int rapporto;
	private final String nome;
	
	private TipoScaglioni(int rapporto, String nome) {
		this.rapporto = rapporto;
		this.nome = nome;
	}
	
	public int getRapporto() {
		return rapporto;
	}

	public String getNome() {
		return nome;
	}

	public String toString() {
		return nome;
	}

}
