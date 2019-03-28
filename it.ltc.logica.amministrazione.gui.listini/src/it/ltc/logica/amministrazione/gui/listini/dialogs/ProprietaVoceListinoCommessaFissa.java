package it.ltc.logica.amministrazione.gui.listini.dialogs;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.composite.CompositeVoceFissa;
import it.ltc.logica.amministrazione.gui.composite.CompositeVoceListino;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoFissa;
import it.ltc.logica.gui.dialog.DialogModel;

public class ProprietaVoceListinoCommessaFissa extends DialogModel<VoceDiListino> {
	
	private static final String titolo = "Propriet\u00E0 - Voce di listino cliente fissa";
	
	private ControllerListiniClienti controller;
	
	private final VoceDiListino voce;
	private final VoceDiListinoFissa voceFissa;
	
	private CompositeVoceListino compositeVoce;
	private CompositeVoceFissa compositeFissa;
	
	private final boolean permessoGestione;
	
	public ProprietaVoceListinoCommessaFissa(VoceDiListino voceDiListino, boolean permesso) {
		super(titolo, voceDiListino);
		controller = ControllerListiniClienti.getInstance();
		voce = voceDiListino;
		voceFissa = voce.getFissa();
		permessoGestione = permesso;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		compositeVoce = new CompositeVoceListino(this, container, CompositeVoceListino.Tipo.LOGISTICA);
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
		Integer ambito = voce.getIdSottoAmbito();
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
		voce.setIdSottoAmbito(ambito);
		String valoreAmbito = compositeVoce.getValoreAmbito();
		if (!valoreAmbito.isEmpty())
			voce.setValoreSottoAmbito(valoreAmbito);
		Double valore = compositeFissa.getValore();
		voceFissa.setValore(valore);
	}

	@Override
	public boolean updateModel() {
		boolean updateListino = controller.aggiornaVoce(voce);
		return updateListino;
	}

	@Override
	public VoceDiListino createNewModel() {
		VoceDiListino listino = new VoceDiListino();
		return listino;
	}

	@Override
	public boolean insertModel() {
		//DO NOTHING!
		return false;
	}

	@Override
	public List<String> validateModel() {
		return null;
	}
	
	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

}
