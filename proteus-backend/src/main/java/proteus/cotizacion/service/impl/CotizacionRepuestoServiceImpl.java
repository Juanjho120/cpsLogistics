package proteus.cotizacion.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.cotizacion.model.CotizacionRepuesto;
import proteus.cotizacion.repository.ICotizacionRepuestoRepository;
import proteus.cotizacion.service.ICotizacionRepuestoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for CotizacionRepuesto Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CotizacionRepuestoServiceImpl extends CRUDImpl<CotizacionRepuesto, Integer> implements ICotizacionRepuestoService {

	@Autowired
	private ICotizacionRepuestoRepository cotizacionRepuestoRepository;

	@Override
	protected IGenericRepository<CotizacionRepuesto, Integer> getRepository() {
		return cotizacionRepuestoRepository;
	}

	@Override
	public List<CotizacionRepuesto> getByCotizacion(Integer idCotizacion) throws Exception {
		return cotizacionRepuestoRepository.findByCotizacion(idCotizacion);
	}

	@Transactional
	@Override
	public void deleteByCotizacion(Integer idCotizacion) throws Exception {
		cotizacionRepuestoRepository.deleteByCotizacion(idCotizacion);
	}
	
}