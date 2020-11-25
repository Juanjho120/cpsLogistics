package proteus.cuenta.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.cuenta.model.CuentaBancaria;
import proteus.cuenta.repository.ICuentaBancariaRepository;
import proteus.cuenta.service.ICuentaBancariaService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for CuentaBancaria Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CuentaBancariaServiceImpl extends CRUDImpl<CuentaBancaria, Integer> implements ICuentaBancariaService {

	@Autowired
	private ICuentaBancariaRepository cuentaBancariaRepository;

	@Override
	protected IGenericRepository<CuentaBancaria, Integer> getRepository() {
		return cuentaBancariaRepository;
	}

	@Override
	public CuentaBancaria getByNumeroAndBanco(String numero, Integer idBanco) throws Exception {
		return cuentaBancariaRepository.findByNumeroAndBanco(numero, idBanco);
	}
	
	@Override
	public CuentaBancaria create(CuentaBancaria cuentaBancaria) throws Exception {
		CuentaBancaria cuentaBancariaAux = this.getByNumeroAndBanco(cuentaBancaria.getNumero(), cuentaBancaria.getBanco().getIdBanco());
		if(cuentaBancariaAux==null) {
			return cuentaBancariaRepository.save(cuentaBancaria);
		}
		return null;
	}

	@Override
	public List<CuentaBancaria> getByCategoria(Integer idCategoria) throws Exception {
		return cuentaBancariaRepository.findByCategoria(idCategoria);
	}

	@Override
	public List<CuentaBancaria> getByCategoriaAndIdItem(Integer idCategoria, Integer idItem) throws Exception {
		return cuentaBancariaRepository.findByCategoriaAndIdItem(idCategoria, idItem);
	}

	@Override
	public List<CuentaBancaria> getByCuentaBancariaTipo(Integer idCuentaBancariaTipo) throws Exception {
		return cuentaBancariaRepository.findByCuentaBancariaTipo(idCuentaBancariaTipo);
	}

	@Transactional
	@Override
	public void deleteByCategoriaAndIdItem(Integer idCategoria, Integer idItem) throws Exception {
		cuentaBancariaRepository.deleteByCategoriaAndIdItem(idCategoria, idItem);
	}
	
}