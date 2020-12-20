package proteus.nota.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.nota.model.NotaCredito;

/**
 * Services for NotaCredito Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface INotaCreditoService extends ICRUD<NotaCredito, Integer> {

	List<NotaCredito> getByFacturaCompra(Integer idFacturaCompra) throws Exception;
	List<NotaCredito> getByFechaIngreso(String fechaDesde, String fechaHasta) throws Exception;
	
}
