package proteus.moneda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.moneda.model.Moneda;
import proteus.moneda.repository.IMonedaRepository;
import proteus.moneda.service.IMonedaService;

/**
 * Services for Moneda Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class MonedaServiceImpl extends CRUDImpl<Moneda, Integer> implements IMonedaService {

	@Autowired
	private IMonedaRepository monedaRepository;

	@Override
	protected IGenericRepository<Moneda, Integer> getRepository() {
		return monedaRepository;
	}

	@Override
	public Moneda getByNombreAndSimbolo(String nombre, String simbolo) throws Exception {
		return monedaRepository.findByNombreAndSimbolo(nombre, simbolo);
	}
	
	@Override
	public Moneda create(Moneda moneda) throws Exception {
		Moneda monedaAux = this.getByNombreAndSimbolo(moneda.getNombre(), moneda.getSimbolo());
		if(monedaAux==null) {
			return monedaRepository.save(moneda);
		}
		return null;
	}
	
}