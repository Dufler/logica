package it.ltc.logica.trasporti.gui.elements.spedizione;

import java.util.ArrayList;
import java.util.Date;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroSpedizioni extends FiltroTabella<Spedizione, CriteriFiltraggioSpedizione> {
	
	private boolean checkDatiCompleti(Boolean c) {
		boolean check = true;
		if (criteri.getDatiCompleti() != null) {
			check = criteri.getDatiCompleti().equals(c);
		}
		return check;
	}
	
	private boolean checkGiacenza(Boolean c) {
		boolean check = true;
		if (criteri.getGiacenza() != null) {
			check = criteri.getGiacenza().equals(c);
		}
		return check;
	}
	
	private boolean checkRitardo(Boolean c) {
		boolean check = true;
		if (criteri.getRitardo() != null) {
			check = criteri.getRitardo().equals(c);
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
	
	private boolean checkCommessa(Integer id) {
		boolean check = true;
		if (criteri.getIdCommessa() != null && !criteri.getIdCommessa().equals(id)) {
			check = false;
		}
		return check;
	}
	
	private boolean checkCorriere(Integer id) {
		boolean check = true;
		if (criteri.getIdCorriere() != null && !criteri.getIdCorriere().equals(id)) {
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
	protected boolean checkElemento(Spedizione spedizione) {
		boolean toShow;
		toShow = checkDataDa(spedizione.getDataPartenza()) && checkDataA(spedizione.getDataPartenza());
		if (toShow) {
			toShow = checkCommessa(spedizione.getIdCommessa());
			if (toShow) {
				toShow = checkCorriere(spedizione.getIdCorriere());
				if (toShow) {
					toShow = checkContrassegno(spedizione.getContrassegno());
					if (toShow) {
						toShow = checkRiferimento(spedizione);
						if (toShow) {
							toShow = checkDatiCompleti(spedizione.getDatiCompleti());
							if (toShow) {
								toShow = checkGiacenza(spedizione.getGiacenza());
								if (toShow) {
									toShow = checkRitardo(spedizione.getInRitardo());
								}
							}
						}
					}
				}
			}
		}
		return toShow;
	}

}
