package it.ltc.logica.common.controller.ingressi;

import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTipo;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerCarichiTipi extends ControllerReadOnly<CaricoTipo> {
	
	public static final String title = "Tipi di carico";
	public static final String resource = "carico/tipo";

	private final HashMap<Integer, List<CaricoTipo>> tipi;
	
	private static ControllerCarichiTipi instance;

	private ControllerCarichiTipi() {
		super(title, resource, CaricoTipo[].class);
		tipi = new HashMap<>();
	}

	public static synchronized ControllerCarichiTipi getInstance() {
		if (null == instance) {
			instance = new ControllerCarichiTipi();
		}
		return instance;
	}
	
	protected List<CaricoTipo> scaricaTipi(Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoCaricamentoDati<CaricoTipo> processo = new ProcessoCaricamentoDati<CaricoTipo>(title, resource, c, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<CaricoTipo> tipi = processo.getEsito() ? processo.getLista() : null;
		return tipi;
	}
	
	public CaricoTipo getTipo(Commessa commessa, String tipo) {
		CaricoTipo tipoCarico = null;
		for (CaricoTipo t : getTipi(commessa)) {
			if (t.getCodice().equals(tipo)) {
				tipoCarico = t;
				break;
			}
		}
		return tipoCarico;
	}
	
	public List<CaricoTipo> getTipi(Commessa commessa) {
		if (!tipi.containsKey(commessa.getId())) {
			List<CaricoTipo> lista = scaricaTipi(commessa);
			if (lista != null) {
				tipi.put(commessa.getId(), lista);
			}
		}
		return tipi.get(commessa.getId());
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CaricoTipo> lista) {
		//DO NOTHING!
		return false;
	}

}
