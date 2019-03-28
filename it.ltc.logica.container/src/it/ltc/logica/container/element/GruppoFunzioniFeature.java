package it.ltc.logica.container.element;

import org.eclipse.nebula.widgets.pgroup.ChevronsToggleRenderer;
import org.eclipse.nebula.widgets.pgroup.FormGroupStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ExpandAdapter;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import it.ltc.logica.container.model.ControllerFeatureFunzioni;
import it.ltc.logica.container.model.Modulo;
import it.ltc.logica.container.model.FunzioneModulo;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.nebula.GruppoPulsanti;

public class GruppoFunzioniFeature extends GruppoPulsanti {

	public GruppoFunzioniFeature(Composite parent, Modulo feature) {
		super(parent, Immagine.valueOf(feature.getImagePath()).getImage(), feature.getNome());
		
		setToggleRenderer(new ChevronsToggleRenderer());
	    setStrategy(new FormGroupStrategy());
	    setBackground(SWTResourceManager.getColor(feature.getColorSWT()));
	    setBackgroundMode(SWT.INHERIT_FORCE);
	    
	    FillLayout layoutGroup = new FillLayout(SWT.HORIZONTAL);
	    setLayout(layoutGroup);
		
		FillLayout layoutComposite = new FillLayout(SWT.VERTICAL);
	    composite.setLayout(layoutComposite);
	    
	    aggiungiFunzioni(feature);
	    
	    setExpanded(false);
	    
	    addExpandListener(new ExpandAdapter() {
	    	@Override
	    	public void itemExpanded(ExpandEvent e) {
	    		System.out.println("switch perspective");
	    		ControllerFeatureFunzioni.getInstance().cambiaPerspective(feature.getPerspectiveID());
	    	}
	    });
	}
	
	private void aggiungiFunzioni(Modulo feature) {
		for (FunzioneModulo funzione : feature.getFunzioni()) {
			Button btnFunzione = new Button(composite, SWT.NONE);
			btnFunzione.setText(funzione.getNome());
			btnFunzione.setImage(Immagine.valueOf(funzione.getImagePath()).getImage());
			btnFunzione.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					//Switch perspective (if necessary) and part.
					System.out.println("switch perspective and show part");
					ControllerFeatureFunzioni.getInstance().cambiaPerspective(feature.getPerspectiveID());
					ControllerFeatureFunzioni.getInstance().mostraPart(funzione.getPartID());
				}
			});
		}
	}

}
