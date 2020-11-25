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

	CajaChica getByProveedorAndCodigoFactura(Integer idProveedor, String codigoFactura) throws Exception;
	List<CajaChica> getByProveedor(Integer idProveedor) throws Exception;
	List<CajaChica> getByServicio(Integer idServicio) throws Exception;
	
}
