package it.ltc.logica.amministrazione.calcolo.algoritmi;

import java.util.Date;
import java.util.HashMap;

import it.ltc.logica.amministrazione.calcolo.ambiti.IAmbitoLogistica;
import it.ltc.logica.common.calcolo.algoritmi.IAmbito;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.common.calcolo.algoritmi.ModelDaCalcolare;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;

public class LogisticaModel extends ModelDaCalcolare {
	
	private final String riferimento;
	private final Date data;
	private final String tipo;
	
	private final HashMap<IAmbitoLogistica, EventoLogisticaModel> eventi;
	
	public LogisticaModel(String riferimento, Date data, String tipo) {
		this.riferimento = riferimento;
		this.data = data;
		this.tipo = tipo;
		this.eventi = new HashMap<IAmbitoLogistica, EventoLogisticaModel>();
	}
	
	public void addEvento(EventoLogisticaModel evento) {
		//Se ne ho già uno dello stesso tipo vado ad incrementarne le quantità.
		if (eventi.containsKey(evento.getEvento())) {
			EventoLogisticaModel esistente = eventi.get(evento.getEvento());
			evento = EventoLogisticaModel.somma(evento, esistente);
		}
		eventi.put(evento.getEvento(), evento);
	}
	
	public boolean isAmbitoApplicabile(IAmbitoLogistica ambito) {
		boolean applicabile = eventi.containsKey(ambito);
		return applicabile;
	}

	public double getCostoTotale(Scopo scopo) {
		double totale = 0;
		if (calcolo != null)
			for (VoceCalcolata voce : calcolo.getVoci()) {
				totale += voce.getCosto();
			}
		return totale;
	}

	public EventoLogisticaModel getEvento(IAmbito<LogisticaModel> ambito) {
		return eventi.get(ambito);
	}

	@Override
	public String toString() {
		return "LogisticaModel [eventi=" + eventi + "]";
	}

	public String getRiferimento() {
		return riferimento;
	}

	public Date getData() {
		return data;
	}

	public String getTipo() {
		return tipo;
	}

}
