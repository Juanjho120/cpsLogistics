package proteus.concepto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.concepto.model.Concepto;
import proteus.concepto.repository.IConceptoRepository;
import proteus.concepto.service.IConceptoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for Concepto Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ConceptoServiceImpl extends CRUDImpl<Concepto, Integer> implements IConceptoService {

	@Autowired
	private IConceptoRepository conceptoRepository;

	@Override
	protected IGenericRepository<Concepto, Integer> getRepository() {
		return conceptoRepository;
	}

	@Override
	public Concepto getByNombre(String nombre) throws Exception {
		return conceptoRepository.findByNombre(nombre);
	}
	
	@Override
	public Concepto create(Concepto concepto) throws Exception {
		Concepto conceptoAux = this.getByNombre(concepto.getNombre());
		if(conceptoAux==null) {
			return conceptoRepository.save(concepto);
		}
		return null;
	}
	
}