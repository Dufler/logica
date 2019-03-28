package it.ltc.logica.common.calcolo.algoritmi;

public abstract class FactoryAlgoritmi {

	public IAlgoritmo getAlgoritmo(String tipoAlgoritmo, String StrategiaAlgoritmo) {
		IAlgoritmo algoritmo;
		TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(tipoAlgoritmo);
		switch (tipo) {
			case FISSO : algoritmo = AlgoritmoFisso.getInstance(); break;
			case PERCENTUALE : algoritmo = AlgoritmoPercentuale.getInstance(getStrategiaPercentuale(StrategiaAlgoritmo)); break;
			case PROPORZIONALE : algoritmo = AlgoritmoProporzionale.getInstance(getStrategiaProporzionale(StrategiaAlgoritmo)); break;
			case SCAGLIONI : algoritmo = AlgoritmoScaglioni.getInstance(getStrategiaScaglioni(StrategiaAlgoritmo)); break;
			case SCAGLIONI_RIPETUTI : algoritmo = AlgoritmoScaglioniRipetuti.getInstance(getStrategiaScaglioniRipetuti(StrategiaAlgoritmo)); break;
			default : algoritmo = null;
		}
		return algoritmo;
	}
	
	protected abstract StrategiaPercentuale getStrategiaPercentuale(String strategia);
	
	protected abstract StrategiaProporzionale getStrategiaProporzionale(String strategia);
	
	protected abstract StrategiaScaglioni getStrategiaScaglioni(String strategia);
	
	protected abstract StrategiaScaglioniRipetuti getStrategiaScaglioniRipetuti(String strategia);
	
}
