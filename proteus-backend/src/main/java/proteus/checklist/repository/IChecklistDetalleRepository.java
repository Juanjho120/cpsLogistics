package proteus.checklist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.checklist.model.ChecklistDetalle;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for ChecklistDetalle Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IChecklistDetalleRepository extends IGenericRepository<ChecklistDetalle, Integer> {

	@Query("FROM ChecklistDetalle WHERE checklist.idChecklist = :idChecklist")
	List<ChecklistDetalle> findByChecklist(Integer idChecklist);
	
	@Modifying
	@Query("DELETE FROM ChecklistDetalle WHERE checklist.idChecklist = :idChecklist")
	void deleteByChecklist(Integer idChecklist);
	
}
