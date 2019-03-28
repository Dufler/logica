package it.ltc.logica.trasporti.calcolo.ambiti.common;

import it.ltc.logica.common.calcolo.algoritmi.AlgoritmoScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.algoritmi.StrategiaScaglioniPerTrasporti;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public abstract class NoloItalia extends IAmbitoTrasporto {

	public static final String ITALIA = "ITA";
	public static final String CODICE = "NOLO";
	
	public NoloItalia(SottoAmbitoFattura ambito) {
		super(ambito);
	}

	@Override
	public boolean isApplicabile(SpedizioneModel spedizione) {
		Indirizzo destinazione = spedizione.getDestinazione();
		boolean applicabile = ITALIA.equals(destinazione.getNazione());
		return applicabile;
	}
	
	@Override
	public double getValoreScaglioni(SpedizioneModel model, MVoceDiListino<SpedizioneModel> voce) {
		double valore = super.getValoreScaglioni(model, voce);
		//Nel caso in cui sto calcolando il nolo sui colli moltiplicato il risultato ottenuto per il numero dei colli
		AlgoritmoScaglioni as = (AlgoritmoScaglioni) voce.getAlgoritmo();
		StrategiaScaglioniPerTrasporti tipoScaglioni = (StrategiaScaglioniPerTrasporti) as.getTipo();
		if (tipoScaglioni == StrategiaScaglioniPerTrasporti.COLLI) {
			Spedizione spedizione = model.getSpedizione();
			int colli = spedizione.getColli();
			valore = colli * valore;
		}
		return valore;
	}
	
	@Override
	public String toString() {
		return CODICE;
	}
	
	@Override
	public Tipo getTipo() {
		return Tipo.NOLO_BASE;
	}

}
