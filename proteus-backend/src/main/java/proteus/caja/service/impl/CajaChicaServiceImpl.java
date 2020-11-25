package proteus.caja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.caja.model.CajaChica;
import proteus.caja.repository.ICajaChicaRepository;
import proteus.caja.service.ICajaChicaService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for CajaChica Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CajaChicaServiceImpl extends CRUDImpl<CajaChica, Integer> implements ICajaChicaService {

	@Autowired
	private ICajaChicaRepository cajaChicaRepository;

	@Override
	protected IGenericRepository<CajaChica, Integer> getRepository() {
		return cajaChicaRepository;
	}

	@Override
	public CajaChica getByProveedorAndCodigoFactura(Integer idProveedor, String codigoFactura) throws Exception {
		return cajaChicaRepository.findByProveedorAndCodigoFactura(idProveedor, codigoFactura);
	}
	
	@Override
	public CajaChica create(CajaChica cajaChica) throws Exception {
		CajaChica cajaChicaAux = this.getByProveedorAndCodigoFactura(cajaChica.getProveedor().getIdProveedor(), cajaChica.getCodigoFactura());
		if(cajaChicaAux==null) {
			return cajaChicaRepository.save(cajaChica);
		}
		return null;
	}

	@Override
	public List<CajaChica> getByProveedor(Integer idProveedor) throws Exception {
		return cajaChicaRepository.findByProveedor(idProveedor);
	}

	@Override
	public List<CajaChica> getByServicio(Integer idServicio) throws Exception {
		return cajaChicaRepository.findByServicio(idServicio);
	}
}
