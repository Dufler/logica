package it.ltc.logica.trasporti.gui.listini.dialogs.corriere;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereProporzionale;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceListino;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceProporzionale;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class ProprietaVoceListinoCorriereProporzionale extends DialogModel<VoceDiListinoCorriere> {
	
	private static final String titolo = "Propriet\u00E0 - Voce di listino corriere proporzionale";
	
	private ControllerListiniCorrieri controller;
	
	private final VoceDiListinoCorriere voce;
	private final VoceDiListinoCorriereProporzionale voceProporzionale;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVoceProporzionale compositeProporzionale;
	
	private final boolean permessoGestione;

	public ProprietaVoceListinoCorriereProporzionale(VoceDiListinoCorriere voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		controller = ControllerListiniCorrieri.getInstance();
		voce = voceDiListino;
		voceProporzionale = voce.getProporzionale();
		permessoGestione = permesso;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeVoce = new CompositeVoceListino(this, container, ETipoListino.CORRIERE);
		compositeVoce.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeVoce.enableElement(permessoGestione);
		
		compositeProporzionale = new CompositeVoceProporzionale(this, container);
		compositeProporzionale.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		compositeProporzionale.enableElement(permessoGestione);
	}

	@Override
	public boolean isDirty() {
		boolean modifyVoce = compositeVoce.isDirty();
		boolean modifyProporzionale = compositeProporzionale.isDirty();
		return modifyVoce || modifyProporzionale;
	}

	@Override
	public void loadModel() {
		String nome = voce.getNome();
		compositeVoce.setNome(nome);
		String descrizione = voce.getDescrizione();
		compositeVoce.setDescrizione(descrizione);
		String tipo = voce.getStrategiaCalcolo();
		compositeProporzionale.setTipo(tipo);
		Integer ambito = voce.getIdSottoAmbito();
		SottoAmbitoFattura ambitoDiTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito);
		compositeVoce.setAmbito(ambitoDiTrasporto);
		String valoreAmbito = voce.getValoreSottoAmbito();
		if (valoreAmbito != null)
			compositeVoce.setValoreAmbito(valoreAmbito);
		if (voceProporzionale != null) {
			Double valore = voceProporzionale.getValore();
			compositeProporzionale.setValore(valore);
			Double minimo = voceProporzionale.getMinimo();
			compositeProporzionale.setMinimo(minimo);
			Double massimo = voceProporzionale.getMassimo();
			compositeProporzionale.setMassimo(massimo);
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
		String tipo = compositeProporzionale.getTipo();
		voce.setStrategiaCalcolo(tipo);
		Double valore = compositeProporzionale.getValore();
		voceProporzionale.setValore(valore);
		Double minimo = compositeProporzionale.getMinimo();
		voceProporzionale.setMinimo(minimo);
		Double massimo = compositeProporzionale.getMassimo();
		voceProporzionale.setMassimo(massimo);
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean updateVoce = controller.aggiornaVoce(voce);
		return updateVoce;
//		boolean updateProporzionale = controller.aggiornaVoceDiListinoProporzionale(voceProporzionale);
//		return updateVoce && updateProporzionale;
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
	public VoceDiListinoCorriere createNewModel() {
		VoceDiListinoCorriere voce = new VoceDiListinoCorriere();
		return voce;
	}

}
