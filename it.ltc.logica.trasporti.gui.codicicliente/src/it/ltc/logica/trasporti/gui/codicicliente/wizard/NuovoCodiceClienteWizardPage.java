package it.ltc.logica.trasporti.gui.codicicliente.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeCodiceCliente;

public class NuovoCodiceClienteWizardPage extends PaginaWizard {
	
	private static final String titolo = "Inserisci un nuovo codice cliente";
	private static final String descrizione = "Specifica il codice, il cliente e il corriere.";
	
	private CompositeCodiceCliente compositeCodice;

	private CodiceClienteCorriere nuovoCodice;
	
	public NuovoCodiceClienteWizardPage() {
		super(titolo, descrizione);
		nuovoCodice = new CodiceClienteCorriere();
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return false;
	}
	
	public CodiceClienteCorriere getNuovoCodice() {
		return nuovoCodice;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeCodice = new CompositeCodiceCliente(this, container);
		compositeCodice.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		nuovoCodice.setCodiceCliente(compositeCodice.getCodiceCliente());
		nuovoCodice.setDescrizione(compositeCodice.getDescrizione());
		nuovoCodice.setCommessa(compositeCodice.getCommessa().getId());
		nuovoCodice.setCorriere(compositeCodice.getCorriere().getId());
		nuovoCodice.setStato(compositeCodice.getStato());
	}

}
