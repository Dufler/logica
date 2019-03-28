package it.ltc.logica.common.controller.uscite;

import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ordini.OrdineTipo;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerOrdineTipi extends ControllerReadOnly<OrdineTipo> {
	
	public static final String title = "Tipi di ordine";
	public static final String resource = "ordine/tipo";

	private final HashMap<Integer, List<OrdineTipo>> tipi;
	
	private static ControllerOrdineTipi instance;

	private ControllerOrdineTipi() {
		super(title, resource, OrdineTipo[].class);
		tipi = new HashMap<>();
	}

	public static synchronized ControllerOrdineTipi getInstance() {
		if (null == instance) {
			instance = new ControllerOrdineTipi();
		}
		return instance;
	}
	
	protected List<OrdineTipo> scaricaTipi(Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoCaricamentoDati<OrdineTipo> processo = new ProcessoCaricamentoDati<OrdineTipo>(title, resource, c, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<OrdineTipo> tipi = processo.getEsito() ? processo.getLista() : null;
		return tipi;
	}
	
	public OrdineTipo getTipo(Commessa commessa, String tipo) {
		OrdineTipo tipoCarico = null;
		for (OrdineTipo t : getTipi(commessa)) {
			if (t.getCodice().equals(tipo)) {
				tipoCarico = t;
				break;
			}
		}
		return tipoCarico;
	}
	
	public List<OrdineTipo> getTipi(Commessa commessa) {
		if (!tipi.containsKey(commessa.getId())) {
			List<OrdineTipo> lista = scaricaTipi(commessa);
			if (lista != null) {
				tipi.put(commessa.getId(), lista);
			}
		}
		return tipi.get(commessa.getId());
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<OrdineTipo> lista) {
		//DO NOTHING!
		return false;
	}

}
