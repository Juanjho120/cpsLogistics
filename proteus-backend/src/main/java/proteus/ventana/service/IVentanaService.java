package proteus.ventana.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.ventana.model.Ventana;

/**
 * Services for Ventana Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IVentanaService extends ICRUD<Ventana, Integer> {

	List<Ventana> getByUsername(String username) throws Exception;
	
}
