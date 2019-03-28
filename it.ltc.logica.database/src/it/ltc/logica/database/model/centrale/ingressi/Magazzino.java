package it.ltc.logica.database.model.centrale.ingressi;

/**
 * Classe che modella un magazzino.
 * E' importante notare che il concetto di magazzino equivale quello di scelta (per molti clienti) l'idea che c'è alla base è un insieme di merce che condivide delle caratteristiche.
 * Carichi e Ordini vengono predisposti su magazzini specifici.
 * @author Damiano
 *
 */
public class Magazzino {
	
	private String codiceLTC;
	private String codiceCliente;
	private String descrizione;
	
	public Magazzino() {}

	public String getCodiceLTC() {
		return codiceLTC;
	}

	public void setCodiceLTC(String codiceLTC) {
		this.codiceLTC = codiceLTC;
	}

	public String getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return codiceLTC;
	}

}
