package proteus.categoria.repository;

import org.springframework.stereotype.Repository;

import proteus.categoria.model.Categoria;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for Categoria Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICategoriaRepository extends IGenericRepository<Categoria, Integer> {

	Categoria findByNombre(String nombre);
	
}
