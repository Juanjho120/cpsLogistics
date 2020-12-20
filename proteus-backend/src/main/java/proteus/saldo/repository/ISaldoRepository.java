package proteus.saldo.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.saldo.model.Saldo;

/**
 * Repository for Saldo Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ISaldoRepository extends IGenericRepository<Saldo, Integer> {

	Saldo findByNombre(String nombre);
	
}
