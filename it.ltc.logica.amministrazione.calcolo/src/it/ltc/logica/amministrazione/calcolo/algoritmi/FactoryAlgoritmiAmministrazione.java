package it.ltc.logica.amministrazione.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.FactoryAlgoritmi;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaPercentuale;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaProporzionale;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.StrategiaScaglioniRipetuti;

public class FactoryAlgoritmiAmministrazione extends FactoryAlgoritmi {

	private static FactoryAlgoritmiAmministrazione instance;
	
	private FactoryAlgoritmiAmministrazione() {}
	
	public static FactoryAlgoritmiAmministrazione getInstance() {
		if (instance == null) {
			instance = new FactoryAlgoritmiAmministrazione();
		}
		return instance;
	}
	
	@Override
	protected StrategiaPercentuale getStrategiaPercentuale(String strategia) {
		return StrategiaPercentualePerAmministrazione.valueOf(strategia);
	}

	@Override
	protected StrategiaProporzionale getStrategiaProporzionale(String strategia) {
		return StrategiaProporzionalePerAmministrazione.valueOf(strategia);
	}

	@Override
	protected StrategiaScaglioni getStrategiaScaglioni(String strategia) {
		return StrategiaScaglioniPerAmministrazione.valueOf(strategia);
	}

	@Override
	protected StrategiaScaglioniRipetuti getStrategiaScaglioniRipetuti(String strategia) {
		return StrategiaScaglioniPerAmministrazione.valueOf(strategia);
	}

}
