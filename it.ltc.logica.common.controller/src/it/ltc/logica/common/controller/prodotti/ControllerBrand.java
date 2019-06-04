package it.ltc.logica.common.controller.prodotti;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Brand;

public class ControllerBrand extends ControllerCommessa<Brand> {
	
	private static final String title = "Brand";
	private static final String resource = "brand";
	
	private static final HashMap<Commessa, ControllerBrand> instances = new HashMap<>();
	
	private final HashMap<Integer, Brand> brands;

	private ControllerBrand(Commessa commessa) {
		super(title, resource, Brand[].class, commessa);
		brands = new HashMap<>();
		caricaDati();
	}
	
	public static ControllerBrand getInstance(Commessa commessa) {
		if (!instances.containsKey(commessa)) {
			ControllerBrand controller = new ControllerBrand(commessa);
			instances.put(commessa, controller);
		}
		return instances.get(commessa);
	}
	
	@Override
	protected void aggiornaInfoInserimento(Brand object, Brand nuovo) {
		object.setId(nuovo.getId());
		brands.put(nuovo.getId(), nuovo);
	}
	
	public Collection<Brand> getBrand() {
		return brands.values();
	}
	
	public Brand getBrandDaCodice(int marchio) {
		Brand brand = null;
		for (Brand b : brands.values()) {
			if (b.getCodice() == marchio) {
				brand = b;
				break;
			}
		}
		return brand;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Brand> lista) {
		brands.clear();
		for (Brand brand : lista) {
			brands.put(brand.getId(), brand);
		}
		return true;
	}

}
