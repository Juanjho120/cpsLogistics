package proteus.checklist.service;

import java.util.List;

import proteus.checklist.model.Checklist;
import proteus.checklist.model.ChecklistDetalle;
import proteus.checklist.model.ChecklistEvaluacion;
import proteus.generico.service.ICRUD;

/**
 * Services for ChecklistDetalle Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IChecklistDetalleService extends ICRUD<ChecklistDetalle, Integer> {

	List<ChecklistDetalle> getByChecklist(Integer idChecklist) throws Exception;
	void deleteByChecklist(Integer idChecklist) throws Exception;
	List<ChecklistDetalle> createByChecklistTipo(Checklist checklist, List<ChecklistEvaluacion> checklistEvaluacionList) throws Exception;
	List<ChecklistDetalle> createByChecklistTipo(Integer idChecklistTipo) throws Exception;
	
}
