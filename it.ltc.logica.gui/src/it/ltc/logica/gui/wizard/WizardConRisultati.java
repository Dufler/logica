package it.ltc.logica.gui.wizard;

import java.util.LinkedList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

public abstract class WizardConRisultati extends WizardConFinale {
	
	public WizardConRisultati(String title, boolean canGoBack) {
		super(title, canGoBack);
	}
	
	public abstract LinkedList<PaginaWizardRisultati> getPaginaRisultati();

	protected void aggiungiListener(Button nextButton) {
		nextButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (PaginaWizardRisultati pagina : getPaginaRisultati()) {
					if (getContainer().getCurrentPage().equals(pagina)) {
						pagina.mostraRisultato();
					}
				}
			}
		});
	}
	
	@Override
	public boolean canFinish() {
		IWizardPage current = getContainer().getCurrentPage();
		boolean finish = current.equals(getPaginaRisultati().getLast()) && current.isPageComplete();
		return finish;
	}

}
