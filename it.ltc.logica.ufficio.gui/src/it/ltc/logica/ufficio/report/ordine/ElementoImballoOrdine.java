package it.ltc.logica.ufficio.report.ordine;

import it.ltc.logica.database.model.centrale.ordini.ImballoCollo;
import it.ltc.logica.database.model.centrale.ordini.ImballoProdotto;

public class ElementoImballoOrdine {
	
	private final String sku;
	private final String descrizione;
	private final String taglia;
	private final String collo;
	private final String formatoCollo;
	private final double pesoCollo;
	private final double volumeCollo;
	private final int quantitaImballata;
	
	public ElementoImballoOrdine(ImballoCollo collo, ImballoProdotto prodotto) {
		this.sku = prodotto.getProdotto();
		this.descrizione = prodotto.getDescrizione();
		this.taglia = prodotto.getTaglia();
		this.collo = collo.getRiferimento();
		this.formatoCollo =  getFormato(collo);
		this.pesoCollo = collo.getPeso();
		this.volumeCollo = collo.getVolume();
		this.quantitaImballata = prodotto.getQuantitaImballata();
	}
	
	private String getFormato(ImballoCollo collo) {
		String formato = "";
		if (collo.getFormato() != null) {
			formato += collo.getFormato();
		}
		Double h = collo.getH();
		Double l = collo.getL();
		Double z = collo.getZ();
		if (h != null && h > 0 && l != null && l > 0 && z != null && z > 0)
			formato += " (" + h + " x " + l + " x " + z + ")";
		return formato;
	}

	public String getSku() {
		return sku;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getTaglia() {
		return taglia;
	}

	public String getCollo() {
		return collo;
	}

	public String getFormatoCollo() {
		return formatoCollo;
	}

	public double getPesoCollo() {
		return pesoCollo;
	}

	public double getVolumeCollo() {
		return volumeCollo;
	}

	public int getQuantitaImballata() {
		return quantitaImballata;
	}

}
