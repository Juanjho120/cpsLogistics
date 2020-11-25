package proteus.moneda.service;

import proteus.generico.service.ICRUD;
import proteus.moneda.model.Moneda;

/**
 * Services for Moneda Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IMonedaService extends ICRUD<Moneda, Integer> {

	Moneda getByNombreAndSimbolo(String nombre, String simbolo) throws Exception;
	
}
