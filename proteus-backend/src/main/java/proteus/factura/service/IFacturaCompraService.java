package proteus.factura.service;

import java.util.List;

import proteus.factura.model.FacturaCompra;
import proteus.generico.service.ICRUD;

/**
 * Services for FacturaCompra Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IFacturaCompraService extends ICRUD<FacturaCompra, Integer>{

	List<FacturaCompra> getByCodigo(String codigo) throws Exception;
	List<FacturaCompra> getByProveedor(Integer idProveedor) throws Exception;
	FacturaCompra getByCodigoAndProveedor(String codigo, Integer idProveedor) throws Exception;
	List<FacturaCompra> getByFecha(String fechaDesde, String fechaHasta) throws Exception;
	List<FacturaCompra> getByRepuesto(Integer idRepuesto) throws Exception;
	List<FacturaCompra> getByVencimiento(Boolean vencida) throws Exception;
	List<FacturaCompra> getByPagada(Boolean pagada) throws Exception;
	List<FacturaCompra> getNotInCreditoProveedorDetalle() throws Exception;
	
}
