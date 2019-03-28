package it.ltc.logica.common.controller.ingressi;

import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ingressi.Stagione;
import it.ltc.logica.gui.task.DialogProgresso;


public class ControllerStagioni extends ControllerReadOnly<Stagione> {
	
	public static final String title = "Stagioni";
	public static final String resource = "stagione";

	private final HashMap<Integer, List<Stagione>> stagioni;
	
	private static ControllerStagioni instance;

	private ControllerStagioni() {
		super(title, resource, Stagione[].class);
		stagioni = new HashMap<>();
	}

	public static synchronized ControllerStagioni getInstance() {
		if (null == instance) {
			instance = new ControllerStagioni();
		}
		return instance;
	}
	
	protected List<Stagione> scaricaTipi(Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoCaricamentoDati<Stagione> processo = new ProcessoCaricamentoDati<Stagione>(title, resource, c, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<Stagione> stagioni = processo.getEsito() ? processo.getLista() : null;
		return stagioni;
	}
	
	public Stagione getStagioneDaCodice(Commessa commessa, String codice) {
		Stagione magazzino = null;
		for (Stagione m : getStagioni(commessa)) {
			if (m.getCodice().equals(codice)) {
				magazzino = m;
				break;
			}
		}
		return magazzino;
	}
	
	public List<Stagione> getStagioni(Commessa commessa) {
		if (!stagioni.containsKey(commessa.getId())) {
			List<Stagione> lista = scaricaTipi(commessa);
			if (lista != null) {
				stagioni.put(commessa.getId(), lista);
			}
		}
		return stagioni.get(commessa.getId());
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Stagione> lista) {
		//DO NOTHING!
		return false;
	}
	
}
