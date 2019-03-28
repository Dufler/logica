package it.ltc.logica.amministrazione.gui.listini.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.composite.CompositeProprietaListinoCliente;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.wizard.PaginaWizard;

public class NuovoListinoCommessaProprietaWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuovo listino cliente - propriet\u00E0";
	private static final String descrizione = "Inserisci le informazioni sul listino.";

	private ListinoCommessa listino;

	private CompositeProprietaListinoCliente compositeProprieta;
	
	public NuovoListinoCommessaProprietaWizardPage() {
		super(titolo, descrizione);
		listino = new ListinoCommessa();
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return false;
	}

	public ListinoCommessa getListino() {
		return listino;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeProprieta = new CompositeProprietaListinoCliente(this, container);
		compositeProprieta.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		listino.setIdCommessa(compositeProprieta.getCommessa().getId());
		listino.setTipo(compositeProprieta.getTipo().getId());
		listino.setNome(compositeProprieta.getNome());
		listino.setDescrizione(compositeProprieta.getDescrizione());
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		boolean valido;
		if (valid) {
			ListinoCommessa esistente = ControllerListiniClienti.getInstance().getListinoPerAmbitoECliente(compositeProprieta.getTipo().getId(), compositeProprieta.getCommessa().getId());
			valido = esistente == null;
			if (!valido) {
				DialogMessaggio.openWarning("Listino gi\u00E0 presente", "\u00C8 stato gi\u00E0 inserito a sistema un listino per la commessa e l'ambito selezionati.\r\nNon sar\u00E0 possibile procedere con l'inserimento.");
			}
		} else {
			valido = false;
		}
		return valido;
	}

}
