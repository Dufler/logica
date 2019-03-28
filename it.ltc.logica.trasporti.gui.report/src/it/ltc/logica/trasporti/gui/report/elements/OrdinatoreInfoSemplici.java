package it.ltc.logica.trasporti.gui.report.elements;

import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.trasporti.controller.ReportElement;

public class OrdinatoreInfoSemplici extends Ordinatore<ReportElement> {

	@Override
	protected int compare(ReportElement t1, ReportElement t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareString(t1.getKey(), t2.getKey()); break;
			case 2 : compare = compareString(t1.getValue(), t2.getValue()); break;
			default : compare = 0;
		}
		return compare;
	}

}
