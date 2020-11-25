package proteus.checklist.service;

import proteus.checklist.model.ChecklistServicioTipo;
import proteus.generico.service.ICRUD;

/**
 * Services for ChecklistServicioTipo Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IChecklistServicioTipoService extends ICRUD<ChecklistServicioTipo, Integer> {

	ChecklistServicioTipo getByNombre(String nombre) throws Exception;
	
}
