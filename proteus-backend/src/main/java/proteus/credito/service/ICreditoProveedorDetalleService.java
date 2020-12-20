package proteus.credito.service;

import java.util.List;

import proteus.credito.model.CreditoProveedorDetalle;
import proteus.generico.service.ICRUD;

/**
 * Services for CreditoProveedorDetalle Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICreditoProveedorDetalleService extends ICRUD<CreditoProveedorDetalle, Integer> {

	CreditoProveedorDetalle getByFacturaCompra(Integer idFacturaCompra) throws Exception;
	List<CreditoProveedorDetalle> getByCreditoProveedor(Integer idCreditoProveedor) throws Exception;
	List<CreditoProveedorDetalle> getByCreditoProveedorAndPagada(Integer idCreditoProveedor, Boolean pagada) throws Exception;
	List<CreditoProveedorDetalle> getByPagada(Boolean pagada) throws Exception;
	void checkFacturasVencidas() throws Exception;
	
}
