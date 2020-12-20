package proteus.boleta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.boleta.model.BoletaTipoDocumento;
import proteus.boleta.repository.IBoletaTipoDocumentoRepository;
import proteus.boleta.service.IBoletaTipoDocumentoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for BoletaTipoDocumento Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class BoletaTipoDocumentoServiceImpl extends CRUDImpl<BoletaTipoDocumento, Integer> implements IBoletaTipoDocumentoService {

	@Autowired
	private IBoletaTipoDocumentoRepository boletaTipoDocumentoRepository;

	@Override
	protected IGenericRepository<BoletaTipoDocumento, Integer> getRepository() {
		return boletaTipoDocumentoRepository;
	}
	
	
}
