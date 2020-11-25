package proteus.modificacion.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.modificacion.model.Modificacion;

/**
 * Services for Modificacion Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IModificacionService extends ICRUD<Modificacion, Integer> {

	List<Modificacion> getByUsuario(Integer idUsuario) throws Exception;
	List<Modificacion> getByConcepto(Integer idConcepto) throws Exception;
	List<Modificacion> getByTabla(String tabla) throws Exception;
	List<Modificacion> getByConceptoAndTabla(Integer idConcepto, String tabla) throws Exception;
	
}
