package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.common.calcolo.algoritmi.AlgoritmoPercentuale;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.common.calcolo.algoritmi.MVocePercentuale;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.algoritmi.StrategiaPercentualePerTrasporti;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasportoConValore;

public class ContrassegnoSuperioreA extends IAmbitoTrasportoConValore {
	
	protected final double sogliaContrassegno;

	public ContrassegnoSuperioreA(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
		sogliaContrassegno = parseValue(valore);
	}
	
	private double parseValue(String valore) {
		double value;
		try {
			value = Double.parseDouble(valore);
		} catch(NumberFormatException e) {
			value = 0;
		}
		return value;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Contrassegno contrassegno = spedizione.getContrassegno();
		boolean applicabile = (contrassegno != null && contrassegno.getValore() != null && contrassegno.getValore() > sogliaContrassegno);
		return applicabile;
	}

	@Override
	protected double getValorePercentuale(SpedizioneModel model, MVoceDiListino<SpedizioneModel> voce) {
		double costo;
		MVocePercentuale dettaglioPercentuale = (MVocePercentuale) voce.getDettagli();
		AlgoritmoPercentuale ap = (AlgoritmoPercentuale) voce.getAlgoritmo();
		StrategiaPercentualePerTrasporti strategia = (StrategiaPercentualePerTrasporti) ap.getStrategia();
		if (strategia == StrategiaPercentualePerTrasporti.SOLO_CONTRASSEGNO) {
			Contrassegno contrassegno = model.getContrassegno();
			double valoreContrassegno = contrassegno.getValore();
			double percentuale = dettaglioPercentuale.getValore();
			Double minimo = dettaglioPercentuale.getMinimo();
			Double massimo = dettaglioPercentuale.getMassimo();
			costo = ap.calcolaCosto(valoreContrassegno, percentuale, minimo, massimo);
		} else {
			costo = 0;
		}
		return costo;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.CONTRASSEGNO;
	}

}
