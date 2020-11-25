package proteus.personal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.personal.model.Personal;
import proteus.personal.repository.IPersonalRepository;
import proteus.personal.service.IPersonalService;

/**
 * Services for Personal Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class PersonalServiceImpl extends CRUDImpl<Personal, Integer> implements IPersonalService {

	@Autowired
	private IPersonalRepository personalRepository;

	@Override
	protected IGenericRepository<Personal, Integer> getRepository() {
		return personalRepository;
	}

	@Override
	public Personal getByTelefono(String telefono) throws Exception {
		return personalRepository.findByTelefono(telefono);
	}

	@Override
	public List<Personal> getByPersonalPuesto(Integer idPersonalPuesto) throws Exception {
		return personalRepository.findByPersonalPuesto(idPersonalPuesto);
	}
	
	@Override
	public Personal create(Personal personal) throws Exception {
		Personal personalAux = this.getByTelefono(personal.getTelefono());
		if(personalAux==null) {
			return personalRepository.save(personal);
		}
		return null;
	}
	
}