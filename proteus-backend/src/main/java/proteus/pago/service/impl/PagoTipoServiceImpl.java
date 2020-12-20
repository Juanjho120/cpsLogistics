package proteus.pago.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.pago.model.PagoTipo;
import proteus.pago.repository.IPagoTipoRepository;
import proteus.pago.service.IPagoTipoService;

/**
 * Services for PagoTipo Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class PagoTipoServiceImpl extends CRUDImpl<PagoTipo, Integer> implements IPagoTipoService {

	@Autowired
	private IPagoTipoRepository pagoTipoRepository;

	@Override
	protected IGenericRepository<PagoTipo, Integer> getRepository() {
		return pagoTipoRepository;
	}
	
	@Override
	public PagoTipo create(PagoTipo pagoTipo) throws Exception {
		PagoTipo pagoTipoAux = pagoTipoRepository.findByNombre(pagoTipo.getNombre());
		if(pagoTipoAux==null) {
			return pagoTipoRepository.save(pagoTipo);
		}
		return null;
	}
	
}
