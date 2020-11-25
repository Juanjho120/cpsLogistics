package proteus.cuenta.repository;

import org.springframework.stereotype.Repository;

import proteus.cuenta.model.CuentaBancariaTipo;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for CuentaBancariaTipo Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICuentaBancariaTipoRepository extends IGenericRepository<CuentaBancariaTipo, Integer> {

	CuentaBancariaTipo findByNombre(String nombre);
	
}
