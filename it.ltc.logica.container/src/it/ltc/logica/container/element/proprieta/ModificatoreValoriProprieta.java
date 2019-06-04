package it.ltc.logica.container.element.proprieta;

import it.ltc.database.dao.locali.ProprietaLogicaDao;
import it.ltc.logica.database.model.locale.ProprietaLogica;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;

public class ModificatoreValoriProprieta extends ModificatoreValoriCelle<ProprietaLogica> {
	
	protected final ProprietaLogicaDao managerProprieta;

	public ModificatoreValoriProprieta(TabellaProprieta tabella) {
		super(tabella);
		managerProprieta = new ProprietaLogicaDao();
	}

	@Override
	protected Object getValore(ProprietaLogica elemento, int indiceColonna) {
		Object valore;
		switch (indiceColonna) {
			case 0 : valore = elemento.getKey(); break;
			case 1 : valore = elemento.getValue(); break;
			default : valore = null;
		}
		return valore;
	}

	@Override
	protected void setValore(ProprietaLogica elemento, Object valore, int indiceColonna) {
		if (indiceColonna == 1 && valore != null && !valore.toString().isEmpty()) {
			elemento.setValue(valore.toString());
			ProprietaLogica update = managerProprieta.aggiorna(elemento);
			if (update == null) {
				DialogMessaggio.openError("Impossibile Aggiornare il valore", "Impossibile aggiornare il valore per la chiave " + elemento.getKey());
			}
		}		
	}

}
