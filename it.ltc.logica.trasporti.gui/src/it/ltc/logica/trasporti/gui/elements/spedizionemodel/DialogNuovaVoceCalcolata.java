package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogSempliceConValidazione;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForMoney;

public class DialogNuovaVoceCalcolata extends DialogSempliceConValidazione {
	
	private static final String titolo = "Nuova voce";
	
	private final ControllerListiniClienti controller;
	
	private ComboBox<VoceDiListino> combo;
	private TextForMoney text;
	
	private VoceDiListino voceSelezionata;
	private double valore;

	private final Calcolo calcolo;
	private final int idListino;
	
	protected DialogNuovaVoceCalcolata(Calcolo risultato) {
		super(titolo, null, true);
		
		calcolo = risultato;
		idListino = risultato.getIdListino();
		controller = ControllerListiniClienti.getInstance();
	}
	
	public void aggiungiAltriBottoni(Composite parent) {
		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aggiungiNuovaVoce();
			}
		});
	}

	private void aggiungiNuovaVoce() {		
		SottoAmbitoFattura ambito = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(voceSelezionata.getIdSottoAmbito());
		VoceCalcolata nuovaVoce = new VoceCalcolata(voceSelezionata.getId(), voceSelezionata.getNome(), ambito.getId(), ambito.getNome(), ambito.getCategoriaAmbito(), ambito.getDescrizione());	
		nuovaVoce.setCosto(valore);
		calcolo.aggiungiVoce(nuovaVoce);
	}

	@Override
	public boolean isDirty() {
		return combo.isDirty() || text.isDirty();
	}

	@Override
	protected boolean validation() {
		boolean validation = validate();
		voceSelezionata = combo.getSelectedValue();
		valore = text.getValue() != null ? text.getValue() : 0.0;
		return validation;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(3, false));
		
		Label lblVoceDiListino = new Label(container, SWT.NONE);
		lblVoceDiListino.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVoceDiListino.setText("Voce di listino: ");
		
		combo = new ComboBox<VoceDiListino>(container);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.setItems(controller.getVociDiListino(idListino));
		addChild(combo);
		
		new SpacerLabel(container);
		
		Label lblValore = new Label(container, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore: ");
		
		text = new TextForMoney(container);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(text);
		
		new SpacerLabel(container);
	}

	@Override
	public void checkElementiGrafici() {
		//DO NOTHING!
		
	}
	
}
