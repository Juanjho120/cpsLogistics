package proteus.marca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.marca.model.MarcaRepuesto;
import proteus.marca.repository.IMarcaRepuestoRepository;
import proteus.marca.service.IMarcaRepuestoService;

/**
 * Services for MarcaRepuesto Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class MarcaRepuestoServiceImpl extends CRUDImpl<MarcaRepuesto, Integer> implements IMarcaRepuestoService {

	@Autowired
	private IMarcaRepuestoRepository marcaRepuestoRepository;

	@Override
	protected IGenericRepository<MarcaRepuesto, Integer> getRepository() {
		return marcaRepuestoRepository;
	}

	@Override
	public MarcaRepuesto getByNombre(String nombre) throws Exception {
		return marcaRepuestoRepository.findByNombre(nombre);
	}
	
	@Override
	public MarcaRepuesto create(MarcaRepuesto marcaRepuesto) throws Exception {
		MarcaRepuesto marcaRepuestoAux = this.getByNombre(marcaRepuesto.getNombre());
		if(marcaRepuestoAux==null) {
			return marcaRepuestoRepository.save(marcaRepuesto);
		}
		return null;
	}
	
}