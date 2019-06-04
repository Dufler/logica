package it.ltc.logica.amministrazione.gui.fatturazione.elements.categorie;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCategorie extends ToolbarCRUDConFiltro<TabellaCategorie, CategoriaMerceologica, CriteriFiltroCategoria> {
	
	private ComboBox<Commessa> comboCommessa;

	public ToolbarCategorie(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(2, false));
		
		Composite compositeCommessa = new Composite(composite, SWT.NONE);
		compositeCommessa.setLayout(new GridLayout(1, false));
		
		Label labelCommessa = new Label(compositeCommessa, SWT.NONE);
		labelCommessa.setText("Seleziona la commessa: ");
		
		comboCommessa = new ComboBox<>(compositeCommessa);
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selezioneCommessa();
			}
		});
		
		Composite compositeFiltri = new Composite(composite, SWT.NONE);
		compositeFiltri.setLayout(new GridLayout(2, false));
		
		Label labelFiltri = new Label(compositeFiltri, SWT.NONE);
		labelFiltri.setText("Filtra per: 		");
		
		new SpacerLabel(compositeFiltri);
		
		aggiungiCampoDiTestoGenericoPerFiltro(compositeFiltri);
		filterText.setEnabled(false);
		
		new SpacerLabel(compositeFiltri);		
	}
	
	@Override
	protected void creaCompositeDx() {
		compositeDx = new Composite(this, SWT.NONE);
		compositeDx.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeDx.setLayout(new GridLayout(1, false));
		
		new SpacerLabel(compositeDx);
		
		toolbar = new ToolBar(compositeDx, SWT.FLAT | SWT.RIGHT);
		toolbar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	protected CriteriFiltroCategoria getCriteriDiFiltraggio() {
		CriteriFiltroCategoria filtro = new CriteriFiltroCategoria(comboCommessa.getSelectedValue(), filterText.getText());
		return filtro;
	}
	
	protected void selezioneCommessa() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		tabella.setCommessa(commessaSelezionata);
		tabella.aggiornaContenuto();
		boolean enable = commessaSelezionata != null;
		filterText.setEnabled(enable);
		if (!enable) {
			filterText.setText("");
		}
		filtra();
	}

	@Override
	protected void resetCampiFiltri() {
		//Tolgo la selezione della commessa
		comboCommessa.setSelectedValue(null);
		//ripulisco e disabilito
		filterText.setText("");
		filterText.setEnabled(false);
		//esprimo la deselezione
		selezioneCommessa();
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_INGRESSI_ELIMINA.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiSeparatore();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}

}
