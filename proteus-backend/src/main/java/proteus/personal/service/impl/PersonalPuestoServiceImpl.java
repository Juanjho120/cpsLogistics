package proteus.personal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.personal.model.PersonalPuesto;
import proteus.personal.repository.IPersonalPuestoRepository;
import proteus.personal.service.IPersonalPuestoService;

/**
 * Services for PersonalPuesto Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class PersonalPuestoServiceImpl extends CRUDImpl<PersonalPuesto, Integer> implements IPersonalPuestoService {

	@Autowired
	private IPersonalPuestoRepository personalPuestoRepository;

	@Override
	protected IGenericRepository<PersonalPuesto, Integer> getRepository() {
		return personalPuestoRepository;
	}

	@Override
	public PersonalPuesto getByNombre(String nombre) throws Exception {
		return personalPuestoRepository.findByNombre(nombre);
	}
	
	@Override
	public PersonalPuesto create(PersonalPuesto personalPuesto) throws Exception {
		PersonalPuesto personalPuestoAux = this.getByNombre(personalPuesto.getNombre());
		if(personalPuestoAux==null) {
			return personalPuestoRepository.save(personalPuesto);
		}
		return null;
	}
	
}