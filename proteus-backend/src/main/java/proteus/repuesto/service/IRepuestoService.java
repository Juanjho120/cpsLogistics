package proteus.repuesto.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.repuesto.model.Repuesto;

/**
 * Services for Repuesto Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IRepuestoService extends ICRUD<Repuesto, Integer> {

	List<Repuesto> getByCodigo(String codigo) throws Exception;
	List<Repuesto> getByCodigoBarra(String codigoBarra) throws Exception;
	void deleteByCodigo(String codigo) throws Exception;
	void deleteByCodigoBarra(String codigoBarra) throws Exception;
	void actualizarCantidad(Integer idRepuesto, Integer cantidad, Boolean sumar) throws Exception;
	String verStock(Integer idRepuesto, Integer cantidad, String mensaje) throws Exception;
	
}
