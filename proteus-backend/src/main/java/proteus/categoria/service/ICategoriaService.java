package proteus.categoria.service;

import proteus.categoria.model.Categoria;
import proteus.generico.service.ICRUD;

/**
 * Services for Categoria Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICategoriaService extends ICRUD<Categoria, Integer> {

	Categoria getByNombre(String nombre) throws Exception;
	
}
