package proteus.checklist.repository;

import org.springframework.stereotype.Repository;

import proteus.checklist.model.ChecklistEvaluacion;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for ChecklistEvaluacion Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IChecklistEvaluacionRepository extends IGenericRepository<ChecklistEvaluacion, Integer> {

	ChecklistEvaluacion findByNombre(String nombre);
	
}
