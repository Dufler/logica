package it.ltc.logica.cdg.gui.analisi.elements;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.cdg.gui.analisi.dialogs.DialogDettagliRiepilogoTempi;
import it.ltc.logica.cdg.gui.analisi.model.RiepilogoGiornalieroCDG;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.decoration.SpacerLabel;

public class RiepilogoTempiComposite extends Composite {

	private static final int style = SWT.BORDER;
	
	protected static final DecimalFormat formatEuro = new DecimalFormat("#,##0.000 \u20AC");
	protected static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private final RiepilogoGiornalieroCDG riepilogo;
	
	private final String data;
	private final String scostamento;
	private final double costiReali;
	private final double costiIpotetici;
//	private final double ricaviIpotetici;
	
	
	public RiepilogoTempiComposite(Composite parent, RiepilogoGiornalieroCDG riepilogo) {
		super(parent, style);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				apriDialogDettaglio();
			}
		});
		setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		this.riepilogo = riepilogo;
		
		//Preparo tutte le label
		data = riepilogo != null && riepilogo.getGiorno() != null ? sdf.format(riepilogo.getGiorno()) : "N/A";
		costiReali = riepilogo != null ? riepilogo.getCostiReali() : 0;
		costiIpotetici = riepilogo != null ? riepilogo.getCostiIpotetici() : 0;
//		ricaviIpotetici = riepilogo != null ? riepilogo.getRicaviIpotetici() : 0;
		double differenzaCosti = Math.abs(costiReali - costiIpotetici);
		Image iconaAndamento = costiReali > costiIpotetici ? Immagine.SOLDIGIU_16X32.getImage() : Immagine.SOLDISU_16X32.getImage();
		scostamento = "Scostamento: " + formatEuro.format(differenzaCosti);
		
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		Label labelIcona = new Label(composite, SWT.NONE);
		labelIcona.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TRANSPARENT));
		labelIcona.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		labelIcona.setImage(iconaAndamento);
		labelIcona.setToolTipText(scostamento);
		
		Label labelData = new Label(composite, SWT.NONE);
		labelData.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TRANSPARENT));
		labelData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		labelData.setText(data);
		
//		Label lblScostamento = new Label(this, SWT.NONE);
//		lblScostamento.setText(scostamento);
		
		new SpacerLabel(this);
		
		Label lblCostiReali = new Label(this, SWT.NONE);
		lblCostiReali.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TRANSPARENT));
		lblCostiReali.setText("Costi Reali: " + formatEuro.format(costiReali));
		
		Label lblCostiIpotetici = new Label(this, SWT.NONE);
		lblCostiIpotetici.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TRANSPARENT));
		lblCostiIpotetici.setText("Costi Ipotetici: " + formatEuro.format(costiIpotetici));
		
//		Label lblRicaviIpotetici = new Label(this, SWT.NONE);
//		lblRicaviIpotetici.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TRANSPARENT));
//		lblRicaviIpotetici.setText("Ricavi Ipotetici: " + formatEuro.format(ricaviIpotetici));
		
		Button btnDettaglio = new Button(this, SWT.NONE);
		btnDettaglio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriDialogDettaglio();
			}
		});
		btnDettaglio.setText("Dettaglio");
	}
	
	private void apriDialogDettaglio() {
		DialogDettagliRiepilogoTempi dialog = new DialogDettagliRiepilogoTempi(riepilogo);
		dialog.open();
	}

}
