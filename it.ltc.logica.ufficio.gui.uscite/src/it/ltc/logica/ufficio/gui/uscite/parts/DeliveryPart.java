
package it.ltc.logica.ufficio.gui.uscite.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.ufficio.gui.uscite.elements.delivery.TabellaDelivery;
import it.ltc.logica.ufficio.gui.uscite.elements.delivery.ToolbarDelivery;

public class DeliveryPart {

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarDelivery toolbar = new ToolbarDelivery(parent);
		
		Label lblDelivery = new Label(parent, SWT.NONE);
		lblDelivery.setText("Delivery: ");
		
		TabellaDelivery tabella = new TabellaDelivery(parent);
		
		toolbar.setTabella(tabella);
	}

}