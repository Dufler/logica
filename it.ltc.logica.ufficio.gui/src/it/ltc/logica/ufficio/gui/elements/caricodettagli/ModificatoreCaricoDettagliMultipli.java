package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Tabella;

public class ModificatoreCaricoDettagliMultipli extends ModificatoreValoriCelle<CaricoDettaglio> {

	public ModificatoreCaricoDettagliMultipli(Tabella<CaricoDettaglio> tabella) {
		super(tabella);
	}

	@Override
	protected Object getValore(CaricoDettaglio elemento, int indiceColonna) {
//		Object valore = null;
//		if (indiceColonna == 7 && elemento != null) {
//			valore = elemento.getQuantitaDichiarata();
//		}
//		return valore;
		String valore = null;
		if (indiceColonna == 7 && elemento != null) {
			valore = Integer.toString(elemento.getQuantitaDichiarata());
		}
		return valore;
	}

	@Override
	protected void setValore(CaricoDettaglio elemento, Object valore, int indiceColonna) {
//		if (indiceColonna == 7 && valore != null && elemento != null) {
//			try { elemento.setQuantitaDichiarata((Integer) valore); } catch (Exception e) { System.out.println(e); }
//		}	
		if (indiceColonna == 7 && valore != null && elemento != null) {
			try {
				String value = (String) valore;
				elemento.setQuantitaDichiarata(Integer.parseInt(value)); 
			} catch (Exception e) { System.out.println(e); }
		}
	}

}
