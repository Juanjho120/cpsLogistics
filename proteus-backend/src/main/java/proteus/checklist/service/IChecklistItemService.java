package proteus.checklist.service;

import proteus.checklist.model.ChecklistItem;
import proteus.generico.service.ICRUD;

/**
 * Services for ChecklistItem Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IChecklistItemService extends ICRUD<ChecklistItem, Integer> {

	ChecklistItem getByNombre(String nombre) throws Exception;
	
}
