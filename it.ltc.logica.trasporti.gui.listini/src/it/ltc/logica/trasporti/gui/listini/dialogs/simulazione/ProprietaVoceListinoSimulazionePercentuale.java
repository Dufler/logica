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
import it.ltc.logica.database.model.locale.ListinoSimulazioneVocePercentuale;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceListino;
import it.ltc.logica.trasporti.gui.composite.CompositeVocePercentuale;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class ProprietaVoceListinoSimulazionePercentuale extends DialogModel<ListinoSimulazioneVoce> {
	
	private static final String titolo = "Propriet\u00E0 - Voce di listino di simulazione percentuale";
	
	private ListiniSimulazioneController controller;
	
	private final ListinoSimulazioneVoce voce;
	private final ListinoSimulazioneVocePercentuale vocePercentuale;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVocePercentuale compositePercentuale;
	
	private final boolean permessoGestione;
	
	public ProprietaVoceListinoSimulazionePercentuale(ListinoSimulazioneVoce voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		controller = ListiniSimulazioneController.getInstance();
		voce = voceDiListino;
		vocePercentuale = controller.getVocePercentuale(voce);
		permessoGestione = permesso;
	}
	
	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeVoce = new CompositeVoceListino(this, container, ETipoListino.SIMULAZIONE_TRASPORTI_CLIENTE);
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
		Integer ambito = voce.getIdsottoAmbito();
		SottoAmbitoFattura ambitoDiTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito);
		compositeVoce.setAmbito(ambitoDiTrasporto);
		String valoreAmbito = voce.getValoreSottoAmbito();
		if (valoreAmbito != null)
			compositeVoce.setValoreAmbito(valoreAmbito);
		if (vocePercentuale != null) {
			String tipo = voce.getStrategia();
			compositePercentuale.setTipo(tipo);
			Double valore = vocePercentuale.getValore();
			compositePercentuale.setValore(valore);
			Double minimo = vocePercentuale.getMinimo();
			compositePercentuale.setMinimo(minimo);
			Double massimo = vocePercentuale.getMassimo();
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
		voce.setIdsottoAmbito(ambito);
		String valoreAmbito = compositeVoce.getValoreAmbito();
		if (!valoreAmbito.isEmpty())
			voce.setValoreSottoAmbito(valoreAmbito);
		String tipo = compositePercentuale.getTipo();
		voce.setStrategia(tipo);
		//vocePercentuale.setTipo(tipo);
		Double valore = compositePercentuale.getValore();
		vocePercentuale.setValore(valore);
		Double minimo = compositePercentuale.getMinimo();
		vocePercentuale.setMinimo(minimo);
		Double massimo = compositePercentuale.getMassimo();
		vocePercentuale.setMassimo(massimo);
	}

	@Override
	public boolean isDirty() {
		boolean modificaVoce = compositeVoce.isDirty();
		boolean modificaPercentuale = compositePercentuale.isDirty();
		return modificaVoce || modificaPercentuale;
	}


	@Override
	public boolean updateModel() {
		boolean updateListino = controller.aggiornaListinoSimulazioneVoce(voce);
		boolean updatePercentuale = controller.aggiornaListinoSimulazioneVocePercentuale(vocePercentuale);
		boolean update = updateListino && updatePercentuale;
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

	@Override
	public List<String> validateModel() {
		return null;
	}

}
