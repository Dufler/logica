package it.ltc.logica.admin.gui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.admin.gui.elements.CriteriAmbiti;
import it.ltc.logica.admin.gui.elements.TabellaAmbiti;
import it.ltc.logica.admin.gui.elements.TabellaSottoAmbiti;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;

public class FatturazionePart {
	
	private final ControllerAmbitiFatturazione controller;
	
	private Composite contentPane;
	private TabellaAmbiti viewerAmbiti;
	private TabellaSottoAmbiti viewerSottoAmbiti;
	
	@Inject
	public FatturazionePart() {
		controller = ControllerAmbitiFatturazione.getInstance();
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		contentPane = parent;
		parent.setLayout(new GridLayout(1, false));
		
		Label lblAmbiti = new Label(parent, SWT.NONE);
		lblAmbiti.setText("Ambiti: ");
		
		viewerAmbiti = new TabellaAmbiti(parent);
		Table tableAmbiti = viewerAmbiti.getTable();
		tableAmbiti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filtraSottoAmbiti();
			}
		});
		GridData layoutAmbiti = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		layoutAmbiti.heightHint = 350;
		tableAmbiti.setLayoutData(layoutAmbiti);
		viewerAmbiti.setInput(controller.getAmbiti());
		viewerAmbiti.aggiustaLarghezzaColonne();
		
		Label lblSottoAmbiti = new Label(parent, SWT.NONE);
		lblSottoAmbiti.setText("Sotto Ambiti: ");
		
		viewerSottoAmbiti = new TabellaSottoAmbiti(parent);
		Table tableSottoAmbiti = viewerSottoAmbiti.getTable();
		GridData layoutSottoAmbiti = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		layoutSottoAmbiti.heightHint = 550;
		tableSottoAmbiti.setLayoutData(layoutSottoAmbiti);
		viewerSottoAmbiti.setInput(controller.getSottoAmbiti());
		viewerSottoAmbiti.aggiustaLarghezzaColonne();
		
	}
	
	private void filtraSottoAmbiti() {
		AmbitoFattura ambitoSelezionato = viewerAmbiti.getAmbitoSelezionato();
		Integer id = ambitoSelezionato != null ? ambitoSelezionato.getId() : null;
		CriteriAmbiti criterio = new CriteriAmbiti(id);
		viewerSottoAmbiti.filtra(criterio);
		contentPane.layout();
	}
	
}