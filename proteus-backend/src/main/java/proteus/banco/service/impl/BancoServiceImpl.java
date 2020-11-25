package proteus.banco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.banco.model.Banco;
import proteus.banco.repository.IBancoRepository;
import proteus.banco.service.IBancoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for Banco Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class BancoServiceImpl extends CRUDImpl<Banco, Integer> implements IBancoService {

	@Autowired
	private IBancoRepository bancoRepository;

	@Override
	protected IGenericRepository<Banco, Integer> getRepository() {
		return bancoRepository;
	}

	@Override
	public Banco getByNombre(String nombre) throws Exception {
		return bancoRepository.findByNombre(nombre);
	}
	
	@Override
	public Banco create(Banco banco) throws Exception {
		Banco bancoAux = this.getByNombre(banco.getNombre());
		if(bancoAux==null) {
			return bancoRepository.save(banco);
		}
		return null;
	}
	
}
