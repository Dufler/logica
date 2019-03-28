package it.ltc.logica.trasporti.controller;

import java.text.DecimalFormat;

public class ReportElement {
	
	public enum UM {
		
		INTERI("", "0"),
		DECIMALI("", "0.0"),
		PERCENTUALE("", "0.0 %"),
		EURO("\u20AC", "0.00"),
		KG("Kg", "0.000"),
		MC("Mc", "0.000");
		
		private final String simbolo;
		private final DecimalFormat df;
		
		private UM(String s, String f) {
			simbolo = s;
			df = new DecimalFormat(f);
		}
		
		public String getFormattedValue(int value) {
			String testo = df.format(value) + " " + simbolo;
			return testo;
		}
		
		public String getFormattedValue(double value) {
			String testo = df.format(value) + " " + simbolo;
			return testo;
		}
	}
	
	private final String key;
	private final String value;
	
	public ReportElement(String chiave, String valore) {
		key = chiave;
		value = valore;
	}
	
	public ReportElement(String chiave, int valore, UM unitaDiMisura) {
		key = chiave;
		value = unitaDiMisura.getFormattedValue(valore);
	}
	
	public ReportElement(String chiave, double valore, UM unitaDiMisura) {
		key = chiave;
		value = unitaDiMisura.getFormattedValue(valore);
	}
	
	public ReportElement(String chiave, boolean valore) {
		key = chiave;
		if (valore) {
			value = "Si";
		} else {
			value = "No";
		}
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
