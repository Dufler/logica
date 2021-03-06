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
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereFissa;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceFissa;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceListino;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class ProprietaVoceListinoCorriereFissa extends DialogModel<VoceDiListinoCorriere> {
	
	private static final String titolo = "Propriet\u00E0 - Voce di listino corriere fissa";

	private ControllerListiniCorrieri controller;
	
	private final VoceDiListinoCorriere voce;
	private final VoceDiListinoCorriereFissa voceFissa;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVoceFissa compositeFissa;
	
	private final boolean permessoGestione;
	
	public ProprietaVoceListinoCorriereFissa(VoceDiListinoCorriere voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		voce = voceDiListino;
		voceFissa = voce.getFissa();
		controller = ControllerListiniCorrieri.getInstance();
		permessoGestione = permesso;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeVoce = new CompositeVoceListino(this, container, ETipoListino.CORRIERE);
		compositeVoce.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeVoce.enableElement(permessoGestione);
		
		compositeFissa = new CompositeVoceFissa(this, container);
		compositeFissa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeFissa.enableElement(permessoGestione);
	}

	@Override
	public boolean isDirty() {
		boolean modifyVoce = compositeVoce.isDirty();
		boolean modifyFissa = compositeFissa.isDirty();
		return modifyVoce || modifyFissa;
	}

	@Override
	public void loadModel() {
		String nome = voce.getNome();
		String descrizione = voce.getDescrizione();
		Integer ambito = voce.getIdSottoAmbito();
		compositeVoce.setNome(nome);
		compositeVoce.setDescrizione(descrizione);
		SottoAmbitoFattura ambitoDiTrasporto = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito);
		compositeVoce.setAmbito(ambitoDiTrasporto);
		String valoreAmbito = voce.getValoreSottoAmbito();
		if (valoreAmbito != null)
			compositeVoce.setValoreAmbito(valoreAmbito);
		Double valore = voceFissa.getValore();
		compositeFissa.setValore(valore);
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
		Double valore = compositeFissa.getValore();
		voceFissa.setValore(valore);
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean updateVoce = controller.aggiornaVoce(voce);
		return updateVoce;
//		boolean updateFissa = controller.aggiornaVoceDiListinoFissa(voceFissa);
//		return updateVoce && updateFissa;
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
