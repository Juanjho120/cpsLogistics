package proteus.categoria.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.categoria.model.Categoria;
import proteus.categoria.repository.ICategoriaRepository;
import proteus.categoria.service.ICategoriaService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for Categoria Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CategoriaServiceImpl extends CRUDImpl<Categoria, Integer> implements ICategoriaService {

	@Autowired
	private ICategoriaRepository categoriaRepository;

	@Override
	protected IGenericRepository<Categoria, Integer> getRepository() {
		return categoriaRepository;
	}

	@Override
	public Categoria getByNombre(String nombre) throws Exception {
		return categoriaRepository.findByNombre(nombre);
	}
	
	@Override
	public Categoria create(Categoria categoria) throws Exception {
		Categoria categoriaAux = this.getByNombre(categoria.getNombre());
		if(categoriaAux==null) {
			return categoriaRepository.save(categoria);
		}
		return null;
	}
	
}
