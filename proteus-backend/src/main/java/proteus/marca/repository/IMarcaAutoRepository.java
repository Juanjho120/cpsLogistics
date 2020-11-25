package proteus.marca.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.marca.model.MarcaAuto;

/**
 * Repository for MarcaAuto Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IMarcaAutoRepository extends IGenericRepository<MarcaAuto, Integer> {

	MarcaAuto findByNombre(String nombre);
	
}
