package proteus.proveedor.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.proveedor.dto.ProveedorDTO;
import proteus.proveedor.model.Proveedor;

/**
 * Services for Proveedor Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IProveedorService extends ICRUD<Proveedor, Integer> {

	Proveedor getByNombreAndNit(String nombre, String nit) throws Exception;
	Proveedor getByNit(String nit) throws Exception;
	List<ProveedorDTO> getAllDTO() throws Exception;
	List<Proveedor> getWithCredito() throws Exception;
	
}
