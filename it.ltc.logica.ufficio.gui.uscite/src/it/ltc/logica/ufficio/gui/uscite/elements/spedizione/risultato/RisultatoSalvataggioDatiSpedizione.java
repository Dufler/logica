package it.ltc.logica.ufficio.gui.uscite.elements.spedizione.risultato;

public class RisultatoSalvataggioDatiSpedizione {
	
	private String riferimento;
	private String ordine;
	private boolean risultato;
	
	public RisultatoSalvataggioDatiSpedizione() {}

	public String getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public boolean isRisultato() {
		return risultato;
	}

	public void setRisultato(boolean risultato) {
		this.risultato = risultato;
	}

}
