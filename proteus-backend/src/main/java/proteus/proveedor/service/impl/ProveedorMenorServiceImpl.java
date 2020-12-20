package proteus.proveedor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.proveedor.model.ProveedorMenor;
import proteus.proveedor.repository.IProveedorMenorRepository;
import proteus.proveedor.service.IProveedorMenorService;

/**
 * Services for ProveedorMenor Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ProveedorMenorServiceImpl extends CRUDImpl<ProveedorMenor, Integer> implements IProveedorMenorService {

	@Autowired
	private IProveedorMenorRepository proveedorMenorRepository;

	@Override
	public ProveedorMenor getByNombre(String nombre) throws Exception {
		return proveedorMenorRepository.findByNombre(nombre);
	}

	@Override
	protected IGenericRepository<ProveedorMenor, Integer> getRepository() {
		return proveedorMenorRepository;
	}
	
	
}
