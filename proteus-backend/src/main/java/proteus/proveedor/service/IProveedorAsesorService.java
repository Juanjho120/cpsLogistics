package proteus.proveedor.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.proveedor.model.ProveedorAsesor;

/**
 * Services for ProveedorAsesor Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IProveedorAsesorService extends ICRUD<ProveedorAsesor, Integer> {

	ProveedorAsesor getByTelefono(String telefono) throws Exception;
	ProveedorAsesor getByNombreAndTelefonoAndProveedor(String nombre, String telefono, Integer idProveedor) throws Exception;
	List<ProveedorAsesor> getByProveedor(Integer idProveedor) throws Exception;
	void deleteByProveedor(Integer idProveedor) throws Exception;
	
}
