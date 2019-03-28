package it.ltc.logica.container.element.style;

import java.util.List;

import org.eclipse.nebula.widgets.pshelf.PShelf;
import org.eclipse.nebula.widgets.pshelf.PShelfItem;
import org.eclipse.nebula.widgets.pshelf.PaletteShelfRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import it.ltc.logica.container.element.AlberoFunzioni;
import it.ltc.logica.container.element.GruppoFunzioniFeature;
import it.ltc.logica.container.model.ControllerFeatureFunzioni;
import it.ltc.logica.container.model.Modulo;
import it.ltc.logica.gui.elements.expand.EspandiBarra;
import it.ltc.logica.gui.elements.expand.EspandiElemento;

public class ManagerStileFeatureFunzioni {
	
	public enum Style { PGROUP, PSHELF, TABFOLDER, EXPANDBAR }
	
	private final ControllerFeatureFunzioni controller;
	
	public ManagerStileFeatureFunzioni() {
		controller = ControllerFeatureFunzioni.getInstance();
	}
	
	public void setStyleAndFeatures(Composite parent, Style style) {
		List<Modulo> features = controller.getFeatures();
		switch (style) {
			case EXPANDBAR : setEspandiBarra(parent, features); break;
			case PGROUP : setPGroup(parent, features); break;
			case PSHELF : setPShelf(parent, features); break;
			case TABFOLDER : setTabFolder(parent, features); break;
			default : throw new RuntimeException("Lo stile indicato per la lista delle funzioni non è valido!");
		}
	}
	
	public void setPGroup(Composite parent, List<Modulo> features) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		for (Modulo feature : features) {
			GruppoFunzioniFeature gruppo = new GruppoFunzioniFeature(composite, feature);
			gruppo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		}
		
	}
	
	public void setPShelf(Composite parent, List<Modulo> features) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setBackgroundImage(SWTResourceManager.getImage(ManagerStileFeatureFunzioni.class, "/it/ltc/logica/gui/resources/ltc_logo.png"));
		
		PShelf shelf = new PShelf(composite, SWT.NONE);
		shelf.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		shelf.setBackgroundMode(SWT.INHERIT_FORCE);
		shelf.setRenderer(new PaletteShelfRenderer());
		shelf.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));

		PShelfItem item1 = new PShelfItem(shelf, SWT.NONE);
		item1.getBody().setBackgroundMode(SWT.INHERIT_FORCE);
		item1.setImage(SWTResourceManager.getImage(ManagerStileFeatureFunzioni.class, "/it/ltc/logica/gui/resources/frecciaBluSu_16x16.png"));
		item1.getBody().setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		item1.setText("First Item");
		item1.getBody().setLayout(new GridLayout(1, false));

		Button btnNewButton_6 = new Button(item1.getBody(), SWT.NONE);
		btnNewButton_6.setAlignment(SWT.LEFT);
		btnNewButton_6.setImage(SWTResourceManager.getImage(ManagerStileFeatureFunzioni.class, "/it/ltc/logica/gui/resources/frecciaBluSu_16x16.png"));
		btnNewButton_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		btnNewButton_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnNewButton_6.setText("New Button");

		Label lblQualcosa = new Label(item1.getBody(), SWT.NONE);
		lblQualcosa.setImage(SWTResourceManager.getImage(ManagerStileFeatureFunzioni.class, "/it/ltc/logica/gui/resources/frecciaBluSu_16x16.png"));
		lblQualcosa.setText("Qualcosa");

		Button btnNewButton_7 = new Button(item1.getBody(), SWT.NONE);
		btnNewButton_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnNewButton_7.setText("New Button");

		PShelfItem item2 = new PShelfItem(shelf, SWT.NONE);
		item2.setImage(SWTResourceManager.getImage(ManagerStileFeatureFunzioni.class, "/it/ltc/logica/gui/resources/croceRossa_16x16.png"));
		item2.getBody().setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		item2.setText("Second Item");

		item2.getBody().setLayout(new FillLayout());

	}
	
	public void setTabFolder(Composite parent, List<Modulo> features) {
		SashForm sashForm = new SashForm(parent, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		CTabFolder tabFolder = new CTabFolder(sashForm, SWT.BORDER);
		tabFolder.setBackgroundImage(SWTResourceManager.getImage(ManagerStileFeatureFunzioni.class, "/it/ltc/logica/gui/resources/ltc_logo.png"));
		tabFolder.setTabHeight(0);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmAdmin = new CTabItem(tabFolder, SWT.NONE);
		tbtmAdmin.setText("Admin");
		
		CTabItem tbtmTrasporti = new CTabItem(tabFolder, SWT.NONE);
		tbtmTrasporti.setText("Trasporti");

		Button btnNewButton_9 = new Button(composite, SWT.FLAT | SWT.TOGGLE);
		btnNewButton_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabFolder.setSelection(0);
			}
		});
		btnNewButton_9.setText("Admin");

		Button btnNewButton_10 = new Button(composite, SWT.NONE);
		btnNewButton_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// throw new RuntimeException();
				tabFolder.setSelection(1);
			}
		});
		btnNewButton_10.setText("Trasporti");

		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		tbtmAdmin.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));

		Button btnNewButton_11 = new Button(composite_1, SWT.NONE);
		btnNewButton_11.setText("Nuovo utente");

		Button btnNewButton_12 = new Button(composite_1, SWT.NONE);
		btnNewButton_12.setText("Gestione utenti");

		Button btnNewButton_13 = new Button(composite_1, SWT.NONE);
		btnNewButton_13.setText("Permessi");

		

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		tbtmTrasporti.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));

		Button btnNewButton_14 = new Button(composite_2, SWT.NONE);
		btnNewButton_14.setText("Fatture");

		Button btnNewButton_15 = new Button(composite_2, SWT.NONE);
		btnNewButton_15.setText("Spedizioni");
		sashForm.setWeights(new int[] { 85, 350 });
	}

	public void setEspandiBarra(Composite parent, List<Modulo> features) {
		EspandiBarra barraFunzioni = new EspandiBarra(parent);
		barraFunzioni.setBackgroundMode(SWT.INHERIT_DEFAULT);

		barraFunzioni.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		barraFunzioni.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		barraFunzioni.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite compositeAdmin = new Composite(barraFunzioni, SWT.NONE);
		compositeAdmin.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		compositeAdmin.setLayout(new FillLayout(SWT.VERTICAL));

		Button btnNewButton = new Button(compositeAdmin, SWT.NONE);
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		btnNewButton.setText("Gestione Utenti");

		Button btnNewButton_1 = new Button(compositeAdmin, SWT.NONE);
		btnNewButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		btnNewButton_1.setText("Nuovo Utente");

		EspandiElemento admin = new EspandiElemento(barraFunzioni, compositeAdmin, "Admin");
		admin.setExpanded(true);

		Composite compositeTrasporti = new Composite(barraFunzioni, SWT.NONE);
		compositeTrasporti.setLayout(new FillLayout(SWT.VERTICAL));

		Button btnSpedizioni = new Button(compositeTrasporti, SWT.NONE);
		btnSpedizioni.setText("Spedizioni");

		Button btnCodiciCliente = new Button(compositeTrasporti, SWT.NONE);
		btnCodiciCliente.setText("Codici Cliente");

		EspandiElemento trasporti = new EspandiElemento(barraFunzioni, compositeTrasporti, "Trasporti");
		trasporti.setExpanded(true);
	}


	public void setAlbero(Composite parent, List<Modulo> features) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		AlberoFunzioni albero = new AlberoFunzioni(composite);
		albero.setFunzioni(features);
	}

}
