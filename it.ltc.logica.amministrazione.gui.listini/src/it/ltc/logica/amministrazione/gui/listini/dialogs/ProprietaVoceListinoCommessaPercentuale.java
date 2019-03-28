package it.ltc.logica.amministrazione.gui.listini.dialogs;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.composite.CompositeVoceListino;
import it.ltc.logica.amministrazione.gui.composite.CompositeVocePercentuale;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoPercentuale;
import it.ltc.logica.gui.dialog.DialogModel;

public class ProprietaVoceListinoCommessaPercentuale extends DialogModel<VoceDiListino> {
	
	private static final String titolo = "Propriet\u00E0 - Voce di listino cliente percentuale";
	
	private ControllerListiniClienti controller;
	
	private final VoceDiListino voce;
	private final VoceDiListinoPercentuale vocePercentuale;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVocePercentuale compositePercentuale;
	
	private final boolean permessoGestione;
	
	public ProprietaVoceListinoCommessaPercentuale(VoceDiListino voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		controller = ControllerListiniClienti.getInstance();
		voce = voceDiListino;
		vocePercentuale = voce.getPercentuale();
		permessoGestione = permesso;
	}
	
	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeVoce = new CompositeVoceListino(this, container, CompositeVoceListino.Tipo.LOGISTICA);
		compositeVoce.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		compositeVoce.enableElement(permessoGestione);
		
		compositePercentuale = new CompositeVocePercentuale(this, container);
		compositePercentuale.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		compositePercentuale.enableElement(permessoGestione);
	}
	
	@Override
	public void loadModel() {
		String nome = voce.getNome();
		compositeVoce.setNome(nome);
		String descrizione = voce.getDescrizione();
		compositeVoce.setDescrizione(descrizione);
		Integer ambito = voce.getIdSottoAmbito();
		SottoAmbitoFattura ambitoDiTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito);
		compositeVoce.setAmbito(ambitoDiTrasporto);
		String valoreAmbito = voce.getValoreSottoAmbito();
		if (valoreAmbito != null)
			compositeVoce.setValoreAmbito(valoreAmbito);
		if (vocePercentuale != null) {
			String tipo = voce.getStrategiaCalcolo(); //vocePercentuale.getTipo();
			compositePercentuale.setTipo(tipo);
			Double valore = vocePercentuale.getValore();
			compositePercentuale.setValore(valore);
			Double minimo = vocePercentuale.getValoreMinimo();
			compositePercentuale.setMinimo(minimo);
			Double massimo = vocePercentuale.getValoreMassimo();
			compositePercentuale.setMassimo(massimo);
		}
	}
	
	@Override
	public void copyDataToModel() {
		String nome = compositeVoce.getNome();
		voce.setNome(nome);
		String descrizione = compositeVoce.getDescrizione();
		voce.setDescrizione(descrizione);
		Integer ambito = compositeVoce.getAmbito().getId();
		voce.setIdSottoAmbito(ambito);
		String valoreAmbito = compositeVoce.getValoreAmbito();
		if (!valoreAmbito.isEmpty())
			voce.setValoreSottoAmbito(valoreAmbito);
		String tipo = compositePercentuale.getTipo();
		voce.setStrategiaCalcolo(tipo);
		//vocePercentuale.setTipo(tipo);
		Double valore = compositePercentuale.getValore();
		vocePercentuale.setValore(valore);
		Double minimo = compositePercentuale.getMinimo();
		vocePercentuale.setValoreMinimo(minimo);
		Double massimo = compositePercentuale.getMassimo();
		vocePercentuale.setValoreMassimo(massimo);
	}

	@Override
	public boolean isDirty() {
		boolean modificaVoce = compositeVoce.isDirty();
		boolean modificaPercentuale = compositePercentuale.isDirty();
		return modificaVoce || modificaPercentuale;
	}


	@Override
	public boolean updateModel() {
		boolean updateListino = controller.aggiornaVoce(voce);
		return updateListino;
//		boolean updatePercentuale = controller.aggiornaVoceDiListinoPercentuale(vocePercentuale);
//		boolean update = updateListino && updatePercentuale;
//		return update;
	}

	@Override
	public boolean insertModel() {
		//DO NOTHING!
		return false;
	}
	
	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public VoceDiListino createNewModel() {
		VoceDiListino listino = new VoceDiListino();
		return listino;
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

}
