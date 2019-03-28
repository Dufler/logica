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
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceScaglioniRipetuti;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceListino;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceScaglioniRipetuti;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class ProprietaVoceListinoSimulazioneScaglioniRipetuti extends DialogModel<ListinoSimulazioneVoce> {
	
	private static final String titolo = "Propriet\u00E0 - Voce di listino di simulazione a scaglioni ripetuti";
	
	private ListiniSimulazioneController controller;
	
	private final ListinoSimulazioneVoce voce;
	private final ListinoSimulazioneVoceScaglioniRipetuti voceScaglioni;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVoceScaglioniRipetuti compositeScaglioni;
	
	private final boolean permessoGestione;

	public ProprietaVoceListinoSimulazioneScaglioniRipetuti(ListinoSimulazioneVoce voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		controller = ListiniSimulazioneController.getInstance();
		voce = voceDiListino;
		voceScaglioni = controller.getVoceScaglioniRipetuti(voce);
		permessoGestione = permesso;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeVoce = new CompositeVoceListino(this, container, ETipoListino.SIMULAZIONE_TRASPORTI_CLIENTE);
		compositeVoce.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeVoce.enableElement(permessoGestione);
		
		compositeScaglioni = new CompositeVoceScaglioniRipetuti(this, container, permessoGestione);
		compositeScaglioni.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeScaglioni.enableElement(permessoGestione);
	}

	@Override
	public boolean isDirty() {
		boolean modifyVoce = compositeVoce.isDirty();
		boolean modifyScaglioni = compositeScaglioni.isDirty();
		return modifyVoce || modifyScaglioni;
	}

	@Override
	public void loadModel() {
		String nome = voce.getNome();
		String descrizione = voce.getDescrizione();
		Integer ambito = voce.getIdsottoAmbito();
		compositeVoce.setNome(nome);
		compositeVoce.setDescrizione(descrizione);
		SottoAmbitoFattura ambitoDiTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito);
		compositeVoce.setAmbito(ambitoDiTrasporto);
		String valoreAmbito = voce.getValoreSottoAmbito();
		if (valoreAmbito != null)
			compositeVoce.setValoreAmbito(valoreAmbito);
		String tipo = voce.getStrategia();
		Double intervallo = voceScaglioni.getIntervallo();
		Double valore = voceScaglioni.getValore();
		Double minimo = voceScaglioni.getMinimo();
		Double massimo = voceScaglioni.getMassimo();
		compositeScaglioni.setTipo(tipo);
		compositeScaglioni.setIntervallo(intervallo);
		compositeScaglioni.setValore(valore);
		compositeScaglioni.setMinimo(minimo);
		compositeScaglioni.setMassimo(massimo);
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
		String tipo = compositeScaglioni.getTipo();
		voce.setStrategia(tipo);
		Double intervallo = compositeScaglioni.getIntervallo();
		voceScaglioni.setIntervallo(intervallo);
		Double valore = compositeScaglioni.getValore();
		voceScaglioni.setValore(valore);
		Double minimo = compositeScaglioni.getMinimo();
		voceScaglioni.setMinimo(minimo);
		Double massimo = compositeScaglioni.getMassimo();
		voceScaglioni.setMassimo(massimo);	
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean updateVoce = controller.aggiornaListinoSimulazioneVoce(voce);
		boolean updateScaglioni = controller.aggiornaListinoSimulazioneVoceScaglioniRipetuti(voceScaglioni);
		boolean update = updateVoce && updateScaglioni;
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
