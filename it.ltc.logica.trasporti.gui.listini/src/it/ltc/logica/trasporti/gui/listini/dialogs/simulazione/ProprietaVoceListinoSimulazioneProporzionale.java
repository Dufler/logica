package it.ltc.logica.trasporti.gui.listini.dialogs.simulazione;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceProporzionale;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceListino;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceProporzionale;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class ProprietaVoceListinoSimulazioneProporzionale extends DialogModel<ListinoSimulazioneVoce> {
	
	private static final String titolo = "Propriet\u00E0 - Voce di listino di simulazione proporzionale";
	
	private ListiniSimulazioneController controller;
	
	private final ListinoSimulazioneVoce voce;
	private final ListinoSimulazioneVoceProporzionale voceProporzionale;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVoceProporzionale compositeProporzionale;
	
	private final boolean permessoGestione;
	
	public ProprietaVoceListinoSimulazioneProporzionale(ListinoSimulazioneVoce voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		controller = ListiniSimulazioneController.getInstance();
		voce = voceDiListino;
		voceProporzionale = controller.getVoceProporzionale(voce);
		permessoGestione = permesso;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeVoce = new CompositeVoceListino(this, container, ETipoListino.SIMULAZIONE_TRASPORTI_CLIENTE);
		compositeVoce.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeVoce.enableElement(permessoGestione);
		
		compositeProporzionale = new CompositeVoceProporzionale(this, container);
		compositeProporzionale.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		compositeProporzionale.enableElement(permessoGestione);
		addChild(compositeProporzionale);
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
		Integer ambito = voce.getIdsottoAmbito();
		SottoAmbitoFattura ambitoDiTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito);
		compositeVoce.setAmbito(ambitoDiTrasporto);
		String valoreAmbito = voce.getValoreSottoAmbito();
		if (valoreAmbito != null)
			compositeVoce.setValoreAmbito(valoreAmbito);
		if (voceProporzionale != null) {
			String tipo = voce.getStrategia();
			compositeProporzionale.setTipo(tipo);
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
		voce.setIdsottoAmbito(ambito);
		String valoreAmbito = compositeVoce.getValoreAmbito();
		if (!valoreAmbito.isEmpty())
			voce.setValoreSottoAmbito(valoreAmbito);
		String tipo = compositeProporzionale.getTipo();
		voce.setStrategia(tipo);
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
		boolean updateVoce = controller.aggiornaListinoSimulazioneVoce(voce);
		boolean updateProporzionale = controller.aggiornaListinoSimulazioneVoceProporzionale(voceProporzionale);
		boolean update = updateVoce && updateProporzionale;
		return update;
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
	public ListinoSimulazioneVoce createNewModel() {
		ListinoSimulazioneVoce listino = new ListinoSimulazioneVoce();
		return listino;
	}

}
