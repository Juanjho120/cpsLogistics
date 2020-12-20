package proteus.saldo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.saldo.model.Saldo;
import proteus.saldo.repository.ISaldoRepository;
import proteus.saldo.service.ISaldoService;

/**
 * Services for Saldo Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class SaldoServiceImpl extends CRUDImpl<Saldo, Integer> implements ISaldoService {
	
	@Autowired
	private ISaldoRepository saldoRepository;

	@Override
	protected IGenericRepository<Saldo, Integer> getRepository() {
		return saldoRepository;
	}

	@Override
	public Saldo getByNombre(String nombre) throws Exception {
		return saldoRepository.findByNombre(nombre);
	}
	
	@Override
	public Saldo create(Saldo saldo) throws Exception {
		Saldo saldoAux = this.getByNombre(saldo.getNombre());
		if(saldoAux==null) {
			return saldoRepository.save(saldo);
		}
		return null;
	}

}
