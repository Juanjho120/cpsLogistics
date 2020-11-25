package proteus.concepto.repository;

import org.springframework.stereotype.Repository;

import proteus.concepto.model.Concepto;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for Concepto Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IConceptoRepository extends IGenericRepository<Concepto, Integer> {

	Concepto findByNombre(String nombre);
	
}
