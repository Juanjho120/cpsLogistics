package proteus.moneda.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.moneda.model.Moneda;

/**
 * Repository for Moneda Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IMonedaRepository extends IGenericRepository<Moneda, Integer> {

	Moneda findByNombreAndSimbolo(String nombre, String simbolo);
	
}
