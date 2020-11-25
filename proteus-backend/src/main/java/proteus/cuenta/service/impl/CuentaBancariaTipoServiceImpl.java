package proteus.cuenta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.cuenta.model.CuentaBancariaTipo;
import proteus.cuenta.repository.ICuentaBancariaTipoRepository;
import proteus.cuenta.service.ICuentaBancariaTipoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for CuentaBancariaTipo Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CuentaBancariaTipoServiceImpl extends CRUDImpl<CuentaBancariaTipo, Integer> implements ICuentaBancariaTipoService {

	@Autowired
	private ICuentaBancariaTipoRepository cuentaBancariaTipoRepository;

	@Override
	protected IGenericRepository<CuentaBancariaTipo, Integer> getRepository() {
		return cuentaBancariaTipoRepository;
	}

	@Override
	public CuentaBancariaTipo getByNombre(String nombre) throws Exception {
		return cuentaBancariaTipoRepository.findByNombre(nombre);
	}
	
	@Override
	public CuentaBancariaTipo create(CuentaBancariaTipo cuentaBancariaTipo) throws Exception {
		CuentaBancariaTipo cuentaBancariaTipoAux = this.getByNombre(cuentaBancariaTipo.getNombre());
		if(cuentaBancariaTipoAux==null) {
			return cuentaBancariaTipoRepository.save(cuentaBancariaTipo);
		}
		return null;
	}
	
}