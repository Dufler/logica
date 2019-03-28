package it.ltc.logica.cdg.gui.analisi.dialogs;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.analisi.elements.TabellaRiepilogoGiornalieroPerFase;
import it.ltc.logica.cdg.gui.analisi.model.RiepilogoGiornalieroCDG;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogSemplice;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;

public class DialogDettagliRiepilogoTempi extends DialogSemplice {
	
	private final static String title = "Dettagli del Riepilogo";
	
	protected final DecimalFormat formatEuro = new DecimalFormat("#,##0.000 \u20AC");
	protected final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private final RiepilogoGiornalieroCDG riepilogo;
	
	public DialogDettagliRiepilogoTempi(RiepilogoGiornalieroCDG riepilogo) {
		super(title, null, true);
		
		this.riepilogo = riepilogo;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		
		String data = riepilogo != null && riepilogo.getGiorno() != null ? sdf.format(riepilogo.getGiorno()) : "N/A";
		double costiReali = riepilogo != null ? riepilogo.getCostiReali() : 0;
		/*Fix sugli extra, li tolgo dal totale per non essere fuorviante*/
		double costiExtra = riepilogo != null ? riepilogo.getCostiExtra() : 0;
		costiReali -= costiExtra;
		/*Fine fix*/
		double costiIpotetici = riepilogo != null ? riepilogo.getCostiIpotetici() : 0;
//		double ricaviIpotetici = riepilogo != null ? riepilogo.getRicaviIpotetici() : 0;
		double differenzaCosti = Math.abs(costiReali - costiIpotetici);
		Image iconaAndamento = costiReali > costiIpotetici ? Immagine.SOLDIGIU_16X32.getImage() : Immagine.SOLDISU_16X32.getImage();
		String scostamento = "Totale Scostamento: " + formatEuro.format(differenzaCosti);
		
		Composite compositeSommario = new Composite(container, SWT.NONE);
		compositeSommario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeSommario.setLayout(new GridLayout(2, false));
		
		Label lblData = new Label(compositeSommario, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblData.setText("Data: " + data);
		
		Label labelIcona = new Label(compositeSommario, SWT.NONE);
		labelIcona.setImage(iconaAndamento);
		labelIcona.setToolTipText(scostamento);
		
		new Label(compositeSommario, SWT.NONE);
		new Label(compositeSommario, SWT.NONE);
		
//		Label lblTotaleRicaviIpotetici = new Label(compositeSommario, SWT.NONE);
//		lblTotaleRicaviIpotetici.setText("Totale Ricavi Ipotetici : " + formatEuro.format(ricaviIpotetici));
		
//		new Label(compositeSommario, SWT.NONE);
		
		Label lblTotaleCostiIpotetici = new Label(compositeSommario, SWT.NONE);
		lblTotaleCostiIpotetici.setText("Totale Costi Ipotetici : " + formatEuro.format(costiIpotetici));
		
		new Label(compositeSommario, SWT.NONE);
		
		Label lblTotaleCostiReali = new Label(compositeSommario, SWT.NONE);
		lblTotaleCostiReali.setText("Totale Costi Reali :" + formatEuro.format(costiReali));
		
		new Label(compositeSommario, SWT.NONE);
		
		Label lblTotaleScostamento = new Label(compositeSommario, SWT.NONE);
		lblTotaleScostamento.setText(scostamento);
		
		new Label(compositeSommario, SWT.NONE);
		
		TabellaRiepilogoGiornalieroPerFase tabella = new TabellaRiepilogoGiornalieroPerFase(container, TabellaRiepilogoGiornalieroPerFase.Tipo.TEMPI);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabella.setElementi(riepilogo.getMappaFaseTotali().values());
	}

	@Override
	public void checkElementiGrafici() {}

}
