package proteus.transaccion.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.transaccion.model.Transaccion;

/**
 * Services for Transaccion Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ITransaccionService extends ICRUD<Transaccion, Integer> {

	Transaccion getByReferencia(String referencia) throws Exception;
	List<Transaccion> getByFechaAprobacion(String fechaDesde, String fechaHasta) throws Exception;
	List<Transaccion> getByFacturaNumeroInSegmentoPago(String facturaNumero) throws Exception;
	
}
