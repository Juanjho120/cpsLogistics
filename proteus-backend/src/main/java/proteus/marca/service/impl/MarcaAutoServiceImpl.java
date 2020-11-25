package proteus.marca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.marca.model.MarcaAuto;
import proteus.marca.repository.IMarcaAutoRepository;
import proteus.marca.service.IMarcaAutoService;

/**
 * Services for MarcaAuto Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class MarcaAutoServiceImpl extends CRUDImpl<MarcaAuto, Integer> implements IMarcaAutoService {

	@Autowired
	private IMarcaAutoRepository marcaAutoRepository;

	@Override
	protected IGenericRepository<MarcaAuto, Integer> getRepository() {
		return marcaAutoRepository;
	}

	@Override
	public MarcaAuto getByNombre(String nombre) throws Exception {
		return marcaAutoRepository.findByNombre(nombre);
	}
	
	@Override
	public MarcaAuto create(MarcaAuto marcaAuto) throws Exception {
		MarcaAuto marcaAutoAux = this.getByNombre(marcaAuto.getNombre());
		if(marcaAutoAux==null) {
			return marcaAutoRepository.save(marcaAuto);
		}
		return null;
	}
	
}