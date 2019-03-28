package it.ltc.logica.amministrazione.gui.listini.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.composite.CompositeVoceListino;
import it.ltc.logica.amministrazione.gui.composite.CompositeVoceScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoScaglioni;
import it.ltc.logica.gui.dialog.DialogModel;

public class ProprietaVoceListinoCommessaScaglioni extends DialogModel<VoceDiListino> {

	private static final String titolo = "Propriet\u00E0 - Voce di listino cliente a scaglioni";
	
	private ControllerListiniClienti controller;
	
	private final VoceDiListino voce;
	private final List<VoceDiListinoScaglioni> vociScaglioni;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVoceScaglioni compositeScaglioni;
	
	private final boolean permessoGestione;
	
	public ProprietaVoceListinoCommessaScaglioni(VoceDiListino voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		controller = ControllerListiniClienti.getInstance();
		voce = voceDiListino;
		vociScaglioni = voce.getScaglioni();
		permessoGestione = permesso;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeVoce = new CompositeVoceListino(this, container, CompositeVoceListino.Tipo.LOGISTICA);
		compositeVoce.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		compositeVoce.enableElement(permessoGestione);
		
		compositeScaglioni = new CompositeVoceScaglioni(this, container);
		compositeScaglioni.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
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
		compositeVoce.setNome(nome);
		String descrizione = voce.getDescrizione();
		compositeVoce.setDescrizione(descrizione);
		Integer ambito = voce.getIdSottoAmbito();
		SottoAmbitoFattura ambitoDiTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito);
		compositeVoce.setAmbito(ambitoDiTrasporto);
		String valoreAmbito = voce.getValoreSottoAmbito();
		if (valoreAmbito != null)
			compositeVoce.setValoreAmbito(valoreAmbito);
		String tipo = "";
		ArrayList<Scaglione> scaglioni = new ArrayList<Scaglione>();
		for (VoceDiListinoScaglioni voceScaglione : vociScaglioni) {
			Scaglione scaglione = new Scaglione();
			scaglione.setTipo(voce.getStrategiaCalcolo());
			//scaglione.setTipo(voceScaglione.getTipo());
			scaglione.setInizio(voceScaglione.getInizio());
			scaglione.setFine(voceScaglione.getFine());
			scaglione.setValore(voceScaglione.getValore());
			scaglioni.add(scaglione);
			tipo = voce.getStrategiaCalcolo(); //voceScaglione.getTipo();
		}
		compositeScaglioni.setScaglioni(scaglioni);
		compositeScaglioni.setTipo(tipo);
		compositeScaglioni.abilitaInserimento(true);
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
		Integer id = voce.getId();
		String tipo = compositeScaglioni.getTipo();
		voce.setStrategiaCalcolo(tipo);
		ArrayList<Scaglione> scaglioni = compositeScaglioni.getScaglioni();
		vociScaglioni.clear();
		for (Scaglione scaglione : scaglioni) {
			VoceDiListinoScaglioni voceScaglione = new VoceDiListinoScaglioni();
			voceScaglione.setIdVoce(id);
			//voceScaglione.setTipo(tipo);
			voceScaglione.setInizio(scaglione.getInizio());
			voceScaglione.setFine(scaglione.getFine());
			voceScaglione.setValore(scaglione.getValore());
			vociScaglioni.add(voceScaglione);
		}
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean updateVoce = controller.aggiornaVoce(voce);
		return updateVoce;
//		boolean updateScaglioni = controller.aggiornaVoceDiListinoScaglioni(vociScaglioni, voce.getId());
//		boolean update = updateVoce && updateScaglioni;
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

}
