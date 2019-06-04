package it.ltc.logica.ufficio.gui.elements.prodotto.movimenti;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;

import it.ltc.logica.common.controller.ingressi.ControllerMagazzini;
import it.ltc.logica.database.model.centrale.CausaliMovimento;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.Magazzino;
import it.ltc.logica.database.model.prodotto.MovimentoProdotto;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarMovimentiProdotto extends ToolbarCRUDConFiltro<TabellaMovimentiProdotto, MovimentoProdotto, CriteriFiltraggioMovimentiProdotto> {

	private ComboBox<Magazzino> comboMagazzino;
	private ComboBox<CausaliMovimento> comboCausale;
	private DateField dataMovimentoDa;
	private DateField dataMovimentoA;
	
	public ToolbarMovimentiProdotto(Composite parent) {
		super(parent);
	}
	
	public void setCommessa(Commessa commessa) {
		comboMagazzino.setItems(ControllerMagazzini.getInstance().getMagazzini(commessa));
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
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiTastoNuovo();
		aggiungiTastoElimina();
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(6, false));
		
		Label lblCausale = new Label(composite, SWT.NONE);
		lblCausale.setText("Causale: ");
		
		Label lblMagazzino = new Label(composite, SWT.NONE);
		lblMagazzino.setText("Magazzino: ");
		
		Label lblDa = new Label(composite, SWT.NONE);
		lblDa.setText("Da: ");
		
		new SpacerLabel(composite);
		
		Label lblA = new Label(composite, SWT.NONE);
		lblA.setText("A: ");
		
		new SpacerLabel(composite);
		
		comboCausale = new ComboBox<>(composite);
		comboCausale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCausale.setItems(CausaliMovimento.values());
		
		comboMagazzino = new ComboBox<>(composite);
		comboMagazzino.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		dataMovimentoDa = new DateField(composite);
		
		new SpacerLabel(composite);
		
		dataMovimentoA = new DateField(composite);
		
		new SpacerLabel(composite);
	}

	@Override
	protected CriteriFiltraggioMovimentiProdotto getCriteriDiFiltraggio() {
		CausaliMovimento causale = comboCausale.getSelectedValue();
		String magazzino = comboMagazzino.getSelectedValue() != null ? comboMagazzino.getSelectedValue().getCodiceLTC() : null;
		Date da = dataMovimentoDa.getSimpleStartValue();
		Date a = dataMovimentoA.getSimpleEndValue();
		CriteriFiltraggioMovimentiProdotto criteri = new CriteriFiltraggioMovimentiProdotto(da, a, causale, magazzino);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboCausale.resetValue();
		comboMagazzino.resetValue();
		dataMovimentoDa.resetValue();
		dataMovimentoA.resetValue();
	}

}
