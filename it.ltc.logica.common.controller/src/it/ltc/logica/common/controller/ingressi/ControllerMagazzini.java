package it.ltc.logica.common.controller.ingressi;

import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ingressi.Magazzino;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerMagazzini extends ControllerReadOnly<Magazzino> {
	
	public static final String title = "Magazzini";
	public static final String resource = "magazzino";

	private final HashMap<Integer, Magazzino> magazziniDefault;
	private final HashMap<Integer, List<Magazzino>> magazzini;
	
	private static ControllerMagazzini instance;

	private ControllerMagazzini() {
		super(title, resource, Magazzino[].class);
		magazzini = new HashMap<>();
		magazziniDefault = new HashMap<>();
	}

	public static synchronized ControllerMagazzini getInstance() {
		if (null == instance) {
			instance = new ControllerMagazzini();
		}
		return instance;
	}
	
	protected List<Magazzino> scaricaTipi(Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoCaricamentoDati<Magazzino> processo = new ProcessoCaricamentoDati<Magazzino>(title, resource, c, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<Magazzino> magazzini = processo.getEsito() ? processo.getLista() : null;
		return magazzini;
	}
	
	public Magazzino getMagazzinoDaCodiceLTC(Commessa commessa, String codiceLTC) {
		Magazzino magazzino = null;
		for (Magazzino m : getMagazzini(commessa)) {
			if (m.getCodiceLTC().equals(codiceLTC)) {
				magazzino = m;
				break;
			}
		}
		return magazzino;
	}
	
	public Magazzino getMagazzinoDefault(Commessa commessa) {
		//Controllo se ho già determinato qual è, se non cè l'ho ancora vado a cercarlo.
		if (!magazziniDefault.containsKey(commessa.getId())) {
			Magazzino magazzino = null;
			for (Magazzino m : getMagazzini(commessa)) {
				if (m.isMagazzinoDefault()) {
					magazzino = m;
					break;
				}
			}
			magazziniDefault.put(commessa.getId(), magazzino);
		}
		return magazziniDefault.get(commessa.getId());
	}
	
	public List<Magazzino> getMagazzini(Commessa commessa) {
		if (!magazzini.containsKey(commessa.getId())) {
			List<Magazzino> lista = scaricaTipi(commessa);
			if (lista != null) {
				magazzini.put(commessa.getId(), lista);
			}
		}
		return magazzini.get(commessa.getId());
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Magazzino> lista) {
		//DO NOTHING!
		return false;
	}

}
