package it.ltc.logica.trasporti.gui.listini.wizards.corriere;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeProprietaListinoCorriere;
import org.eclipse.swt.layout.GridData;

public class NuovoListinoCorriereProprietaWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuovo listino corriere - propriet\u00E0";
	private static final String descrizione = "Inserisci le informazioni sul listino.";
	
	private ListinoCorriere listino;
	
	private CompositeProprietaListinoCorriere compositeListino;

	public NuovoListinoCorriereProprietaWizardPage() {
		super(titolo, descrizione);
		listino = new ListinoCorriere();
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return true;
	}
	
	public ListinoCorriere getListino() {
		return listino;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeListino = new CompositeProprietaListinoCorriere(this, container);
		compositeListino.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		listino.setIdCorriere(compositeListino.getCorriere().getId());
		listino.setTipo(compositeListino.getTipo().getId());
		listino.setNome(compositeListino.getNome());
		listino.setDescrizione(compositeListino.getDescrizione());
	}
	
}
