package proteus.marca.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.marca.model.MarcaRepuesto;

/**
 * Repository for MarcaRepuesto Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IMarcaRepuestoRepository extends IGenericRepository<MarcaRepuesto, Integer> {

	MarcaRepuesto findByNombre(String nombre);
	
}
