package proteus.factura.service;

import java.util.List;

import proteus.factura.model.FacturaCompraDetalle;
import proteus.generico.service.ICRUD;

/**
 * Services for FacturaCompraDetalle Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IFacturaCompraDetalleService extends ICRUD<FacturaCompraDetalle, Integer> {

	List<FacturaCompraDetalle> getByFacturaCompra(Integer idFacturaCompra) throws Exception;
	void deleteByFacturaCompra(Integer idFacturaCompra) throws Exception;
}
