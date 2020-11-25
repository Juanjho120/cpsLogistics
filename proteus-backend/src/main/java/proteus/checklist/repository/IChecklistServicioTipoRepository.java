package proteus.checklist.repository;

import org.springframework.stereotype.Repository;

import proteus.checklist.model.ChecklistServicioTipo;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for ChecklistServicioTipo Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IChecklistServicioTipoRepository extends IGenericRepository<ChecklistServicioTipo, Integer> {

	ChecklistServicioTipo findByNombre(String nombre);
	
}
