package it.ltc.logica.common.calcolo.costanti;

public enum IVA {
	
	_10("_10", "IVA 10%", 10.0),
	_22("_22", "IVA 22%", 22.0),
	E146("E146", "Esente Art. 146(1)(e)-Dir. 2006/112EC", 0.0),
	EB("EB", "Art. 15 escluso", 0.0),
	ES("ES", "Art. 10 esente", 0.0),
	ES7("ES7", "Esente art. 9/IC dpr 633/72", 0.0),
	FVI("FVI", "Fuori campo iva art. 1 2 3", 0.0),
	NI7("NI7", "Non imponibile art. 7-dpr 633/72 26/10/72", 0.0),
	VLI("VLI", "Vendita clienti con lett. int. art. 8 c.2", 0.0);
	
	private final String codice;
	private final String descrizione;
	private final double percentuale;

	private IVA(String codice, String descrizione, double percentuale) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.percentuale = percentuale;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}

	public String getCodice() {
		return codice;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public double getPercentuale() {
		return percentuale;
	}
	
}
