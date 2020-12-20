package proteus.caja.service;

import java.util.List;

import proteus.caja.model.CajaChica;
import proteus.generico.service.ICRUD;

/**
 * Services for CajaChica Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICajaChicaService extends ICRUD<CajaChica, Integer> {

	List<CajaChica> getByServicio(Integer idServicio) throws Exception;
	List<CajaChica> getByPlaca(Integer idPlaca) throws Exception;
	List<CajaChica> getByFechaIngreso(String fechaDesde, String fechaHasta) throws Exception;
	
}
