package it.ltc.logica.trasporti.gui.elements.fatturazione;

import java.util.ArrayList;
import java.util.Date;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class FiltroSpedizioniFatturazione extends FiltroTabella<SpedizioneModel, CriteriFiltraggioSpedizioniFatturazione> {
	
	private boolean checkDatiCompleti(Boolean c) {
		boolean check = true;
		if (criteri.getDatiCompleti() != null) {
			check = criteri.getDatiCompleti().equals(c);
		}
		return check;
	}
	
	private boolean checkDataDa(Date dataSpedizione) {
		boolean check = true;
		if (criteri.getDa() != null) {
			if (dataSpedizione == null || criteri.getDa().after(dataSpedizione))
				check = false;
		}
		return check;
	}
	
	private boolean checkDataA(Date dataSpedizione) {
		boolean check = true;
		if (criteri.getA() != null) {
			if (dataSpedizione == null || criteri.getA().before(dataSpedizione))
				check = false;
		}
		return check;
	}
	
	private boolean checkContrassegno(Boolean c) {
		boolean check = true;
		if (criteri.getContrassegno() != null) {
			check = criteri.getContrassegno().equals(c);
		}
		return check;
	}
	
	private boolean checkRiferimento(Spedizione spedizione) {
		boolean check = true;
		if (criteri.getRiferimento() != null && !criteri.getRiferimento().isEmpty()) {
			check = false;
			ArrayList<String> listaElementiDiRicerca = getElementiDiRicerca(spedizione);
			for (String elementoDiRicerca : listaElementiDiRicerca) {
				check = elementoDiRicerca.toUpperCase().contains(criteri.getRiferimento().toUpperCase());
				if (check)
					break;
			}
		}
		return check;
	}
	
	private ArrayList<String> getElementiDiRicerca(Spedizione spedizione) {
		ArrayList<String> listaCheck = new ArrayList<String>();
		String letteraDiVettura = spedizione.getLetteraDiVettura();
		if (letteraDiVettura != null && !letteraDiVettura.isEmpty())
			listaCheck.add(letteraDiVettura);
		String riferimentoCliente = spedizione.getRiferimentoCliente();
		if (riferimentoCliente != null && !riferimentoCliente.isEmpty())
			listaCheck.add(riferimentoCliente);
		String riferimentoMittente = spedizione.getRiferimentoMittente();
		if (riferimentoMittente != null && !riferimentoMittente.isEmpty())
			listaCheck.add(riferimentoMittente);
		String destinatario = spedizione.getRagioneSocialeDestinatario();
		if (destinatario != null && !destinatario.isEmpty())
			listaCheck.add(destinatario);
		return listaCheck;
	}

	@Override
	protected boolean checkElemento(SpedizioneModel model) {
		boolean toShow;
		Spedizione spedizione = model.getSpedizione();
		toShow = checkDataDa(spedizione.getDataPartenza()) && checkDataA(spedizione.getDataPartenza());
		if (toShow) {
			toShow = checkContrassegno(spedizione.getContrassegno());
			if (toShow) {
				toShow = checkRiferimento(spedizione);
				if (toShow) {
					toShow = checkDatiCompleti(model.isDatiPerCalcoloCompleti());
				}
			}
		}
		return toShow;
	}

}
