package it.ltc.logica.trasporti.gui.listini.wizards.simulazione.rincaracorriere;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeProprietaListinoCliente;

public class RincaraCorriereProprietaWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuovo listino di simulazione - propriet\u00E0";
	private static final String descrizione = "Inserisci le informazioni sul listino.";

	private ListinoSimulazione listino;
	
	private CompositeProprietaListinoCliente compositeProprieta;
	
	protected RincaraCorriereProprietaWizardPage() {
		super(titolo, descrizione);
		listino = new ListinoSimulazione();
	}

	public ListinoSimulazione getListino() {
		return listino;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeProprieta = new CompositeProprietaListinoCliente(this, container, CompositeProprietaListinoCliente.Tipo.SIMULAZIONE);
		compositeProprieta.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		listino.setTipo(compositeProprieta.getTipo().getId());
		listino.setNome(compositeProprieta.getNome());
		listino.setDescrizione(compositeProprieta.getDescrizione());
	}

}
