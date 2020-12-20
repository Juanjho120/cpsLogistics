package proteus.comprobante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.comprobante.model.ComprobanteTipo;
import proteus.comprobante.repository.IComprobanteTipoRepository;
import proteus.comprobante.service.IComprobanteTipoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for ComprobanteTipo Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ComprobanteTipoServiceImpl extends CRUDImpl<ComprobanteTipo, Integer> implements IComprobanteTipoService {
	
	@Autowired
	private IComprobanteTipoRepository comprobanteTipoRepository;

	@Override
	protected IGenericRepository<ComprobanteTipo, Integer> getRepository() {
		return comprobanteTipoRepository;
	}
	
	@Override
	public ComprobanteTipo getByNombre(String nombre) throws Exception {
		return comprobanteTipoRepository.findByNombre(nombre);
	}
	
	@Override
	public ComprobanteTipo create(ComprobanteTipo comprobanteTipo) throws Exception {
		ComprobanteTipo comprobanteTipoAux = this.getByNombre(comprobanteTipo.getNombre());
		if(comprobanteTipoAux==null) {
			return comprobanteTipoRepository.save(comprobanteTipo);
		}
		return null;
	}

}
