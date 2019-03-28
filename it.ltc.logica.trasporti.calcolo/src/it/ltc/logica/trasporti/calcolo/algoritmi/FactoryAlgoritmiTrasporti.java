package it.ltc.logica.trasporti.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.FactoryAlgoritmi;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaPercentuale;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaProporzionale;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaScaglioniRipetuti;

public class FactoryAlgoritmiTrasporti extends FactoryAlgoritmi {

	private static FactoryAlgoritmiTrasporti instance;
	
	private FactoryAlgoritmiTrasporti() {}
	
	public static FactoryAlgoritmiTrasporti getInstance() {
		if (instance == null) {
			instance = new FactoryAlgoritmiTrasporti();
		}
		return instance;
	}
	
	@Override
	protected StrategiaPercentuale getStrategiaPercentuale(String strategia) {
		return StrategiaPercentualePerTrasporti.valueOf(strategia);
	}

	@Override
	protected StrategiaProporzionale getStrategiaProporzionale(String strategia) {
		return StrategiaProporzionalePerTrasporti.valueOf(strategia);
	}

	@Override
	protected StrategiaScaglioni getStrategiaScaglioni(String strategia) {
		return StrategiaScaglioniPerTrasporti.valueOf(strategia);
	}

	@Override
	protected StrategiaScaglioniRipetuti getStrategiaScaglioniRipetuti(String strategia) {
		return StrategiaScaglioniPerTrasporti.valueOf(strategia);
	}	

}
