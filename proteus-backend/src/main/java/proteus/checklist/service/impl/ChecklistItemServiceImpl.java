package proteus.checklist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.checklist.model.ChecklistItem;
import proteus.checklist.repository.IChecklistItemRepository;
import proteus.checklist.service.IChecklistItemService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for ChecklistItem Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ChecklistItemServiceImpl extends CRUDImpl<ChecklistItem, Integer> implements IChecklistItemService {

	@Autowired
	private IChecklistItemRepository checklistItemRepository;

	@Override
	protected IGenericRepository<ChecklistItem, Integer> getRepository() {
		return checklistItemRepository;
	}

	@Override
	public ChecklistItem getByNombre(String nombre) throws Exception {
		return checklistItemRepository.findByNombre(nombre);
	}
	
	@Override
	public ChecklistItem create(ChecklistItem checklistItem) throws Exception {
		ChecklistItem checklistItemAux = this.getByNombre(checklistItem.getNombre());
		if(checklistItemAux==null) {
			return checklistItemRepository.save(checklistItem);
		}
		return null;
	}
	
}