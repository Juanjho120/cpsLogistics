package proteus.checklist.service;

import proteus.checklist.model.ChecklistEvaluacion;
import proteus.generico.service.ICRUD;

/**
 * Services for ChecklistEvaluacion Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IChecklistEvaluacionService extends ICRUD<ChecklistEvaluacion, Integer> {

	ChecklistEvaluacion getByNombre(String nombre) throws Exception;
	
}
