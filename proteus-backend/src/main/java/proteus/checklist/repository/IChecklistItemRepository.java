package proteus.checklist.repository;

import org.springframework.stereotype.Repository;

import proteus.checklist.model.ChecklistItem;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for ChecklistItem Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IChecklistItemRepository extends IGenericRepository<ChecklistItem, Integer> {

	ChecklistItem findByNombre(String nombre);
	
}
