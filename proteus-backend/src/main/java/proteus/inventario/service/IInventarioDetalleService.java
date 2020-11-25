package proteus.inventario.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.inventario.model.InventarioDetalle;

/**
 * Services for InventarioDetalle Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IInventarioDetalleService extends ICRUD<InventarioDetalle, Integer> {

	void deleteByInventario(Integer idInventario) throws Exception;
	List<InventarioDetalle> getByInventario(Integer idInventario) throws Exception;
	
}
