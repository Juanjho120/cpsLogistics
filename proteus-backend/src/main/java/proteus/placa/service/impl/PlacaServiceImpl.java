package proteus.placa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.placa.model.Placa;
import proteus.placa.repository.IPlacaRepository;
import proteus.placa.service.IPlacaService;

/**
 * Services for Placa Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class PlacaServiceImpl extends CRUDImpl<Placa, Integer> implements IPlacaService {

	@Autowired
	private IPlacaRepository placaRepository;

	@Override
	protected IGenericRepository<Placa, Integer> getRepository() {
		return placaRepository;
	}

	@Override
	public Placa getByNumero(String numero) throws Exception {
		return placaRepository.findByNumero(numero);
	}
	
	@Override
	public Placa create(Placa placa) throws Exception {
		Placa placaAux = this.getByNumero(placa.getNumero());
		if(placaAux==null) {
			return placaRepository.save(placa);
		}
		return null;
	}

	@Override
	public List<Placa> getByMarcaAuto(Integer idMarcaAuto) throws Exception {
		return placaRepository.findByMarcaAuto(idMarcaAuto);
	}

	@Override
	public void updateUltimoKilometraje(Integer idPlaca, Integer ultimoKilometraje) throws Exception {
		Placa placa = this.getById(idPlaca);
		if(placa!=null) {
			if(ultimoKilometraje > placa.getUltimoKilometraje()) {
				placa.setUltimoKilometraje(ultimoKilometraje);
				placaRepository.save(placa);
			} else {
				throw new Exception("No es posible actualizar a un kilometraje menor o igual");
			}
		}
	}
	
}