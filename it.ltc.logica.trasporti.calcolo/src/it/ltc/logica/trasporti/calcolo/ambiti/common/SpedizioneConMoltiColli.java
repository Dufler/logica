package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.common.calcolo.algoritmi.AlgoritmoProporzionale;
import it.ltc.logica.common.calcolo.algoritmi.AlgoritmoScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.AlgoritmoScaglioniRipetuti;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.common.calcolo.algoritmi.MVoceProporzionale;
import it.ltc.logica.common.calcolo.algoritmi.MVoceScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.MVoceScaglioniRipetuti;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.algoritmi.StrategiaProporzionalePerTrasporti;
import it.ltc.logica.trasporti.calcolo.algoritmi.StrategiaScaglioniPerTrasporti;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasportoConValore;

public abstract class SpedizioneConMoltiColli extends IAmbitoTrasportoConValore {

	private final int sogliaColli;
	
	public SpedizioneConMoltiColli(SottoAmbitoFattura ambito, String valore) {
		super(ambito, valore);
		sogliaColli = parseValue(valore);
	}
	
	private int parseValue(String valore) {
		int value;
		try {
			value = Integer.parseInt(valore);
		} catch (NumberFormatException e) {
			value = 0;
		}
		return value;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_EXTRA;
	}

	@Override
	public boolean isApplicabile(SpedizioneModel model) {
		int colli = model.getSpedizione().getColli() > 0 ? model.getSpedizione().getColli() : 1;
		boolean applicabile = colli > sogliaColli;
		return applicabile;
	}
	
	public double getValoreProporzionale(SpedizioneModel model, MVoceDiListino<SpedizioneModel> voce) {
		MVoceProporzionale dettaglioProporzionale = (MVoceProporzionale) voce.getDettagli();
		AlgoritmoProporzionale ap = (AlgoritmoProporzionale) voce.getAlgoritmo();
		Spedizione spedizione = model.getSpedizione();
		StrategiaProporzionalePerTrasporti tipoProporzionale = (StrategiaProporzionalePerTrasporti) ap.getStrategia();
		double elementi;
		switch (tipoProporzionale) {
			case COLLI : elementi = spedizione.getColli() - sogliaColli; break;
			default : elementi = 0;
		}
		double valorePerElemento = dettaglioProporzionale.getValore();
		Double minimo = dettaglioProporzionale.getMinimo();
		Double massimo = dettaglioProporzionale.getMassimo();
		double costo = ap.calcolaCosto(valorePerElemento, elementi, minimo, massimo);
		return costo;
	}
	
	public double getValoreScaglioni(SpedizioneModel model, MVoceDiListino<SpedizioneModel> voce) {
		MVoceScaglioni dettaglioScaglioni = (MVoceScaglioni) voce.getDettagli();
		AlgoritmoScaglioni as = (AlgoritmoScaglioni) voce.getAlgoritmo();
		Spedizione spedizione = model.getSpedizione();
		StrategiaScaglioniPerTrasporti tipoScaglioni = (StrategiaScaglioniPerTrasporti) as.getTipo();
		double valore;
		switch(tipoScaglioni) {
			case COLLI : valore = spedizione.getColli() - sogliaColli; break;
			default : valore = 0;
		}
		double costo = as.calcolaCosto(valore, dettaglioScaglioni.getScaglioni());
		return costo;
	}
	
	public double getValoreScaglioniRipetuti(SpedizioneModel model, MVoceDiListino<SpedizioneModel> voce) {
		MVoceScaglioniRipetuti dettaglioScaglioniRipetuti = (MVoceScaglioniRipetuti) voce.getDettagli();
		AlgoritmoScaglioniRipetuti asr = (AlgoritmoScaglioniRipetuti) voce.getAlgoritmo();
		Spedizione spedizione = model.getSpedizione();
		StrategiaScaglioniPerTrasporti tipoScaglioniRipetuti = (StrategiaScaglioniPerTrasporti) asr.getTipo();
		double valore;
		switch(tipoScaglioniRipetuti) {
			//Qui non è necessario togliere la soglia, verrà decrementato dall'algoritmo dalla soglia minima.
			case COLLI : valore = spedizione.getColli(); break;
			default : valore = 0;
		}
		double intervallo = dettaglioScaglioniRipetuti.getIntervallo();
		double costoSingoloScaglione = dettaglioScaglioniRipetuti.getValore();
		Double minimo = dettaglioScaglioniRipetuti.getSogliaMinima();
		//Nel caso in cui non abbia inserito un minimo allora gli imposto la soglia
		if (minimo == null || minimo < 1) {
			minimo = sogliaColli * 1.0;
		}
		Double massimo = dettaglioScaglioniRipetuti.getSogliaMassima();
		double costo = asr.calcolaCosto(valore, intervallo, costoSingoloScaglione, minimo, massimo);
		return costo;
	}

}
