package proteus.pago.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.pago.model.PagoProveedor;

/**
 * Services for PagoProveedor Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IPagoProveedorService extends ICRUD<PagoProveedor, Integer> {

	PagoProveedor getByNoDocumento(String noDocumento) throws Exception;
	List<PagoProveedor> getByProveedor(Integer idProveedor) throws Exception;
	List<PagoProveedor> getByFechaPago(String fechaDesde, String fechaHasta) throws Exception;
	
}
