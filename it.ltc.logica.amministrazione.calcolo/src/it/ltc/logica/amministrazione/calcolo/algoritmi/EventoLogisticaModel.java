package it.ltc.logica.amministrazione.calcolo.algoritmi;

import it.ltc.logica.amministrazione.calcolo.ambiti.IAmbitoLogistica;

public class EventoLogisticaModel {
	
	private final IAmbitoLogistica evento;
	private final int colli;
	private final int pezzi;
	private final double tempo;
	
	public EventoLogisticaModel(IAmbitoLogistica evento, int colli, int pezzi, double tempo) {
		this.evento = evento;
		this.colli = colli;
		this.pezzi = pezzi;
		this.tempo = tempo;
	}
	
	@Override
	public String toString() {
		return "EventoLogisticaModel [evento=" + evento + ", colli=" + colli + ", pezzi=" + pezzi + ", tempo=" + tempo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoLogisticaModel other = (EventoLogisticaModel) obj;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
			return false;
		return true;
	}

	public IAmbitoLogistica getEvento() {
		return evento;
	}
	
	public int getColli() {
		return colli;
	}

	public int getPezzi() {
		return pezzi;
	}

	public double getTempo() {
		return tempo;
	}
	
	public static EventoLogisticaModel somma(EventoLogisticaModel e1, EventoLogisticaModel e2) {
		IAmbitoLogistica a1 = e1.getEvento();
		IAmbitoLogistica a2 = e2.getEvento();
		if (!a1.equals(a2))
			throw new RuntimeException("Gli ambiti non sono identici, impossibile sommarli.");
		EventoLogisticaModel somma = new EventoLogisticaModel(a1, e1.colli + e2.getColli(), e1.getPezzi() + e2.getPezzi(), e1.getTempo() + e2.getTempo());
		return somma;
	}

}
