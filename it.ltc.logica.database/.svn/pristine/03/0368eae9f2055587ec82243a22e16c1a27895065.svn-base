package it.ltc.logica.database.model.centrale.fatturazione;

/**
 * Definisce le possibili modalità di pagamento di una fattura.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public enum ModalitaPagamentoFattura {
	
	B30("Bonifico Bancario 30 gg d.f.f.m."),
	B60("Bonifico Bancario 60 gg d.f.f.m."),
	B6010("Bonifico Bancario 60+10 gg d.f.f.m."),
	B90("Bonifico Bancario 90 gg d.f.f.m."),
	B9010("Bonifico Bancario 90+10 gg d.f.f.m."),
	PA("Pagamento anticipato"),
	PE("Pagamento effettuato"),
	R30("Riba 30 gg d.f.f.m."),
	R60("Riba 60 gg d.f.f.m."),
	R6010("Riba 60+10 gg d.f.f.m."),
	R90("Riba 90 gg d.f.f.m."),
	R9010("Riba 90+10 gg d.f.f.m."),
	RD("Rimessa diretta vista fattura");
	
	private final String descrizione;
	
	private ModalitaPagamentoFattura(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}		

}
