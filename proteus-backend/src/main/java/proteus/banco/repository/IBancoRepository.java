package proteus.banco.repository;

import org.springframework.stereotype.Repository;

import proteus.banco.model.Banco;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for Banco Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IBancoRepository extends IGenericRepository<Banco, Integer> {

	Banco findByNombre(String nombre);
	
}
