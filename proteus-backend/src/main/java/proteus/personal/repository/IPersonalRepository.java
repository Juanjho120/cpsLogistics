package proteus.personal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.personal.model.Personal;

/**
 * Repository for Personal Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IPersonalRepository extends IGenericRepository<Personal, Integer> {

	Personal findByTelefono(String telefono);
	
	@Query("FROM Personal WHERE personalPuesto.idPersonalPuesto = :idPersonalPuesto")
	List<Personal> findByPersonalPuesto(Integer idPersonalPuesto);
	
}
