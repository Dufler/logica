package it.ltc.logica.trasporti.gui.listini.dialogs.simulazione;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceFissa;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceFissa;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceListino;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class ProprietaVoceListinoSimulazioneFissa extends DialogModel<ListinoSimulazioneVoce> {
	
	private static final String titolo = "Propriet\u00E0 - Voce di listino di simulazione fissa";
	
	private ListiniSimulazioneController controller;
	
	private final ListinoSimulazioneVoce voce;
	private final ListinoSimulazioneVoceFissa voceFissa;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVoceFissa compositeFissa;
	
	private final boolean permessoGestione;
	
	public ProprietaVoceListinoSimulazioneFissa(ListinoSimulazioneVoce voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		controller = ListiniSimulazioneController.getInstance();
		voce = voceDiListino;
		voceFissa = controller.getVoceFissa(voce);
		permessoGestione = permesso;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeVoce = new CompositeVoceListino(this, container, ETipoListino.SIMULAZIONE_TRASPORTI_CLIENTE);
		compositeVoce.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeVoce.enableElement(permessoGestione);
		
		compositeFissa = new CompositeVoceFissa(this, container);
		compositeFissa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeFissa.enableElement(permessoGestione);
	}

	@Override
	public boolean isDirty() {
		boolean voceValida = compositeVoce.isDirty();
		boolean fissaValida = compositeFissa.isDirty();
		return voceValida || fissaValida;
	}

	@Override
	public void loadModel() {
		String nome = voce.getNome();
		String descrizione = voce.getDescrizione();
		compositeVoce.setNome(nome);
		compositeVoce.setDescrizione(descrizione);
		Integer ambito = voce.getIdsottoAmbito();
		SottoAmbitoFattura ambitoDiTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito);
		compositeVoce.setAmbito(ambitoDiTrasporto);
		String valoreAmbito = voce.getValoreSottoAmbito();
		if (valoreAmbito != null)
			compositeVoce.setValoreAmbito(valoreAmbito);
		if (voceFissa != null) {
			Double valore = voceFissa.getValore();
			compositeFissa.setValore(valore);
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
		Double valore = compositeFissa.getValore();
		voceFissa.setValore(valore);
	}

	@Override
	public boolean updateModel() {
		boolean updateListino = controller.aggiornaListinoSimulazioneVoce (voce);
		boolean updateFissa = controller.aggiornaListinoSimulazioneVoceFissa(voceFissa);
		boolean update = updateListino && updateFissa;
		return update;
	}

	@Override
	public ListinoSimulazioneVoce createNewModel() {
		ListinoSimulazioneVoce listino = new ListinoSimulazioneVoce();
		return listino;
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
	public List<String> validateModel() {
		return null;
	}

}
