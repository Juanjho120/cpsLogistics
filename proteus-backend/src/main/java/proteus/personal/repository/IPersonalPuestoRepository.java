package proteus.personal.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.personal.model.PersonalPuesto;

/**
 * Repository for PersonalPuesto Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IPersonalPuestoRepository extends IGenericRepository<PersonalPuesto, Integer> {

	PersonalPuesto findByNombre(String nombre);
	
}
