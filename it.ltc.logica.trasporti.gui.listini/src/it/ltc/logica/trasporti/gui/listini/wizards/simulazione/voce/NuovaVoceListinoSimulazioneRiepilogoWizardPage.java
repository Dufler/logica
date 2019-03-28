package it.ltc.logica.trasporti.gui.listini.wizards.simulazione.voce;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceScaglioni;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;

public class NuovaVoceListinoSimulazioneRiepilogoWizardPage extends PaginaWizardRisultati {
	
	private final static String title = "Nuova Voce di listino di simulazione - Riepilogo";
	private final static String description = "Controlla i dati inseriti";
	
	private Text textTipo;
	private Text textValore;
	private Text textNome;
	private Text textDescrizione;
	private Text textAmbito;
	
	private final ListinoSimulazioneVoce voce;
	
	private TipoAlgoritmo tipoAlgoritmo;

	public NuovaVoceListinoSimulazioneRiepilogoWizardPage(ListinoSimulazioneVoce voce) {
		super(title, description, true);
		this.voce = voce;
	}
	
	public void setTipoAlgoritmo(TipoAlgoritmo tipoAlgoritmo) {
		this.tipoAlgoritmo = tipoAlgoritmo;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(2, false));
		setPageComplete(true);
		
		Label lblTipo = new Label(container, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		textTipo = new Text(container, SWT.BORDER);
		textTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textTipo.setEnabled(false);
		
		Label lblAmbito = new Label(container, SWT.NONE);
		lblAmbito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAmbito.setText("Ambito: ");
		
		textAmbito = new Text(container, SWT.BORDER);
		textAmbito.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textAmbito.setEnabled(false);
		
		Label lblValorei = new Label(container, SWT.NONE);
		lblValorei.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValorei.setText("Valore/i:");
		
		textValore = new Text(container, SWT.BORDER);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textValore.setEnabled(false);
		
		Label lblNome = new Label(container, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome: ");
		
		textNome = new Text(container, SWT.BORDER);
		textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textNome.setEnabled(false);
		
		Label lblDescrizione = new Label(container, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new Text(container, SWT.BORDER | SWT.MULTI);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		textDescrizione.setEnabled(false);	
	}

	@Override
	public void mostraRisultato() {
		String nome = voce.getNome();
		String descrizione = voce.getDescrizione();
		Integer ambito = voce.getIdsottoAmbito();
		String valore = "";
		switch (tipoAlgoritmo) {
			case FISSO : valore = Double.toString(voce.getFissa().getValore()); valore += " \u20AC"; break;
			case PERCENTUALE : valore = Double.toString(voce.getPercentuale().getValore()); valore += " \u0025"; break;
			case PROPORZIONALE : valore = Double.toString(voce.getProporzionale().getValore()); valore += " \u20AC"; break;
			case SCAGLIONI_RIPETUTI : valore = Double.toString(voce.getRipetuti().getValore()); valore += " \u20AC"; break;
			case SCAGLIONI : 
			{
				List<ListinoSimulazioneVoceScaglioni> vociScaglioni = voce.getScaglioni();
				for (ListinoSimulazioneVoceScaglioni voceScaglione : vociScaglioni) {
					valore += voceScaglione.getValore() + " - ";
				}
				valore = valore.substring(0, valore.length() - 2);
				valore += " \u20AC";
			} break;
			default : valore = "-";break;
		}
		textNome.setText(nome);
		textDescrizione.setText(descrizione);
		textTipo.setText(tipoAlgoritmo.getNome());
		textValore.setText(valore);
		textAmbito.setText(ControllerAmbitiFatturazione.getInstance().getSottoAmbito(ambito).getNome());
	}

}