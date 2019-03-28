package it.ltc.logica.cdg.gui.costiricavi.elements.generici;

import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici.DriverRipartizione;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioCostiRicaviGenerici extends CriteriFiltraggio {
	
	private final String testo;
	private final Integer sede;
	private final DriverRipartizione driver;
	
	public CriteriFiltraggioCostiRicaviGenerici(String testo, Integer sede, DriverRipartizione driver) {
		this.testo = testo;
		this.sede = sede;
		this.driver = driver;
	}

	public String getTesto() {
		return testo;
	}

	public Integer getSede() {
		return sede;
	}

	public DriverRipartizione getDriver() {
		return driver;
	}

}
