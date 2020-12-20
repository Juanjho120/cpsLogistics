package proteus.transaccion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.transaccion.model.TransaccionEstado;
import proteus.transaccion.repository.ITransaccionEstadoRepository;
import proteus.transaccion.service.ITransaccionEstadoService;

/**
 * Services for TransaccionEstado Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class TransaccionEstadoServiceImpl extends CRUDImpl<TransaccionEstado, Integer> implements ITransaccionEstadoService {

	@Autowired
	private ITransaccionEstadoRepository transaccionEstadoRepository;

	@Override
	protected IGenericRepository<TransaccionEstado, Integer> getRepository() {
		return transaccionEstadoRepository;
	}
	
	@Override
	public TransaccionEstado create(TransaccionEstado transaccionEstado) throws Exception {
		TransaccionEstado transaccionEstadoAux = transaccionEstadoRepository.findByNombre(transaccionEstado.getNombre());
		if(transaccionEstadoAux==null) {
			return transaccionEstadoRepository.save(transaccionEstado);
		}
		return null;
	}
	
}
